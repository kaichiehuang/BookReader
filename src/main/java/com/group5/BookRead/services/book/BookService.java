package com.group5.BookRead.services.myBook;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.MyBook;

import java.util.HashMap;
import java.util.List;

public interface MyBookService {
    MyBook removeFromShelf(int bookId);
    MyBook addToShelf(MyBook book, int bookshelfId);
    List<Book> getBooks(String bookshelf, int userId);
    List<MyBook> getMyBooks(String bookshelf, int userId);
    HashMap<String, List<Book>> getBooksOnBookshelves(int userId);
}
