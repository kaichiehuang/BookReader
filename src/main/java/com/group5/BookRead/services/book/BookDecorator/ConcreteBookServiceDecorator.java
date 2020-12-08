package com.group5.BookRead.services.book.BookDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLIntegrityConstraintViolationException;
import com.group5.BookRead.services.book.BookExistsOnTragetShelfException;
import org.springframework.stereotype.Component;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.services.book.BookService;

import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

@Component
public final class ConcreteBookServiceDecorator extends BookServiceDecorator {
    
    private BookRepository bookRepository;
    
    @Autowired
    public ConcreteBookServiceDecorator(
        final BookRepository bookRepository,
        final BookService service) {
        super(service);
        this.bookRepository = bookRepository;
    }

    public Book remove(final int bookId,
                       final String bookshelf,
                       final int userId) {
        Book book = getBook(bookId);
        if (super.remove(bookId, userId, bookshelf)) {
            return book;
        }
        return null;
    }

    /**
     * get the book with all information including author, description
     * @param id
     * @return
     */
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
    public Book addBookToShelf(final Book book,
                    final String bookshelfName,
                    final int userId)
            throws BookExistsOnTragetShelfException {

        Bookshelf bookShelf = super.getShelf(
                bookshelfName,
                userId);
        MyBook existing = super.getMyBook(
                bookShelf.getId(),
                userId,
                book.getId());

        if (existing != null) {
            // exists
            throw new BookExistsOnTragetShelfException(
                    book.getName() + " exists on " + bookshelfName);
        }

        List<Bookshelf> shelves = super.getBookShelves(userId);
        for (Bookshelf shelf : shelves) {
            if (!shelf.getName().equals(bookshelfName)) {
                MyBook curBook = super.getMyBook(
                        userId,
                        shelf.getId(),
                        book.getId());
                if (curBook != null
                        && (shelf.getName().equals("favorites")
                        || bookshelfName.equals("favorites"))) {
                    throw new BookExistsOnTragetShelfException(
                            book.getName() + " exists on " + shelf.getName());
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
        if (super.addToShelf(myBook)) {
            return book;
        }
        return null;
    }

    /**
     *  add the book to the Book repository
     * @param book
     * @return
     */
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
    public List<Book> getBooks(final String bookshelfName, final int userId) {
        List<MyBook> myBooks = super.getMyBooks(
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
        List<Bookshelf> bookshelves = super.getBookShelves(userId);
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
    public Book getBookByNameAuthor(final String name, final String author) {
        return bookRepository.findByNameAndAuthor(name, author);
    }
}

