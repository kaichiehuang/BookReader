package com.group5.BookRead.services.friend;

public class AlreadyRequestedException extends Exception {
    public AlreadyRequestedException(final String message) {
        super(message);
    }
}
