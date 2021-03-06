package com.group5.BookRead.controllers;

import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.book.BookDecorator.BookServiceDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

abstract class BookController {
    @Autowired
    BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    @Qualifier("basicDecoratedBookService")
    BookServiceDecorator bookServiceDecorator;
}
