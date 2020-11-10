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
        public Bookshelf mapRow(ResultSet rs, int rowNum) throws SQLException {
            Bookshelf shelf = new Bookshelf();
            shelf.setId(rs.getInt("id"));
            shelf.setName(rs.getString("name"));
            shelf.setUserId(rs.getInt("user_id"));
            return shelf;
        }

    }

    @Override
    public final int insert(Bookshelf shelf) throws
        SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into Bookshelf (user_id, name) "
                + "values(?, ?)",
                new Object[] {shelf.getUserId(), shelf.getName()});
    }

    @Override
    public final List<Bookshelf> findAllByUserId(int userId) {
        try {
            List<Bookshelf> shelfList = jdbcTemplate.query("select * "
                    + "from Bookshelf " + "where user_id = ?",
                new Object[] {userId},
                new BookshelfRowMapper());
            return shelfList;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public final Bookshelf findByNameAndUserId(String name, int userId) {
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
    public final int findIdByNameAndUserId(String name, int userId) {
        return 0;
    }

    @Override
    public final Bookshelf findById(int id) {
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
    public final int deleteById(int id) {
        return jdbcTemplate.update("delete from Bookshelf where id = ?",
            new Object[] {id});
    }

    @Override
    public final Bookshelf findByBookshelfNameAndUsername(String bookshelfName,
            String username) {
        return null;
    }

}
