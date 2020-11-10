package com.group5.BookRead.repositories;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.MyBook;

public interface MyBookRepository {

	int insert(MyBook mybook) throws SQLIntegrityConstraintViolationException;
	List<MyBook> findAllByUserId(int id);
	List<MyBook> findAllByUserIdAndShelfId(int user_id, int bookshelf_id);
	List<MyBook> findAllByBookId(int id);
	int findIdByAllIds(int book_id, int user_id, int bookshelf_id);
	int update(MyBook mybook);
	int deleteById(int id);

    MyBook findById(int id);

	MyBook findById(int bookshelf_id, int user_id, int book_id);

    MyBook findByUsernameAndBookShelfnameAndBookId(String username, String bookshelfName, int bookId);
}
