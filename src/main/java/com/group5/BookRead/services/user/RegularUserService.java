package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service (value = "regular")
public class RegularUserService implements UserService {

    private UserRepository userRepository;

    @Autowired
    public RegularUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * create user and added to database
     * bookshelves will be created as well when an user registers
     * @param username
     * @param password
     * @return registered user
     */
    @Override
    public int createUser(final User user) throws SQLIntegrityConstraintViolationException {
        return userRepository.insert(user);
    }

    /**
     * get user by username
     * @param username
     * @return user
     */
    @Override
    public User findByUsername(final String username) {
        return null;
    }

    /**
     * remove user from database, and remove bookshelves and books of the user
     * @param userId
     * @return removed user
     */
    @Override
    public User removeUser(final int userId) {
        return null;
    }
}
