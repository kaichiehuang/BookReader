package com.group5.BookRead.services.bookshelf;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.repositories.BookshelfRepository;
import org.springframework.beans.factory.annotation.Autowired;

public final class RecommendBookshelfService implements BookshelfService {
    private BookshelfRepository bookshelfRepository;

    @Autowired
    public RecommendBookshelfService(final BookshelfRepository bookshelfRepository) {
        this.bookshelfRepository = bookshelfRepository;
    }

    /**
     *  Find the Bookshelf object based onthe type and the onwer
     * @param bookshelf bookshelf type
     * @param user username
     * @return BookShelf object
     */
    @Override
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
    @Override
    public Bookshelf findBookshelf(final String bookshelf, final int user) {
        Bookshelf shelf = bookshelfRepository.findByBookshelfNameAndUserId(
            bookshelf,
            user);
        return shelf;
    }

    /**
     *  find all bookshelves of an given user
     * @param userId
     * @return a list of bookshelves
     */
    @Override
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
    @Override
    public boolean create(final String bookshelf, final int userId) {
        Bookshelf shelf = new Bookshelf(userId, bookshelf);
        try {
            return bookshelfRepository.insert(shelf) == 1;
        } catch (SQLIntegrityConstraintViolationException exception) {
            return false;
        }
    }
}
