package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.MyBook;

public interface BookService {
    Book save(final Book book) throws SQLIntegrityConstraintViolationException;
    Book getBook(final int id);
    Book chooseBook(final Book book);
    Book getBookByNameAuthor(final String name, final String author);
    Book getBook(final String identifier);
    List<MyBook> getMyBooks(final int userId,
        final int bookId);
    MyBook getMyBook(final int userId,
        final int bookshelfId, final int bookId);
    double updateProgress(final int userId,
        final int bookId, final double progress);  
}
