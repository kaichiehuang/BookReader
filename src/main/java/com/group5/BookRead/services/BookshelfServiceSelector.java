package com.group5.BookRead.services;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.repositories.UserRepository;
import com.group5.BookRead.services.bookshelf.BookshelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
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


    private BookshelfService getService(String type) {
        if (type.equals("read") || type.equals("default")) return readBookshelfService;
        else if (type.equals("reading")) return  readingBookshelfService;
        return favoriteBookshelfService;
    }

    Bookshelf getBookShelf(String username, String bookshelfName) {
        BookshelfService bookshelfService = getService(bookshelfName);
        return bookshelfService.findBookshelf(bookshelfName, username);
    }


}
