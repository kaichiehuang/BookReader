package com.group5.BookRead.services.bookshelf;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.repositories.BookshelfRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public abstract class BookshelfService {

    private BookshelfRepository bookshelfRepository;
    BookshelfService(final BookshelfRepository bookshelfRepository) {
        this.bookshelfRepository = bookshelfRepository;
    }

    /**
     *  Find the Bookshelf object based onthe type and the onwer
     * @param bookshelf bookshelf type
     * @param user username
     * @return BookShelf object
     */
    public Bookshelf findBookshelf(final String bookshelf, final String user) {
        return bookshelfRepository.findByBookshelfNameAndUsername(
                bookshelf,
                user);
    }

    /**
     * Find the Bookshelf object based onthe type and the onwer
     * @param bookshelf
     * @param user
     * @return
     */
    public Bookshelf findBookshelf(final String bookshelf, final int user) {
        return bookshelfRepository.findByBookshelfNameAndUserId(
                bookshelf,
                user);
    }

    /**
     *  find all bookshelves of an given user
     * @param userId
     * @return a list of bookshelves
     */
    public List<Bookshelf> findBookshelves(final int userId) {
        //System.out.println(bookshelfRepository.findAllByUserId(userId));
        return bookshelfRepository.findAllByUserId(userId);
    }

    /**
     *  create a new shelf
     * @param bookshelf
     * @param userId
     * @return
     */
    public boolean create(final String bookshelf, final int userId) {
        Bookshelf shelf = new Bookshelf(userId, bookshelf);
        try {
            return bookshelfRepository.insert(shelf) == 1;
        } catch (SQLIntegrityConstraintViolationException exception) {
            return false;
        }
    }
}

