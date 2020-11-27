package com.group5.BookRead.services.book.excludedBook;

import com.group5.BookRead.models.ExcludedBook;
import com.group5.BookRead.repositories.ExcludedBookRepository;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public final class RegularExcludedBookService implements ExcludedBookService {

    private ExcludedBookRepository excludedBookRepository;

    @Autowired
    public RegularExcludedBookService(
            final ExcludedBookRepository excludedBookRepository) {
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
        ExcludedBook book = new ExcludedBook(0, userId, bookId);
        try {
            excludedBookRepository.insert(book);
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
        }
    }

}

