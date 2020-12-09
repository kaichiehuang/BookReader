package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;
import java.util.HashMap;



public interface BookService {
    Book save(Book book) throws SQLIntegrityConstraintViolationException;
    Book getBook(int id);
    Book chooseBook(Book book);
    Book getBookByNameAuthor(String name, String author);
    Book getBook(String identifier);
    List<MyBook> getMyBooks(int userId,
        int bookId);
    MyBook getMyBook(int userId,
        int bookshelfId, int bookId);
    double updateProgress(int userId,
        int bookId, double progress);
    
    Bookshelf getShelf(String bookshelf, int userId) throws DecoratorChainException;
    
    boolean remove(int bookId,
        int userId,
        String bookshelf) throws DecoratorChainException;
    Book remove(int bookId,
        String bookshelf,
        int userId) throws DecoratorChainException;
    List<MyBook> getMyBooks(String bookshelfName,
        int userId) throws DecoratorChainException;
    Book addBookToShelf(Book book,
                    String bookshelfName,
                    int userId)
            throws BookExistsOnTragetShelfException, DecoratorChainException;
    boolean addToShelf(MyBook book) 
        throws DecoratorChainException;
    List<Book> getBooks(String bookshelfName, int userId) 
        throws DecoratorChainException;
    HashMap<String, List<Book>> getBooksOnBookshelves(int userId) 
        throws DecoratorChainException;
    List<Bookshelf> getBookShelves(int userId) 
        throws DecoratorChainException;
    Bookshelf getReadingShelf(int userId, int bookId) 
        throws DecoratorChainException;
    void moveBook(String srcShelf, String dstShelf,
        int userId, int bookId) throws DecoratorChainException;

    List<Integer> getExcludedBooks(int userId) throws DecoratorChainException;
    void addToExcluded(int bookId, int userId) throws DecoratorChainException;
}
