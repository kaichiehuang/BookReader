package com.group5.BookRead.services.book;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.book.myBook.BookHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service(value = "book")
public final class RegularBookService implements BookService {

    private BookRepository bookRepository;
    private BookHelperService bookHelperService;

    @Autowired
    public RegularBookService(final BookRepository bookRepository,
                              final BookHelperService bookHelperService) {
        this.bookRepository = bookRepository;
        this.bookHelperService = bookHelperService;
    }


    @Override
    public Book remove(final int bookId) {
        Book book = getBook(bookId);
        if (bookRepository.deleteById(bookId) == 1) {
            return book;
        }
        return null;
    }

    /**
     * get the book with all information including author, description
     * @param id
     * @return
     */
    @Override
    public Book getBook(final int id) {
        return bookRepository.findById(id);
    }

    /**
     *
     * @param book
     * @param bookshelfName
     * @param userId
     * @return
     */
    @Override
    public Book add(final Book book,
                    final String bookshelfName,
                    final int userId) {
        try {

            if (bookRepository.insert(book) == 1) {
                Book stored = bookRepository.findByNameAndAuthor(
                        book.getName(),
                        book.getAuthor());
                Bookshelf bookShelf = bookshelfServiceSelector.getBookShelf(
                        userId,
                        bookshelfName);
                MyBook myBook = new MyBook(stored.getId(),
                        userId,
                        bookShelf.getId(),
                        0);
                bookServiceSelector.addBookToShelf(myBook, bookShelf.getId());
                return book;
            }
            return null;
        } catch (SQLIntegrityConstraintViolationException exception) {
            return null;
        }
    }


    /**
     * get all Books with inforamtion for a user on a bookshelf
     * @param bookshelfName
     * @param userId
     * @return
     */
    @Override
    public List<Book> getBooks(final String bookshelfName, final int userId) {
        List<MyBook> myBooks = bookServiceSelector.getAllBooks(
                bookshelfName,
                userId);
        List<Book> books = new ArrayList<>();
        for (MyBook mybook : myBooks) {
            books.add(bookRepository.findById(mybook.getBook_id()));
        }
        return books;
    }

    /**
     * Get all books from all bookshelf, used for initial rendering
     * @param userId
     * @return
     */
    public HashMap<String, List<Book>> getBooksOnBookshelves(final int userId) {
        List<Bookshelf> bookshelves = bookshelfRepository.findAllByUserId(userId);
        HashMap<String, List<Book>> map = new HashMap<>();
        for (Bookshelf bookshelf : bookshelves) {
            map.put(bookshelf.getName(),
                    getBooks(bookshelf.getName(), userId));
        }
        return map;
    }
}

