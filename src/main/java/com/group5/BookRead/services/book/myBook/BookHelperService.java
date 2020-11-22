package com.group5.BookRead.services.book.myBook;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import java.util.List;

public interface BookHelperService {
    boolean remove(int bookId, int userId, String bookshelf);
    boolean addToShelf(MyBook book);
    List<MyBook> getMyBooks(String bookshelf, int userId);
    MyBook getMyBook(int userId, int bookshelfId, int bookId);
    Bookshelf getShelf(String bookshelf, int userId);
    List<Bookshelf> getBookShelves(int userId);
}
