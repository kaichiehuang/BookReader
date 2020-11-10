package com.group5.BookRead.services.bookshelf;

import com.group5.BookRead.repositories.BookshelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "reading")
public class ReadingBookshelfService extends BookshelfService {

    private BookshelfRepository bookshelfRepository;
    @Autowired
    ReadingBookshelfService(final BookshelfRepository bookshelfRepository) {
        super(bookshelfRepository);
        this.bookshelfRepository = bookshelfRepository;
    }
}
