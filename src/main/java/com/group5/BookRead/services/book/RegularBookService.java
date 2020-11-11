package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.services.book.myBook.BookHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service(value = "book")
public final class RegularBookService implements BookService {

    private BookRepository bookRepository;
    private BookHelperService bookHelperService;


    @Autowired
    public RegularBookService(final BookRepository bookRepository,
                              final BookHelperService bookHelperService) {
        this.bookRepository = bookRepository;
        this.bookHelperService = bookHelperService;
    }


    @Override
    public Book remove(final int bookId,
                       final String bookshelf,
                       final int userId) {
        Book book = getBook(bookId);
        if (bookHelperService.remove(userId, bookId, bookshelf)) {
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
                    final int userId) {

        Bookshelf bookShelf = bookHelperService.getShelf(
                bookshelfName,
                userId);

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
            if (bookRepository.insert(book) == 1) {
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
        List<Book> books = new ArrayList<>();
        for (MyBook mybook : myBooks) {
            books.add(bookRepository.findById(mybook.getBookId()));
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
        System.out.println(bookshelves.size());
        HashMap<String, List<Book>> map = new HashMap<>();
        for (Bookshelf bookshelf : bookshelves) {
            map.put(bookshelf.getName(),
                    getBooks(bookshelf.getName(), userId));
        }
        return map;
    }
}

