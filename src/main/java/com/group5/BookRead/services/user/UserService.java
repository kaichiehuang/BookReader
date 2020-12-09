package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService {
    User findByUsername(String username);
    User createUser(String username, String password) throws Exception;
    List<User> findAllUsers();
    User findByUserId(int userId) throws UsernameNotFoundException;
    int setDefalultBookshelf(int userId, String bookshelfName);
}
