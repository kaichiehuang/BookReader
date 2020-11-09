package com.group5.BookRead.repositories;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.Book;

public interface BookRepository {
	
	int insert(Book book) throws SQLIntegrityConstraintViolationException;
	List<Book> findAll();
	Book findById(int id);
	Book findByNameAndAuthor(String name, String author);
	int findIdByNameAndAuthor(String name, String author);
	int update(Book book);
	int deleteById(int id);
	
	
}
