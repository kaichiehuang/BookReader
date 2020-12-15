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

    public BookServiceDecorator(final BookService service) {
        this.wrappee = service;
    }

    /**
     * @return
     */
    @Override
    public Book save(final Book book)
            throws SQLIntegrityConstraintViolationException {
        return wrappee.save(book);
    }

    /**
     * @return
     */
    @Override
    public Book getBook(final int id) {
        return wrappee.getBook(id);
    }

    /**
     * @return
     */
    @Override
    public Book chooseBook(final Book book) {
        return wrappee.chooseBook(book);
    }

    /**
     * @return
     */
    @Override
    public Book getBookByNameAuthor(final String name, final String author) {
        return wrappee.getBookByNameAuthor(name, author);
    }

    /**
     * @return
     */
    @Override
    public Book getBook(final String identifier) {
        return wrappee.getBook(identifier);
    }

    /**
     * @return
     */
    @Override
    public List<MyBook> getMyBooks(final int userId,
        final int bookId) {
        return wrappee.getMyBooks(userId, bookId);
    }

    /**
     * @return
     */
    @Override
    public MyBook getMyBook(final int userId,
        final int bookshelfId, final int bookId) {
        return wrappee.getMyBook(userId, bookshelfId, bookId);
    }

    /**
     * @return
     */
    @Override
    public double updateProgress(final int userId,
        final int bookId, final double progress) {
        return wrappee.updateProgress(userId, bookId, progress);
    }

    /**
     * @return
     */
    @Override
    public List<Integer> getExcludedBooks(final int userId)
            throws DecoratorChainException {
        return wrappee.getExcludedBooks(userId);
    }

    /**
     * @return
     */
    @Override
    public void addToExcluded(final int bookId, final int userId)
        throws DecoratorChainException {
        wrappee.addToExcluded(bookId, userId);
    }

    /**
     * @return
     */
    @Override
    public Bookshelf getShelf(final String bookshelf, final int userId)
        throws DecoratorChainException {
        return wrappee.getShelf(bookshelf, userId);
    }

    /**
     * @return
     */
    @Override
    public boolean remove(final int bookId,
                          final int userId,
                          final String bookshelf)
                                  throws DecoratorChainException {
        return wrappee.remove(bookId, userId, bookshelf);
    }

    /**
     * @return
     */
    @Override
    public Book remove(final int bookId,
                       final String bookshelf,
                       final int userId) throws DecoratorChainException {
        return wrappee.remove(bookId, bookshelf, userId);
    }

    /**
     * @return
     */
    @Override
    public List<MyBook> getMyBooks(final String bookshelfName,
                                   final int userId)
                                           throws DecoratorChainException {
        return wrappee.getMyBooks(bookshelfName, userId);
    }

    /**
     * @return
     */
    @Override
    public Book save(final Book book,
                    final String bookshelfName,
                    final int userId)
            throws BookExistsOnTragetShelfException, DecoratorChainException {
        return wrappee.save(book, bookshelfName, userId);
    }

    /**
     * @return
     */
    @Override
    public boolean addToShelf(final MyBook book)
            throws DecoratorChainException {
        return wrappee.addToShelf(book);
    }

    /**
     * @return
     */
    @Override
    public List<Book> getBooks(
            final String bookshelfName, final int userId)
                    throws DecoratorChainException {
        return wrappee.getBooks(bookshelfName, userId);
    }

    /**
     * @return
     */
    @Override
    public HashMap<String, List<Book>> getBooksOnBookshelves(
            final int userId) throws DecoratorChainException {
        return wrappee.getBooksOnBookshelves(userId);
    }

    /**
     * @return
     */
    @Override
    public List<Bookshelf> getBookShelves(final int userId)
            throws DecoratorChainException {
        return wrappee.getBookShelves(userId);
    }

    /**
     * @return
     */
    @Override
    public Bookshelf getReadingShelf(
            final int userId, final int bookId)
                    throws DecoratorChainException {
        return wrappee.getReadingShelf(userId, bookId);
    }

    /**
     * @return
     */
    @Override
    public void moveBook(final String srcShelf, final String dstShelf,
        final int userId, final int bookId) throws DecoratorChainException {
        wrappee.moveBook(srcShelf, dstShelf, userId, bookId);
    }

    /**
     * @return
     */
    public Book addBookToShelf(final Book book,
            final String bookshelf, final int userId)
        throws BookExistsOnTragetShelfException, DecoratorChainException {
        return wrappee.addBookToShelf(book, bookshelf, userId);
    }

}
