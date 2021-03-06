package com.group5.BookRead.services.bookshelf.BookshelfFactory;

import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.services.bookshelf.BookshelfService;
import com.group5.BookRead.services.bookshelf.RecommendBookshelfService;

public class RecommendBookshelfServiceCreator extends BookshelfServiceCreator {

    /**
     * @return
     */
    @Override
    public BookshelfService createBookshelfService(
            final BookshelfRepository bookshelfRepo) {
        return new RecommendBookshelfService(bookshelfRepo);
    }
}
