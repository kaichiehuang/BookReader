package com.group5.BookRead.services.book.myBook;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.BookshelfServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class RegularBookHelperService implements BookHelperService {

    private MyBookRepository myBookRepository;
    private BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    public RegularBookHelperService(final BookRepository bookRepository,
                              final  BookshelfServiceSelector bookshelfServiceSelector) {
        this.bookRepository = bookRepository;
        this.bookshelfServiceSelector = bookshelfServiceSelector;
    }

    public MyBook removeFromShelf(final int myBookId) {
        return null;
    }

    public MyBook addToShelf(final MyBook book, final int bookshelfId) {
        return null;
    }

    @Override
    public List<Book> getBooks(String bookshelf, int userId) {
        return null;
    }

    /**
     *  Heleper methods for Book service
     * @param bookshelfName
     * @param userId
     * @return
     */
    @Override
    public List<MyBook> getMyBooks(final String bookshelfName, final int userId) {
        Bookshelf bookshelf = bookshelfServiceSelector.getBookShelf(
                userId,
                bookshelfName);
        return myBookRepository.findAllByUserIdAndShelfId(
                userId,
                bookshelf.getId());
    }

    /**
     * Get all books from all bookshelf, used for initial rendering
     * @param userId
     * @return
     */
    public HashMap<String, List<Book>> getBooksOnBookshelves(final int userId) {
        List<Bookshelf> bookshelves =bookshelfServiceSelector.getBookShelves(userId);
        HashMap<String, List<Book>> map = new HashMap<>();
        for (Bookshelf bookshelf : bookshelves) {
            map.put(bookshelf.getName(),
                    getBooks(bookshelf.getName(), userId));
        }
        return map;
    }

    public MyBook getMyBook(final String user,
                            final String bookshelf,
                            final int bookId) {
        return myBookRepository.findByUsernameAndBookShelfnameAndBookId(
                user,
                bookshelf,
                bookId);
    }


}
