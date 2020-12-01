package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User removeUser(int userId);
    User createUser(User user);
    List<User> findAllUsers();
}
