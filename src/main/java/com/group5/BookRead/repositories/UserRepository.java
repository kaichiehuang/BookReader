package com.group5.BookRead.repositories;

import com.group5.BookRead.models.User;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;


public interface UserRepository {

    int insert(User user) throws SQLIntegrityConstraintViolationException;

    List<User> findAll();

    User findById(int id);

    User findByUsername(String username);

    int findIdByUsername(String username);

    int update(User user);

    int deleteById(int id);

}
