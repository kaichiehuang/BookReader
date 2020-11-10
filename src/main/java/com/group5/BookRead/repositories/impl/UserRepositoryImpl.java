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

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum)
                throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));
            user.setUsernme(rs.getString("username"));
            return user;
        }
    }

    @Override
    public final int insert(User user) throws
        SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into User (username, password) "
        + "values(?, ?)",
            new Object[] {user.getUsernme(), user.getPassword()});
    }

    @Override
    public final List<User> findAll() {
        return jdbcTemplate.query("select * from User", new UserRowMapper());
    }

    @Override
    public final User findById(int id) {
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

    @Override
    public final User findByUsername(String username) {
        try {
            User user = jdbcTemplate.queryForObject(
                "select * from User " + "where username = ?",
                new Object[] {username},
                new UserRowMapper());
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public final int findIdByUsername(String username) {
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

    @Override
    public final int update(User user) {
        return jdbcTemplate.update("update User "
                + "set username = ?, password = ? "
                + "where id = ?",
                new Object[] {user.getUsernme(), user.getPassword(),
                        user.getId()});
    }

    @Override
    public final int deleteById(int id) {
        return jdbcTemplate.update("delete from User where id = ?",
                new Object[] {id});
    }

}
