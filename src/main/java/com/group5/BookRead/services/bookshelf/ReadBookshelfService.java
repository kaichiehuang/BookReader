package com.group5.BookRead.services.bookshelf;

import com.group5.BookRead.repositories.BookshelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "read")
public final class ReadBookshelfService extends BookshelfService {

    private BookshelfRepository bookshelfRepository;
    @Autowired
    ReadBookshelfService(final BookshelfRepository bookshelfRepository) {
        super(bookshelfRepository);
        this.bookshelfRepository = bookshelfRepository;
    }


}
