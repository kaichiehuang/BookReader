package com.group5.BookRead.repositories;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.Bookshelf;

public interface BookshelfRepository {

	int insert(Bookshelf shelf) throws SQLIntegrityConstraintViolationException;
	List<Bookshelf> findAllByUserId(int user_id);
	Bookshelf findByNameAndUserId(String name, int user_id);
	int findIdByNameAndUserId(String name, int user_id);
	Bookshelf findById(int id);
	int deleteById(int id);
	
}
