package com.group5.BookRead.services.bookshelf;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.repositories.BookshelfRepository;

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

}

