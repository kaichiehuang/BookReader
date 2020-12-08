package com.group5.BookRead.services.bookAPI;
import java.util.List;

public interface BookAPI {
    List<BookFromAPI> getBooks(String searchKey);
    BookFromAPI getBook(String bookIdentifier);
}
