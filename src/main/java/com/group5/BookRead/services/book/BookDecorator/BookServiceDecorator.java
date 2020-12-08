package com.group5.BookRead.services.book.BookDecorator;

import java.sql.SQLIntegrityConstraintViolationException;

import com.group5.BookRead.services.book.BookService;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;

import java.util.List;

public class BookServiceDecorator implements BookService {

    private BookService wrappee;

    public BookServiceDecorator(BookService service){
        this.wrappee = service;
    }

    @Override
    public Book save(final Book book) throws SQLIntegrityConstraintViolationException {
        return wrappee.save(book);
    }

    @Override
    public Book getBook(final int id){
        return wrappee.getBook(id);
    }

    @Override
    public Book chooseBook(final Book book){
        return wrappee.chooseBook(book);
    }

    @Override
    public Book getBookByNameAuthor(final String name, final String author){
        return wrappee.getBookByNameAuthor(name, author);
    }

    @Override
    public Book getBook(final String identifier){
        return wrappee.getBook(identifier);
    }

    @Override
    public List<MyBook> getMyBooks(final int userId,
        final int bookId){
        return wrappee.getMyBooks(userId, bookId);
    }

    @Override
    public MyBook getMyBook(final int userId,
        final int bookshelfId, final int bookId){
        return wrappee.getMyBook(userId, bookshelfId, bookId);
    }

    @Override
    public double updateProgress(final int userId,
        final int bookId, final double progress){
        return wrappee.updateProgress(userId, bookId, progress);
    }  
}
