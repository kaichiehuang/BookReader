package com.group5.BookRead.services.bookshelf.BookshelfFactory;

import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.services.bookshelf.BookshelfService;

public abstract class BookshelfServiceCreator {
    public abstract BookshelfService
        createBookshelfService(BookshelfRepository bookshelfRepo);
}
