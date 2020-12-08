package com.group5.BookRead.services.book.BookDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLIntegrityConstraintViolationException;
import com.group5.BookRead.services.book.BookExistsOnTragetShelfException;
import org.springframework.stereotype.Component;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;

import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.services.book.BookService;
import com.group5.BookRead.services.book.DecoratorChainException;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Component
public final class BookShelfServiceDecorator extends BookServiceDecorator {
    private BookRepository bookRepository;
    private BookshelfServiceSelector bookshelfServiceSelector;
    private BookshelfRepository bookshelfRepo;
    private MyBookRepository myBookRepository;

    @Autowired
    public BookShelfServiceDecorator (
        final BookService service,
        final BookRepository bookRepository,
        final BookshelfServiceSelector bookshelfServiceSelector,
        final BookshelfRepository bookshelfRepo,
        final MyBookRepository myBookRepository) {
        super(service);
        this.bookRepository = bookRepository;
        this.bookshelfServiceSelector = bookshelfServiceSelector;
        this.bookshelfRepo = bookshelfRepo;
        this.myBookRepository = myBookRepository;
    }
    @Override
	public Bookshelf getShelf(final String bookshelf, final int userId) {
        return bookshelfServiceSelector.getBookShelf(userId, bookshelf);
    }
    @Override
    public boolean remove(final int bookId,
                          final int userId,
                          final String bookshelf) {
        Bookshelf shelf = bookshelfServiceSelector.getBookShelf(userId,
                bookshelf);
        int status = myBookRepository.deleteByUserIdAndBookshelfIdAndBookId(
                userId,
                shelf.getId(),
                bookId);
        return  status == 1;
    }
    @Override
    public Book remove(final int bookId,
                       final String bookshelf,
                       final int userId) {
        Book book = super.getBook(bookId);
        if (remove(bookId, userId, bookshelf)) {
            return book;
        }
        return null;
    }

    /**
     *  Heleper methods for Book service
     * @param bookshelfName
     * @param userId
     * @return
     */
    @Override
    public List<MyBook> getMyBooks(final String bookshelfName,
                                   final int userId) {
        Bookshelf bookshelf = bookshelfServiceSelector.getBookShelf(
                userId,
                bookshelfName);
        return myBookRepository.findAllByUserIdAndShelfId(
                userId,
                bookshelf.getId());
    }
    

    /**
     *
     * @param book
     * @param bookshelfName
     * @param userId
     * @return
     */
    @Override
    public Book addBookToShelf(final Book book,
                    final String bookshelfName,
                    final int userId)
            throws BookExistsOnTragetShelfException {

        Bookshelf bookShelf = getShelf(
                bookshelfName,
                userId);
        MyBook existing = super.getMyBook(
                bookShelf.getId(),
                userId,
                book.getId());

        // check if there are duplicates in same shelf
        if (existing != null) {
            // exists
            throw new BookExistsOnTragetShelfException(
                    book.getTitle() + " exists on " + bookshelfName);
        }

        //TODO: please refactor by using bookshelf subclasses
        // used for mutual excluse want to read, reading, and read bookshelf
        // i.e. only one can be in these three shelves
        if (bookshelfName.equals("want to read")
            || bookshelfName.equals("read")
            || bookshelfName.equals("reading")) {
            List<Bookshelf> shelves = getBookShelves(userId);

            for (Bookshelf shelf : shelves) {
                if (shelf.getName().equals("want to read")
                    || shelf.getName().equals("read")
                    || shelf.getName().equals("reading")) {
                    MyBook curBook = super.getMyBook(
                            userId,
                            shelf.getId(),
                            book.getId());

                    if (curBook != null) {
                        throw new BookExistsOnTragetShelfException(
                                book.getTitle() + " exists on "
                                + shelf.getName());
                    }
                }
            }
        }

        // Create a new myBook as we are changing the bookshelf
        MyBook myBook = new MyBook(
                book.getId(),
                userId,
                bookShelf.getId(),
                0);

        // add myBook object to db
        if (addToShelf(myBook)) {
            return book;
        }
        return null;
    }
    
    @Override
    public boolean addToShelf(final MyBook book) {
        try {
            return myBookRepository.insert(book) == 1;
        } catch (SQLIntegrityConstraintViolationException e) {
            return false;
        }
    }

    /**
     * get all Books with inforamtion for a user on a bookshelf
     * @param bookshelfName
     * @param userId
     * @return
     */
    @Override
    public List<Book> getBooks(final String bookshelfName, final int userId) {
        List<MyBook> myBooks = getMyBooks(
                bookshelfName,
                userId);

       //System.out.println(myBooks);
        List<Book> books = new ArrayList<>();
        for (MyBook mybook : myBooks) {
            Book b = bookRepository.findById(mybook.getBookId());
            if (b != null) {
                books.add(b);
            }
        }
        return books;
    }


    /**
     * Get all books from all bookshelf, used for initial rendering
     * @param userId
     * @return
     */
    @Override
    public HashMap<String, List<Book>> getBooksOnBookshelves(final int userId) {
        List<Bookshelf> bookshelves = getBookShelves(userId);
        // System.out.println(bookshelves.size());
        HashMap<String, List<Book>> map = new HashMap<>();
        for (Bookshelf bookshelf : bookshelves) {
            map.put(bookshelf.getName(),
                    getBooks(bookshelf.getName(), userId));
        }
        return map;
    }
    @Override
    public List<Bookshelf> getBookShelves(final int userId) {
        return bookshelfServiceSelector.getBookShelves(userId);
    }

    /**
     * get currect bookshelf that the book located in current user
     * only return: want to read, reading, and read used for automatical
     * moving bookshelf
     * @param bookId
     * @param userId
     * @return bookshelf
     */
    @Override
    public Bookshelf getReadingShelf(final int userId, final int bookId) {
        List<MyBook> mybooks = this.getMyBooks(userId, bookId);
        for (MyBook b : mybooks) {
            Bookshelf shelf = bookshelfRepo.findById(b.getBookshelfId());
            String name = shelf.getName();
            if (name.equals("want to read")
                || name.equals("reading")
                || name.equals("read")) {
                return shelf;
            }
        }
        System.out.println("book not in want to read, reading or read");
        return null;
    }

    /**
     * move book from srcShelf to dstShelf with progress maintained
     * @param srcShelf
     * @param dstShelf
     * @param userId
     * @param bookId
     * @return void
     */
    @Override
    public void moveBook(final String srcShelf, final String dstShelf,
        final int userId, final int bookId) {
        Bookshelf srcSh = this.getShelf(srcShelf, userId);
        Bookshelf dstSh = this.getShelf(dstShelf, userId);
        MyBook mbook = this.getMyBook(userId, srcSh.getId(), bookId);
        mbook.setBookshelfId(dstSh.getId());
        myBookRepository.update(mbook);
    }

    /**
     * Get excluded book list of the user
     * @param userId
     * @return excluded book list
     */
    @Override
    public List<Integer> getExcludedBooks(final int userId) 
        throws DecoratorChainException {
        return super.getExcludedBooks(userId);
    }


    /**
     * Add book to user's excluded list
     * @param bookId
     * @param userId
     */
    @Override
    public void addToExcluded(final int bookId, final int userId) 
        throws DecoratorChainException {
        super.addToExcluded(bookId, userId);
    }
}

