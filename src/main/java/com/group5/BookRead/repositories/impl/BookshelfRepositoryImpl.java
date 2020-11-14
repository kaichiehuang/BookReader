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

    /**  insert bookshelf
     * @param shelf
     * @return status code
     */
    @Override
    public int insert(final Bookshelf shelf) throws
        SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into Bookshelf (user_id, name) "
                + "values(?, ?)",
                new Object[] {shelf.getUserId(), shelf.getName()});
    }

    /**  find all bookshelves of a user
     * @param userId
     * @return bookshelfList
     */
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

    /**  find bookshelf by shelf name and userId
     * @param name
     * @param userId
     * @return bookshelf
     */
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

    /**  find bookshelf id by shelf name and userId
     * @param name
     * @param userId
     * @return id
     */
    @Override
    public int findIdByNameAndUserId(final String name, final int userId) {
        System.err.println("BookshelfRepositoryImpl:93 - not implement");
        System.exit(1);
        return 0;
    }

    /**  find bookshelf by id
     * @param id
     * @return bookshelf
     */
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

    /**  delete bookshelf by id
     * @param id
     * @return status code
     */
    @Override
    public int deleteById(final int id) {
        return jdbcTemplate.update("delete from Bookshelf where id = ?",
            new Object[] {id});
    }

    /**  find bookshelf by shelf name and user name
     * @param bookshelfName
     * @param username
     * @return bookshelf
     */
    @Override
    public Bookshelf findByBookshelfNameAndUsername(
            final String bookshelfName, final String username) {
        System.err.println("BookshelfRepositoryImpl:132 - not implement");
        System.exit(1);
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
        try {
            Bookshelf shelf = jdbcTemplate.queryForObject("select * "
                    + "from Bookshelf " + "where name = ? and user_id = ?",
                new Object[] {bookshelf, user},
                new BookshelfRowMapper());
            return shelf;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

}
