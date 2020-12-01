package com.group5.BookRead.repositories;

import com.group5.BookRead.models.Book;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface BookRepository {
    int insert(Book book) throws SQLIntegrityConstraintViolationException;

//    Book save(Book book) throws SQLIntegrityConstraintViolationException;

    List<Book> findAll();

    Book findById(int id);

    Book findByNameAndAuthor(String name, String author);

    int findIdByNameAndAuthor(String name, String author);

    int update(Book book);

    int deleteById(int id);

    Book findByIdentifier(String identifier);


}
