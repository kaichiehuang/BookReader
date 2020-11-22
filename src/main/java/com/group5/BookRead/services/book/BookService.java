package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import java.util.List;

public interface BookService {
    boolean remove(int bookId, int userId, String bookshelf);
    boolean addToShelf(MyBook book);
    Bookshelf getShelf(String bookshelf, int userId);
    List<Bookshelf> getBookShelves(int userId);
    List<MyBook> getMyBooks(String bookshelf, int userId);
    MyBook getMyBook(int userId, int bookshelfId, int bookId);
}
