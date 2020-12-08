package com.group5.BookRead.controllers;

import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.book.BookDecorator.ConcreteBookServiceDecorator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

abstract class BookController {
    @Autowired
    BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    BookServiceSelector bookServiceSelector;

    @Autowired
    @Qualifier("basicDecoratedBookService")
    ConcreteBookServiceDecorator bookServiceDecorator;
}
