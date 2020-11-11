package com.group5.BookRead.repositories;

import com.group5.BookRead.models.Bookshelf;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface BookshelfRepository {

    int insert(Bookshelf shelf) throws SQLIntegrityConstraintViolationException;

    List<Bookshelf> findAllByUserId(int userId);

    Bookshelf findByNameAndUserId(String name, int userId);

    int findIdByNameAndUserId(String name, int userId);

    Bookshelf findById(int id);

    int deleteById(int id);

    Bookshelf findByBookshelfNameAndUsername(String shelfName, String username);

}
