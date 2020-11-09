package com.group5.BookRead.services.bookAPI;
import java.util.List;

public interface BookAPI {
    public List<BookFromAPI> getBooks(String searchKey);
}
