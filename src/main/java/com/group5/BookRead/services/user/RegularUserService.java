package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.UserRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import com.group5.BookRead.services.user.userBuilder.BuilderDirector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service (value = "regular")
public class RegularUserService implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    BookshelfServiceSelector bookshelfServiceSelector;

    @Autowired
    BuilderDirector builderDirector;

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
    public User createUser(final String username, final String password) throws Exception {
        try {
            User user = builderDirector.makeUser(username, password);
            return user;
        } catch (Exception e) {
            throw new Exception("cannot create user (" + username
                    + ", " + password + ") : \n" + e.getMessage());
        }
    }

    /**
     * get all user
     * @return List<User>
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**  find by user id
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public User findByUserId(final int userId)
            throws UsernameNotFoundException {
        User user = userRepository.findById(userId);
        if (user == null) {
            throw new UsernameNotFoundException("" + userId);
        }
        return user;
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
