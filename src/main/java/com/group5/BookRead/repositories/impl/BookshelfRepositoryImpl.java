package com.group5.BookRead.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.repositories.BookshelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookshelfRepositoryImpl implements BookshelfRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    class BookshelfRowMapper implements RowMapper<Bookshelf> {

        @Override
        public Bookshelf mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            Bookshelf shelf = new Bookshelf();
            shelf.setId(rs.getInt("id"));
            shelf.setName(rs.getString("name"));
            shelf.setUserId(rs.getInt("user_id"));
            return shelf;
        }

    }

    @Override
    public int insert(final Bookshelf shelf) throws
        SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into Bookshelf (user_id, name) "
                + "values(?, ?)",
                new Object[] {shelf.getUserId(), shelf.getName()});
    }

    @Override
    public List<Bookshelf> findAllByUserId(final int userId) {
        try {
            List<Bookshelf> shelfList = jdbcTemplate.query("select * "
                    + "from Bookshelf " + "where user_id = ?",
                new Object[] {userId},
                new BookshelfRowMapper());
            System.out.print(userId);
            return shelfList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Bookshelf findByNameAndUserId(
            final String name, final int userId) {
        try {
            Bookshelf shelf = jdbcTemplate.queryForObject("select * "
                    + "from Bookshelf " + "where name = ? and user_id = ?",
                new Object[] {name, userId},
                new BookshelfRowMapper());
            return shelf;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public final int findIdByNameAndUserId(final String name,
            final int userId) {
        return 0;
    }

    @Override
    public Bookshelf findById(final int id) {
        try {
            Bookshelf shelf = jdbcTemplate.queryForObject("select * "
                    + "from Bookshelf " + "where id = ?",
                new Object[] {id},
                new BookshelfRowMapper());
            return shelf;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int deleteById(final int id) {
        return jdbcTemplate.update("delete from Bookshelf where id = ?",
            new Object[] {id});
    }

    @Override
    public Bookshelf findByBookshelfNameAndUsername(
            final String bookshelfName, final String username) {
        return null;
    }

    /**
     *  find bookshelf
     * @param bookshelf
     * @param user
     * @return
     */
    @Override
    public Bookshelf findByBookshelfNameAndUserId(final String bookshelf,
                                                  final int user) {
        return null;
    }

}
