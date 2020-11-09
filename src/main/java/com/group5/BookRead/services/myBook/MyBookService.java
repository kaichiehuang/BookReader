package com.group5.BookRead.services.myBook;

import com.group5.BookRead.models.MyBook;

public interface MyBookService {

    public MyBook removeFromShelf(int bookId);
    public MyBook addToShelf(MyBook book, int bookshelfId);

}
