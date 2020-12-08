package com.group5.BookRead.services.book.BookDecorator;

import java.sql.SQLIntegrityConstraintViolationException;
import com.group5.BookRead.services.book.DecoratorChainException;
import com.group5.BookRead.services.book.BookExistsOnTragetShelfException;

import com.group5.BookRead.services.book.BookService;
import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;

import java.util.List;
import java.util.HashMap;

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

    @Override
    public List<Integer> getExcludedBooks(int userId) throws DecoratorChainException {
        return wrappee.getExcludedBooks(userId);
    }

    @Override
    public void addToExcluded(int bookId, int userId) throws DecoratorChainException {
        wrappee.addToExcluded(bookId, userId);
    }

    @Override
    public Bookshelf getShelf(final String bookshelf, final int userId) 
        throws DecoratorChainException{
        return wrappee.getShelf(bookshelf, userId);
    }
    
    @Override
    public boolean remove(final int bookId,
                          final int userId,
                          final String bookshelf) throws DecoratorChainException{
        return wrappee.remove(bookId, userId, bookshelf);
    }

    @Override
    public Book remove(final int bookId,
                       final String bookshelf,
                       final int userId) throws DecoratorChainException{
        return wrappee.remove(bookId, bookshelf, userId);
    }
    
    @Override
    public List<MyBook> getMyBooks(final String bookshelfName,
                                   final int userId) throws DecoratorChainException{
        return wrappee.getMyBooks(bookshelfName, userId);
    }

    @Override
    public Book addBookToShelf(final Book book,
                    final String bookshelfName,
                    final int userId)
            throws BookExistsOnTragetShelfException, DecoratorChainException{
        return wrappee.addBookToShelf(book, bookshelfName, userId);
    }

    @Override
    public boolean addToShelf(final MyBook book) throws DecoratorChainException{
        return wrappee.addToShelf(book);
    }

    @Override
    public List<Book> getBooks(final String bookshelfName, final int userId) throws DecoratorChainException{
        return wrappee.getBooks(bookshelfName, userId);
    }

    @Override
    public HashMap<String, List<Book>> getBooksOnBookshelves(final int userId) throws DecoratorChainException{
        return wrappee.getBooksOnBookshelves(userId);
    }

    @Override
    public List<Bookshelf> getBookShelves(final int userId) throws DecoratorChainException{
        return wrappee.getBookShelves(userId);
    }

    @Override
    public Bookshelf getReadingShelf(final int userId, final int bookId) throws DecoratorChainException{
        return wrappee.getReadingShelf(userId, bookId);
    }

    @Override
    public void moveBook(final String srcShelf, final String dstShelf,
        final int userId, final int bookId) throws DecoratorChainException{
        wrappee.moveBook(srcShelf, dstShelf, userId, bookId);
    }
}
