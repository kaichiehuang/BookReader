package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;

import java.sql.SQLIntegrityConstraintViolationException;


public interface UserService {

    int createUser(User user) throws SQLIntegrityConstraintViolationException;
    User findByUsername(String username);
    User removeUser(int userId);

}
