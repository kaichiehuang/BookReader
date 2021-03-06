package com.group5.BookRead.repositories;

import com.group5.BookRead.models.MyBook;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface MyBookRepository {

    int insert(MyBook mybook) throws SQLIntegrityConstraintViolationException;

    List<MyBook> findAllByUserId(int id);

    List<MyBook> findAllByUserIdAndShelfId(int userId, int bookshelfId);

    List<MyBook> findAllByBookId(int id);

    List<MyBook> findAllMybooks(int userId, int bookshelfId);

    MyBook findByAllIds(int bookId, int userId, int bookshelfId);

    int update(MyBook mybook);

    int deleteById(int id);

    MyBook findById(int id);

    MyBook findById(int bookshelfId, int userId, int bookId);


    MyBook findByUsernameAndBookShelfNameAndBookId(
        String username, String shelfName, String bookId);

    int deleteByUserIdAndBookshelfIdAndBookId(int userId, int id, int bookId);

    MyBook findByUserAndBookShelfAndBookId(String userid, String shelfName,
        String bookId);
}
