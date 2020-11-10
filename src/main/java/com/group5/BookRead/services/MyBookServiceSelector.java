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

    /**
     * remove book from the bookshelf of an uer
     * @param myBookId mybook id (associated with user and bookshelf already)
     * @return MyBook
     */
    public MyBook removeBook(final int myBookId) {
        return myBookService.removeFromShelf(myBookId);
    }

    /**
     * add a book to the shelf of an user
     * @param myBook the book being added (MyBook)
     * @param bookshelfId the bookshelf where the book is added to
     * @return the added book MyBook obejct
     */
    public MyBook addBookToShelf(final MyBook myBook, final int bookshelfId) {
        return myBookService.addToShelf(myBook, bookshelfId);
    }
}
