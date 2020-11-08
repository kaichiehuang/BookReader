package com.group5.BookRead;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public final class BookReadApplication {
    /**
     * Don't let anyone instantiate this class.
     */
    private BookReadApplication() { }

    public static void main(final String[] args) {
        SpringApplication.run(BookReadApplication.class, args);
    }

}
