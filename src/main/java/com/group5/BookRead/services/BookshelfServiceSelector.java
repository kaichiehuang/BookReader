package com.group5.BookRead.services;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.services.bookshelf.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BookshelfServiceSelector {

    @Autowired
    @Qualifier("reading")
    BookshelfService readingBookshelfService;

    @Autowired
    @Qualifier("read")
    BookshelfService readBookshelfService;

    @Autowired
    @Qualifier("favorites")
    BookshelfService favoriteBookshelfService;

    @Autowired
    @Qualifier("recommend")
    BookshelfService recommendBookshelfService;

    @Autowired
    @Qualifier("want-to-read")
    BookshelfService wantToReadBookshelfService;

    @Autowired
    @Qualifier("myshelf")
    BookshelfService regularBookshelfService;


    private BookshelfService getService(final String type) {
        if (type.equals("read")) {
            return readBookshelfService;
        } else if (type.equals("reading")) {
            return readingBookshelfService;
        } else if (type.equals("want-to-read")) {
            return wantToReadBookshelfService;
        } else if (type.equals("recommend")) {
            return recommendBookshelfService;
        } else if (type.equals("favorites")) {
            return favoriteBookshelfService;
        }
        return regularBookshelfService;
    }

    /**  get the bookshelf of type (bookshelfName) for user
     * @param username
     * @param bookshelfName
     * @return bookshelf
     */
    public Bookshelf getBookShelf(final String username,
                                  final String bookshelfName) {
        BookshelfService bookshelfService = getService(bookshelfName);
        return bookshelfService.findBookshelf(bookshelfName, username);
    }

    /**
     * get the bookshelf of type (bookshelfName) for user
     * @param userId
     * @param bookshelfName
     * @return
     */
    public Bookshelf getBookShelf(final int userId,
                                  final String bookshelfName) {
        BookshelfService bookshelfService = getService(bookshelfName);
        Bookshelf shelf = bookshelfService.findBookshelf(bookshelfName, userId);
        return shelf;
    }

    /**
     *  get bookshelves of an user
     * @param userId
     * @return
     */
    public List<Bookshelf> getBookShelves(final int userId) {
        return getService("myshelf").findBookshelves(userId);
    }

    /**
     *  cretae shelf
     * @param bookshelf
     * @param userId
     * @return
     */
    public boolean create(final String bookshelf, final int userId) {
        return getService(bookshelf).create(bookshelf, userId);
    }


}
