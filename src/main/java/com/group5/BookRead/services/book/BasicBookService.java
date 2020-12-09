package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;


import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.HashMap;


public final class BasicBookService implements BookService {
    private BookRepository bookRepository;
    private MyBookRepository myBookRepository;

    @Autowired
    public BasicBookService(
        final BookRepository bookRepository,
        final MyBookRepository myBookRepository
    ) {
        this.bookRepository = bookRepository;
        this.myBookRepository = myBookRepository;
    }

    @Override
    public Book save (final Book book) 
        throws SQLIntegrityConstraintViolationException {
        bookRepository.insert(book);
        return bookRepository.findByIdentifier(book.getBookIdentifier());
    }

    /**
     * get the book with all information including author, description
     * @param id
     * @return
     */
    public Book getBook(final int id) {
        return bookRepository.findById(id);
    }

    /**
     *  add the book to the Book repository
     * @param book
     * @return
     */
    public Book chooseBook(final Book book) {
        try {
            int res = bookRepository.insert(book);
            if (res == 1) {
                return bookRepository.findByNameAndAuthor(
                        book.getTitle(),
                        book.getAuthor());
            }

            return null;
        } catch (SQLIntegrityConstraintViolationException exception) {
            return null;
        }
    }

    /**
     * Get book with same name and author
     * @param name book name
     * @param author book author
     * @return book object
     */
    public Book getBookByNameAuthor(final String name, final String author) {
        return bookRepository.findByNameAndAuthor(name, author);
    }
    

    @Override
    public Book getBook(final String identifier) {
        System.out.println(identifier);
        return bookRepository.findByIdentifier(identifier);
    }

    /**
     * get all MyBook object with user and book
     * @param bookId
     * @param userId
     * @return List of mybook
     */
    @Override
    public List<MyBook> getMyBooks(final int userId,
                            final int bookId) {
        return myBookRepository.findAllMybooks(userId, bookId);
    }


    @Override
    public MyBook getMyBook(final int userId,
                            final int bookshelfId,
                            final int bookId) {
        return myBookRepository.findByAllIds(bookId, userId, bookshelfId);
    }

    // need to move in decorator
    @Override
    public double updateProgress(final int userId,
        final int bookId, final double progress) {

        List<MyBook> mybooks = this.getMyBooks(userId, bookId);
        for (MyBook b : mybooks) {
            b.setProgress(progress);
            myBookRepository.update(b);
        }
        return progress;
    }

    @Override
    public List<Integer> getExcludedBooks(final int userId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public void addToExcluded(final int bookId, final int userId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public Bookshelf getShelf(final String bookshelf, final int userId) 
        throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }
    
    @Override
    public boolean remove(final int bookId,
                          final int userId,
                          final String bookshelf) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public Book remove(final int bookId,
                       final String bookshelf,
                       final int userId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }
    
    @Override
    public List<MyBook> getMyBooks(final String bookshelfName,
                                   final int userId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public Book addBookToShelf(final Book book,
                    final String bookshelfName,
                    final int userId)
            throws BookExistsOnTragetShelfException, DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public boolean addToShelf(final MyBook book) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public List<Book> getBooks(final String bookshelfName, final int userId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public HashMap<String, List<Book>> getBooksOnBookshelves(final int userId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public List<Bookshelf> getBookShelves(final int userId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public Bookshelf getReadingShelf(final int userId, final int bookId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }

    @Override
    public void moveBook(final String srcShelf, final String dstShelf,
        final int userId, final int bookId) throws DecoratorChainException{
        throw new DecoratorChainException(
            "Error: decorator is not correctly chained. "  
            + "Please check decorator declaration."
        );
    }
}
