package com.group5.BookRead.services.bookshelf;
import com.group5.BookRead.models.Bookshelf;
import java.util.List;

public interface BookshelfService {

    /**
     *  Find the Bookshelf object based onthe type and the onwer
     * @param bookshelf bookshelf type
     * @param user username
     * @return BookShelf object
     */
    Bookshelf findBookshelf(String bookshelf, String user);

    /**
     * Find the Bookshelf object based onthe type and the onwer
     * @param bookshelf
     * @param user
     * @return
     */
    Bookshelf findBookshelf(String bookshelf, int user);

    /**
     *  find all bookshelves of an given user
     * @param userId
     * @return a list of bookshelves
     */
    List<Bookshelf> findBookshelves(int userId);

    /**
     *  create a new shelf
     * @param bookshelf
     * @param userId
     * @return
     */
    boolean create(String bookshelf, int userId);
}

