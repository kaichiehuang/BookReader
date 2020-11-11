package com.group5.BookRead.services;

import com.group5.BookRead.models.User;
import com.group5.BookRead.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserServiceSelector {

    @Autowired
    UserService userService;

    /**
     *  create user
     * @param user
     * @return
     */
    public boolean createUser(final User user) {
        return userService.createUser(user) != null;
    }

    /**
     *  Load user through username
     * @param username
     * @return
     */
    public User getUser(final String username) {
        try {
            return userService.findByUsername(username);
        } catch (UsernameNotFoundException exception) {
            return null;
        }
    }
}
