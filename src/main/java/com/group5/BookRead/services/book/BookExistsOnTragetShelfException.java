package com.group5.BookRead.services.book;

public class BookExistsOnTragetShelfException extends Exception {
    public BookExistsOnTragetShelfException(final String message) {
        super(message);
    }
}
