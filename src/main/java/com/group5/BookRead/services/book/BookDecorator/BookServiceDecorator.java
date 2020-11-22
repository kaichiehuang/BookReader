package com.group5.BookRead.services.book.BookDecorator;

import com.group5.BookRead.services.book.BookService;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;

import java.util.List;

public class BookServiceDecorator implements BookService {

    private BookService wrappee;

    public BookServiceDecorator(BookService service){
        this.wrappee = service;
    }

    @Override
    public boolean remove(int bookId, int userId, String bookshelf) {
        return wrappee.remove(bookId, userId, bookshelf);
    }

    @Override
    public boolean addToShelf(MyBook book){
        return wrappee.addToShelf(book);
    }

    @Override
    public Bookshelf getShelf(String bookshelf, int userId){
        return wrappee.getShelf(bookshelf, userId);
    }

    @Override
    public List<Bookshelf> getBookShelves(int userId){
        return wrappee.getBookShelves(userId);
    }

    @Override
    public List<MyBook> getMyBooks(String bookshelf, int userId){
        return wrappee.getMyBooks(bookshelf, userId);
    }

    @Override
    public MyBook getMyBook(int userId, int bookshelfId, int bookId){
        return wrappee.getMyBook(userId, bookshelfId, bookId);
    }
}
