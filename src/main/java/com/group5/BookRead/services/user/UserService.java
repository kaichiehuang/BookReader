package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;


public interface UserService {
    User findByUsername(String username);
    User removeUser(int userId);
    User createUser(User user);
}
