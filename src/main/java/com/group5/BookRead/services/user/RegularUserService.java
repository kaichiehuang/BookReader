package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.UserRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;

@Service (value = "regular")
public class RegularUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    public RegularUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * create user and added to database
     * bookshelves will be created as well when an user registers
     * @param user
     * @return registered user
     */
    @Override
    public User createUser(final User user) {
        String[] bookshelves = {"favorites", "recommended", "reading", "read"};
        try {
            userRepository.insert(user);
            User storedUser = userRepository.findByUsername(user.getUsername());
            for (String bookshelf : bookshelves) {
                bookshelfServiceSelector.create(bookshelf, storedUser.getId());
            }
            return storedUser;
        } catch (SQLIntegrityConstraintViolationException exception) {
            return null;
        }
    }


    /**
     * get user by username
     * @param username
     * @return user
     */
    @Override
    public User findByUsername(final String username)
            throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
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
