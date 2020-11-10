package com.group5.BookRead.services.myBook;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookRepository;
import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service(value = "myBook")
public final class RegularMyBookService implements  MyBookService {


    private MyBookRepository myBookRepository;
    private BookRepository bookRepository;
    private BookshelfRepository bookshelfRepository;

    @Autowired
    public RegularMyBookService(final MyBookRepository myBookRepository,
                                final BookRepository bookRepository,
                                final BookshelfRepository bookshelfRepository) {
        this.myBookRepository = myBookRepository;
        this.bookRepository = bookRepository;
        this.bookshelfRepository = bookshelfRepository;
    }

    public MyBook removeFromShelf(final int myBookId) {
        MyBook myBook = myBookRepository.findById(myBookId);
        if (myBookRepository.deleteById(myBookId) == 1) {
            return myBook;
        }
        return null;
    }

    public MyBook addToShelf(final MyBook book, final int bookshelfId) {
        book.setBookshelf_id(bookshelfId);
        try {
            if (myBookRepository.insert(book) == 1) {
                return myBookRepository.findById(
                        book.getBookshelf_id(),
                        book.getUser_id(),
                        book.getBook_id());
            }
            return null;
        } catch (SQLIntegrityConstraintViolationException e) {
            return null;
        }
    }

    /**
     *  get all books of an user
     * @param userId
     * @return a list of books
     */
    @Override
    public List<Book> getBooks(final String bookshelfName, final int userId) {
        List<MyBook> myBooks = getMyBooks(bookshelfName, userId);
        List<Book> books = new ArrayList<>();
        for (MyBook mybook : myBooks) {
            books.add(bookRepository.findById(mybook.getBook_id()));
        }
        return books;
    }

    @Override
    public List<MyBook> getMyBooks(final String bookshelfName, final int userId) {
        Bookshelf bookshelf = bookshelfRepository.findByNameAndUserId(
                bookshelfName,
                userId);
        return myBookRepository.findAllByUserIdAndShelfId(
                userId,
                bookshelf.getId());
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

    public MyBook getMyBook(final String user,
                            final String bookshelf,
                            final int bookId) {
        return myBookRepository.findByUsernameAndBookShelfnameAndBookId(
                user,
                bookshelf,
                bookId);
    }

    
}
