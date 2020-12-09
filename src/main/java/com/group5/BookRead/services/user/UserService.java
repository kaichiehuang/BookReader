package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User removeUser(int userId);
    User createUser(String username, String password) throws Exception;
    User createUser(User user);
    List<User> findAllUsers();
    User findByUserId(int userId) throws UsernameNotFoundException;
}
