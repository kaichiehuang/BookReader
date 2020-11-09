package com.group5.BookRead.services.bookshelf;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.repositories.BookshelfRepository;

public abstract class BookshelfService {

    BookshelfRepository bookshelfRepository;
    BookshelfService(BookshelfRepository bookshelfRepository) {
        this.bookshelfRepository = bookshelfRepository;
    }

    public Bookshelf findBookshelf(String bookshelfName, String username) {
        return bookshelfRepository.findByBookshelfNameAndUsername(bookshelfName, username);
    }

}
