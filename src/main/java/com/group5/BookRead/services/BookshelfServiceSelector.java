package com.group5.BookRead.services;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.services.bookshelf.BookshelfService;
import com.group5.BookRead.services.bookshelf.BookshelfFactory.BookshelfServiceCreator;
import com.group5.BookRead.services.bookshelf.BookshelfFactory.FavoriteBookshelfServiceCreator;
import com.group5.BookRead.services.bookshelf.BookshelfFactory.ReadBookshelfServiceCreator;
import com.group5.BookRead.services.bookshelf.BookshelfFactory.ReadingBookshelfServiceCreator;
import com.group5.BookRead.services.bookshelf.BookshelfFactory.RecommendBookshelfServiceCreator;
import com.group5.BookRead.services.bookshelf.BookshelfFactory.RegularBookshelfServiceCreator;
import com.group5.BookRead.services.bookshelf.BookshelfFactory.WantToReadBookshelfServiceCreator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class BookshelfServiceSelector {

    private static BookshelfServiceCreator bookshelfServiceCreator;

    @Autowired
    private BookshelfRepository bookshelfRepo;

    @Autowired
    private MyBookRepository myBookRepository;

    private BookshelfService getService(final String type) {
        if (type.equals("read")) {
            bookshelfServiceCreator = new ReadBookshelfServiceCreator();
        } else if (type.equals("reading")) {
            bookshelfServiceCreator = new ReadingBookshelfServiceCreator();
        } else if (type.equals("want-to-read")) {
            bookshelfServiceCreator = new WantToReadBookshelfServiceCreator();
        } else if (type.equals("recommend")) {
            bookshelfServiceCreator = new RecommendBookshelfServiceCreator();
        } else if (type.equals("favorites")) {
            bookshelfServiceCreator = new FavoriteBookshelfServiceCreator();
        } else {
            bookshelfServiceCreator = new RegularBookshelfServiceCreator();
        }
        return bookshelfServiceCreator.createBookshelfService(bookshelfRepo);
    }

//    /**  get the bookshelf of type (bookshelfName) for user
//     * @param username
//     * @param bookshelfName
//     * @return bookshelf
//     */
//    public Bookshelf getBookShelf(final String username,
//                                  final String bookshelfName) {
//        BookshelfService bookshelfService = getService(bookshelfName);
//        return bookshelfService.findBookshelf(bookshelfName, username);
//    }

    /**
     * get the bookshelf of type (bookshelfName) for user
     * @param userId
     * @param bookshelfName
     * @return
     */
    public Bookshelf getBookShelf(final int userId,
                                  final String bookshelfName) {
        BookshelfService bookshelfService = getService(bookshelfName);
        Bookshelf shelf = bookshelfService.findBookshelf(bookshelfName, userId);
        return shelf;
    }


    /**
     *  get bookshelves of an user
     * @param userId
     * @return
     */
    public List<Bookshelf> getBookShelves(final int userId) {
        return getService("myshelf").findBookshelves(userId);
    }

    /**
     *  cretae shelf
     * @param bookshelf
     * @param userId
     * @return
     */
    public boolean create(final String bookshelf, final int userId) {
        return getService(bookshelf).create(bookshelf, userId);
    }

    public boolean removeBook(final int bookId, final int userId,
                              final String bookshelf) {
        Bookshelf shelf = getBookShelf(userId, bookshelf);
        return myBookRepository.deleteByUserIdAndBookshelfIdAndBookId(
                userId, shelf.getId(), bookId) == 1;
    }

    public List<MyBook> getBooksOnShelf(final String bookshelfName, final int userId) {
        Bookshelf bookshelf = getBookShelf(
                userId,
                bookshelfName);
        return myBookRepository.findAllByUserIdAndShelfId(
                userId,
                bookshelf.getId());
    }

}
