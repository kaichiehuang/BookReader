package com.group5.BookRead.services;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.services.book.BookExistsOnTragetShelfException;
import com.group5.BookRead.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BookServiceSelector {

    @Autowired
    @Qualifier("book")
    BookService bookService;

    /**
     *  remove a book
     * @param bookId
     * @param bookshelf
     * @param userId
     * @return
     */
    public Book removeBook(final int bookId,
                           final String bookshelf,
                           final int userId) {
        Book book = bookService.remove(bookId, bookshelf, userId);
        return book;
    }

    /**
     * add a book
     * @param book
     * @param bookshelf
     * @param userId
     * @return
     */
    public Book addBookToShelf(final Book book,
                               final String bookshelf,
                               final int userId)
            throws BookExistsOnTragetShelfException {
        // check if book exists in database
        Book curBook = bookService.getBookByNameAuthor(book.getName(),
            book.getAuthor());
        if (curBook == null) {
            // the book does not exist
            curBook = bookService.chooseBook(book);
        }

        // The curBook is the current book
        // now here, we actually need to check if the bookshelf is not favorites
        Book addedBook = bookService.addBookToShelf(curBook, bookshelf, userId);
        if (addedBook != null) {
            return addedBook;
        }
        return null;
    }


    /**
     * get bookshelves and books on the shelves for a user
     * @param userId
     * @return
     */
    public HashMap<String, List<Book>> getBooksFromShelves(final int userId) {
        return bookService.getBooksOnBookshelves(userId);
    }

    /**
     * get all books of an user on one shelf
     * @param bookshelfType
     * @param userId
     * @return
     */
    public List<Book> getBooks(final String bookshelfType, final int userId) {
        return bookService.getBooks(bookshelfType, userId);
    }

}
