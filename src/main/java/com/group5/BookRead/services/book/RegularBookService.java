package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.services.book.excludedBook.ExcludedBookService;
import com.group5.BookRead.services.book.myBook.BookHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Service(value = "book")
public final class RegularBookService implements BookService {

    private BookRepository bookRepository;
    private BookHelperService bookHelperService;
    private ExcludedBookService excludedBookService;


    @Autowired
    public RegularBookService(final BookRepository bookRepository,
                              final BookHelperService bookHelperService,
                              final ExcludedBookService excludedBookService) {
        this.bookRepository = bookRepository;
        this.bookHelperService = bookHelperService;
        this.excludedBookService = excludedBookService;
    }


    @Override
    public Book remove(final int bookId,
                       final String bookshelf,
                       final int userId) {
        Book book = getBook(bookId);
        if (bookHelperService.remove(bookId, userId, bookshelf)) {
            return book;
        }
        return null;
    }

    /**
     * get the book with all information including author, description
     * @param id
     * @return
     */
    @Override
    public Book getBook(final int id) {
        return bookRepository.findById(id);
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

        Bookshelf bookShelf = bookHelperService.getShelf(
                bookshelfName,
                userId);
        MyBook existing = bookHelperService.getMyBook(
                bookShelf.getId(),
                userId,
                book.getId());
            
        // check if there are duplicates in same shelf
        if (existing != null) {
            // exists
            throw new BookExistsOnTragetShelfException(
                    book.getName() + " exists on " + bookshelfName);
        }

        //TODO: please refactor by using bookshelf subclasses
        // used for mutual excluse want to read, reading, and read bookshelf
        // i.e. only one can be in these three shelves
        if (bookshelfName.equals("want to read") 
            || bookshelfName.equals("read") 
            || bookshelfName.equals("reading")){
            List<Bookshelf> shelves = bookHelperService.getBookShelves(userId);
        
            for (Bookshelf shelf : shelves) {
                if (shelf.getName().equals("want to read") 
                    || shelf.getName().equals("read") 
                    || shelf.getName().equals("reading")) {
                    MyBook curBook = bookHelperService.getMyBook(
                            userId,
                            shelf.getId(),
                            book.getId());
                    
                    if (curBook != null) {
                        throw new BookExistsOnTragetShelfException(
                                book.getName() + " exists on " 
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
        if (bookHelperService.addToShelf(myBook)) {
            return book;
        }
        return null;
    }

    /**
     *  add the book to the Book repository
     * @param book
     * @return
     */
    @Override
    public Book chooseBook(final Book book) {
        try {
            int res = bookRepository.insert(book);
            if (res == 1) {
                return bookRepository.findByNameAndAuthor(
                        book.getName(),
                        book.getAuthor());
            }

            return null;
        } catch (SQLIntegrityConstraintViolationException exception) {
            return null;
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
        List<MyBook> myBooks = bookHelperService.getMyBooks(
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
    public HashMap<String, List<Book>> getBooksOnBookshelves(final int userId) {
        List<Bookshelf> bookshelves = bookHelperService.getBookShelves(userId);
        // System.out.println(bookshelves.size());
        HashMap<String, List<Book>> map = new HashMap<>();
        for (Bookshelf bookshelf : bookshelves) {
            map.put(bookshelf.getName(),
                    getBooks(bookshelf.getName(), userId));
        }
        return map;
    }

    /**
     * Get book with same name and author
     * @param name book name
     * @param author book author
     * @return book object
     */
    @Override
    public Book getBookByNameAuthor(final String name, final String author) {
        return bookRepository.findByNameAndAuthor(name, author);
    }


    /**
     * Get excluded book list of the user
     * @param userId
     * @return excluded book list
     */
    @Override
    public List<Integer> getExcludedBooks(final int userId) {
        return excludedBookService.getExcludedBooks(userId);
    }


    /**
     * Add book to user's excluded list
     * @param bookId
     * @param userId
     */
    @Override
    public void addToExcluded(final int bookId, final int userId) {
        excludedBookService.addToExcluded(bookId, userId);
    }
}

