package com.group5.BookRead.services.myBook;

import com.group5.BookRead.models.MyBook;

public interface MyBookService {
    MyBook removeFromShelf(int bookId);
    MyBook addToShelf(MyBook book, int bookshelfId);
}
