package com.group5.BookRead.controllers;

import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.book.myBook.BookHelperService;

import org.springframework.beans.factory.annotation.Autowired;

abstract class BookController {
    @Autowired
    BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    BookServiceSelector bookServiceSelector;

    @Autowired
    BookHelperService bookHelperService;
}
