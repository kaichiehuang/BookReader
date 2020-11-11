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


    private BookshelfService getService(final String type) {
        if (type.equals("read") || type.equals("default")) {
            return readBookshelfService;
        } else if (type.equals("reading")) {
            return readingBookshelfService;
        }
        return favoriteBookshelfService;
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
        return bookshelfService.findBookshelf(bookshelfName, userId);
    }

    /**
     *  get bookshelves of an user
     * @param userId
     * @return
     */
    public List<Bookshelf> getBookShelves(final int userId) {
        return getService("defualt").findBookshelves(userId);
    }


}
