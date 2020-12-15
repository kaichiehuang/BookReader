package com.group5.BookRead.services.bookshelf.BookshelfFactory;

import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.services.bookshelf.BookshelfService;
import com.group5.BookRead.services.bookshelf.ReadBookshelfService;

public class ReadBookshelfServiceCreator extends BookshelfServiceCreator {

    /**
     * @return
     */
    @Override
    public BookshelfService createBookshelfService(
            final BookshelfRepository bookshelfRepo) {
        return new ReadBookshelfService(bookshelfRepo);
    }
}
