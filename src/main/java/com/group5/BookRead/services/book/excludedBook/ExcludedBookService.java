package com.group5.BookRead.services.book.excludedBook;

import java.util.List;

public interface ExcludedBookService {
    List<Integer> getExcludedBooks(int userId);
    void addToExcluded(int bookId, int userId);
}
