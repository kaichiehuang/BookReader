package com.group5.BookRead.services;

import com.group5.BookRead.services.bookAPI.BookAPI;
import com.group5.BookRead.services.bookAPI.BookFromAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExternalBookAPI {
    @Autowired
    BookAPI bookAPI;

    /**
     * set the desired bookAPI
     * @param bookAPI
     */
    public void setBookAPI(final BookAPI bookAPI) {
        this.bookAPI = bookAPI;
    }

    /**
     * get a list of books whose titles match the search words
     * @param search
     * @return a list of books
     */
    public List<BookFromAPI> searchBook(final String search) {
        return bookAPI.getBooks(search);
    }
}
