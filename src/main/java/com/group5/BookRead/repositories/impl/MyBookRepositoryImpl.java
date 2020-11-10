package com.group5.BookRead.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.MyBookRepository;

@Repository
public class MyBookRepositoryImpl implements MyBookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class MyBookRowMapper implements RowMapper<MyBook> {

        @Override
        public MyBook mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            MyBook mb = new MyBook();
            mb.setId(rs.getInt("id"));
            mb.setBookId(rs.getInt("book_id"));
            mb.setBookshelfId(rs.getInt("bookshelf_id"));
            mb.setUserId(rs.getInt("user_id"));
            mb.setProgress(rs.getInt("progress"));
            return mb;
        }

    }

    @Override
    public final int insert(final MyBook mybook) throws
        SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into MyBook (book_id, "
                + "user_id, bookshelf_id, progress) " + "values(?, ?, ?, ?)",
            new Object[] {mybook.getBookId(), mybook.getUserId(),
                    mybook.getBookshelfId(), mybook.getProgress()
                });
    }

    @Override
    public final List<MyBook> findAllByUserId(final int id) {
        try {
            List<MyBook> myBookList = jdbcTemplate.query(
                    "select * from MyBook " + "where user_id = ?",
                new Object[] {id},
                new MyBookRowMapper());
            return myBookList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public final List<MyBook> findAllByUserIdAndShelfId(
            final int userId, final int bookshelfId) {
        try {
            List<MyBook> myBookList = jdbcTemplate.query(
                    "select * from MyBook " + "where user_id = ?"
                            + "and bookshelf_id = ?",
                new Object[] {userId, bookshelfId},
                new MyBookRowMapper());
            return myBookList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public final List<MyBook> findAllByBookId(final int id) {
        try {
            List<MyBook> myBookList = jdbcTemplate.query(
                    "select * from MyBook " + "where book_id = ?",
                new Object[] {id},
                new MyBookRowMapper());
            return myBookList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public final int findIdByAllIds(final int bookId,
            final int userId, final int bookshelfId) {
        try {
            int id = jdbcTemplate.queryForObject("select id from MyBook "
                    + "where book_id = ? and user_id = ? and bookshelf_id = ?",
                new Object[] {bookId, userId,
                    bookshelfId}, int.class);
            return id;

        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }

    @Override
    public final int update(final MyBook mb) {
        return jdbcTemplate.update("update MyBook " + "set book_id = ?,"
                + "user_id = ?, bookshelf_id = ?, progress = ? "
                + "where id = ?",
            new Object[] {
                    mb.getBookId(), mb.getUserId(),
                    mb.getBookshelfId(), mb.getProgress(), mb.getId()
            });
    }

    @Override
    public final int deleteById(final int id) {
        return jdbcTemplate.update("delete from MyBook where id = ?",
                new Object[] {id});
    }

    @Override
    public final MyBook findById(final int id) {
        return null;
    }

    @Override
    public final MyBook findById(final int bookshelfId,
            final int userId, final int bookId) {
        return null;
    }

    @Override
    public final MyBook findByUsernameAndBookShelfnameAndBookId(
            final String username, final String bookshelfName,
            final String bookId) {
        return null;
    }

}
