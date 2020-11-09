package com.group5.BookRead.services.user;

import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service (value = "regular")
public class RegularUserService implements UserService{


    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(String username, String password) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public User removeUser(int userId) {
        return null;
    }
}