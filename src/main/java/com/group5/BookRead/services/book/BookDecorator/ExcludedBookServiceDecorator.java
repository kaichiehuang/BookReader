package com.group5.BookRead.services.book.BookDecorator;

import com.group5.BookRead.models.ExcludedBook;
import com.group5.BookRead.repositories.ExcludedBookRepository;
import com.group5.BookRead.repositories.UserRepository;
import com.group5.BookRead.services.book.BookService;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class ExcludedBookServiceDecorator extends BookServiceDecorator  {
    private ExcludedBookRepository excludedBookRepository;

    @Autowired
    public ExcludedBookServiceDecorator (
        final BookService service,
        final ExcludedBookRepository excludedBookRepository) {
        super(service);
        this.excludedBookRepository = excludedBookRepository;
    }

    @Override
    public List<Integer> getExcludedBooks(final int userId) {
        List<ExcludedBook> excluded =
                excludedBookRepository.findExcludedByUserId(userId);
        if (excluded == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        for (ExcludedBook book: excluded) {
            result.add(book.getBookId());
        }
        return result;
    }

    @Override
    public void addToExcluded(final int bookId, final int userId) {
        int id = excludedBookRepository.findIdByOtherIds(bookId, userId);
        if (id != -1) {
            return;
        }
        ExcludedBook book = new ExcludedBook(0, userId, bookId);
        try {
            excludedBookRepository.insert(book);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("exception: " + e);
        }
    }
}
