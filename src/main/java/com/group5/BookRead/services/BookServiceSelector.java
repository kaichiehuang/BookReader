package com.group5.BookRead.services;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.services.book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class BookServiceSelector {

    @Autowired
    BookService bookService;

    /**
     *  remove a book
     * @param bookId
     * @param bookshelf
     * @param userId
     * @return
     */
    public Book removeBook(final int bookId, final String bookshelf, final int userId) {
        return null;
    }

    /**
     *  add a book
     * @param myBook
     * @param bookshelfId
     * @return
     */
    public MyBook addBookToShelf(final MyBook myBook, final int bookshelfId) {
        return null;
    }


    /**
     * get bookshelves and books on the shelves for a user
     * @param userId
     * @return
     */
    public HashMap<String, List<Book>> getBooksFromShelves(final int userId) {
        return null;
    }

    /**
     * get all books of an user on one shelf
     * @param bookshelfType
     * @param userId
     * @return
     */
    public List<Book> getBooks(final String bookshelfType, final int userId) {
        return null;
    }

}
