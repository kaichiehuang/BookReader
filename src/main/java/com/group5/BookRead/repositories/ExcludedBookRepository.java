package com.group5.BookRead.repositories;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.ExcludedBook;

public interface ExcludedBookRepository {
    int insert(ExcludedBook excluded)
            throws SQLIntegrityConstraintViolationException;

    List<ExcludedBook> findAll();

    List<ExcludedBook> findExcludedByUserId(int userId);

    ExcludedBook findById(int id);

    int findIdByOtherIds(int bookId, int userId);

    int deleteById(int id);
}
