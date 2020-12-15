package com.group5.BookRead.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.group5.BookRead.models.ExcludedBook;
import com.group5.BookRead.repositories.ExcludedBookRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ExcludedBookRepositoryImpl implements ExcludedBookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class ExcludedBookRowMapper implements RowMapper<ExcludedBook> {

        @Override
        public ExcludedBook mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            ExcludedBook book = new ExcludedBook();
            book.setId(rs.getInt("id"));
            book.setUserId(rs.getInt("user_id"));
            book.setBookId(rs.getInt("book_id"));
            return book;
        }

    }

    /**  insert excluded book
     * @param excluded book
     * @return status code
     */
    @Override
    @Transactional
    public int insert(final ExcludedBook excluded) {
        return jdbcTemplate.update(
                "insert into ExcludedBook (user_id, book_id) "
                + "values(?, ?)",
                new Object[] {
                        excluded.getUserId(), excluded.getBookId()
                });
    }

    /**  find all excluded books
     * @return excluded bookList
     */
    @Override
    public List<ExcludedBook> findAll() {
        return jdbcTemplate.query(
                "select * from ExcludedBook", new ExcludedBookRowMapper());
    }

    /**  find excluded book by excluded book id
     * @param id
     * @return excluded book
     */
    @Override
    public ExcludedBook findById(final int id) {
        try {
            ExcludedBook book = jdbcTemplate.queryForObject(
                    "select * from ExcludedBook "
                    + "where id = ?",
                new Object[] {id},
                new ExcludedBookRowMapper());
            return book;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**  find id by book id and user id
     * @param bookId
     * @param userId
     * @return id
     */
    @Override
    public int findIdByOtherIds(final int bookId, final int userId) {
        try {
            int id = jdbcTemplate.queryForObject("select id from ExcludedBook "
                    + "where book_id = ? and user_id = ?",
                    new Object[] {bookId, userId}, int.class);
            return id;

        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }

    /**  delete excluded book by excluded book id
     * @param id
     * @return status code
     */
    @Override
    @Transactional
    public int deleteById(final int id) {
        return jdbcTemplate.update("delete from ExcludedBook where id = ?",
                new Object[]{id});
    }

    /**  find excluded book list by userId
     * @param userId
     * @return excluded book list
     */
    @Override
    public List<ExcludedBook> findExcludedByUserId(final int userId) {
        try {
            List<ExcludedBook> result = jdbcTemplate.query(
                    "select * from ExcludedBook "
                    + "where user_id = ?",
                    new Object[] {userId}, new ExcludedBookRowMapper());
            return result;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
