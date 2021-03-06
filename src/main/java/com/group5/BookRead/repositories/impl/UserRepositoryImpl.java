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

import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));
            user.setUsername(rs.getString("username"));
            user.setDefaultBookshelf(rs.getString("default_bookshelf"));
            return user;
        }
    }

    /**  insert user
     * @param user
     * @return status code
     */
    @Override
    @Transactional
    public int insert(final User user) throws

        SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update(
                "insert into User (username, password, default_bookshelf) "
                        + "values(?, ?, ?)",
            new Object[] {user.getUsername(),
                    user.getPassword(), user.getDefaultBookshelf()});
    }

    /**  find all user
     * @return userList
     */
    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from User", new UserRowMapper());
    }

    /**  find user by id
     * @param id
     * @return user
     */
    @Override
    public User findById(final int id) {
        try {
            User user = jdbcTemplate.queryForObject(
                    "select * from User " + "where id = ?",
                new Object[] {id},
                new UserRowMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**  find user by username
     * @param username
     * @return user
     */
    @Override
    public User findByUsername(final String username) {
        try {
            User user = jdbcTemplate.queryForObject(
                "select * from User " + "where username = ?",
                new Object[] {username},
                new UserRowMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
    }

    /**  find user id by username
     * @param username
     * @return id
     */
    @Override
    public int findIdByUsername(final String username) {
        try {
            int id = jdbcTemplate.queryForObject("select id from User "
                    + "where username = ?",
                new Object[] {username},
                int.class);
            return id;
        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }

    /**  update user
     * @param user
     * @return status code
     */
    @Override
    public int update(final User user) {
        return jdbcTemplate.update("update User "
                + "set username = ?, password = ? "
                + "where id = ?",
                new Object[] {user.getUsername(), user.getPassword(),
                        user.getId()});
    }

    /**  update user
     * @param user
     * @return status code
     */
    @Override
    public int setDefalultBookshelf(
            final int userId, final String bookshelfName) {
        return jdbcTemplate.update("update User "
                + "set default_bookshelf = ? "
                + "where id = ?",
                new Object[] {bookshelfName, userId});
    }

    /**  delete user by id
     * @param id
     * @return status code
     */
    @Override
    @Transactional
    public int deleteById(final int id) {
        return jdbcTemplate.update("delete from User where id = ?",
                new Object[] {id});
    }

}
