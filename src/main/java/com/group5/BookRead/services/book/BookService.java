package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;

import java.util.HashMap;
import java.util.List;

public interface BookService {
    Book remove(int bookId, String bookshelf, int userId);
    Book addBookToShelf(Book book,
             String bookshelfName,
             int userId);
    Book chooseBook(Book book);
    Book getBook(int id);
    List<Book> getBooks(String bookshelfName, int userId);
    HashMap<String, List<Book>> getBooksOnBookshelves(int userId);
}
