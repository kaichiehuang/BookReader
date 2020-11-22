package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    User findByUsername(String username);
    User removeUser(int userId);
    User createUser(User user);
    User findByUserId(int userId) throws UsernameNotFoundException;
}
