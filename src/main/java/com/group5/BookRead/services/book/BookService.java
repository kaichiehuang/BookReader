package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;

import java.util.List;

public interface BookService {
    Book remove(int bookId);
    Book add(Book book,
             String bookshelfName,
             int userId);
    Book getBook(int id);
    List<Book> getBooks(String bookshelfName, int userId);
}
