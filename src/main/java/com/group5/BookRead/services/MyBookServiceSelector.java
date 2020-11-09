package com.group5.BookRead.services;

import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.services.myBook.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyBookServiceSelector {

    @Autowired
    @Qualifier("myBook")
    MyBookService myBookService;

    MyBook removeBook(int myBookId) {
        return myBookService.removeFromShelf(myBookId);
    }

    MyBook addBookToShelf(MyBook myBook, int desBookshelfId) {
        return myBookService.addToShelf(myBook, desBookshelfId);
    }
}
