package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;

import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;
import java.util.HashMap;



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
    
    Bookshelf getShelf(final String bookshelf, final int userId) throws DecoratorChainException;
    
    boolean remove(final int bookId,
        final int userId,
        final String bookshelf) throws DecoratorChainException;
    Book remove(final int bookId,
        final String bookshelf,
        final int userId) throws DecoratorChainException;
    List<MyBook> getMyBooks(final String bookshelfName,
        final int userId) throws DecoratorChainException;
    Book addBookToShelf(final Book book,
                    final String bookshelfName,
                    final int userId)
            throws BookExistsOnTragetShelfException, DecoratorChainException;
    boolean addToShelf(final MyBook book) 
        throws DecoratorChainException;
    List<Book> getBooks(final String bookshelfName, final int userId) 
        throws DecoratorChainException;
    HashMap<String, List<Book>> getBooksOnBookshelves(final int userId) 
        throws DecoratorChainException;
    List<Bookshelf> getBookShelves(final int userId) 
        throws DecoratorChainException;
    Bookshelf getReadingShelf(final int userId, final int bookId) 
        throws DecoratorChainException;
    void moveBook(final String srcShelf, final String dstShelf,
        final int userId, final int bookId) throws DecoratorChainException;

    List<Integer> getExcludedBooks(int userId) throws DecoratorChainException;
    void addToExcluded(int bookId, int userId) throws DecoratorChainException;
}
