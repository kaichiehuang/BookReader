package com.group5.BookRead.services.user.userBuilder;

import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.UserRepository;
import com.group5.BookRead.services.BookshelfServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class RegularUserBuilder implements UserBuilder {


//    String[] bookshelves = new String[] {"favorites", "recommended",
//            "reading", "read", "want to read"};
//        try {
//        userRepository.insert(user);
//        User storedUser = userRepository.findByUsername(user.getUsername());
//        for (String bookshelf : bookshelves) {
//            bookshelfServiceSelector.create(bookshelf, storedUser.getId());
//        }
//        return storedUser;
//    } catch (
//    SQLIntegrityConstraintViolationException exception) {
//        return null;
//    }
//        return null;

    /**
     * cerate a user based on current type
     * @param username
     * @param password
     * @return
     */

    User user;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookshelfServiceSelector bookshelfServiceSelector;

    /**
     * create user object
     * @param username
     * @param password
     */
    @Override
    public void createUser(final String username, final String password) throws Exception {
        User newUser = new User(username, password);
        userRepository.insert(newUser);
        this.user = userRepository.findByUsername(newUser.getUsername());
    }

    /**
     * create favorite bookshelf
     */
    @Override
    public void createFavoriteBookshelf() {
        bookshelfServiceSelector.create("favorites", user.getId());
    }

    /**
     * create recommended bookshelf
     */
    @Override
    public void createRecommendedBookshelf() {
        bookshelfServiceSelector.create("recommended", user.getId());
    }

    /**
     * create reading bookshelf
     */
    @Override
    public void createReadingBookshelf() {
        bookshelfServiceSelector.create("reading", user.getId());
    }

    /**
     * create read
     */
    @Override
    public void createReadBookShelf() {
        bookshelfServiceSelector.create("read", user.getId());
    }

    /**
     * create want to read bookshelf
     */
    @Override
    public void createWantToReadBookshelf() {
        bookshelfServiceSelector.create("want to read", user.getId());
    }

    /**
     * return result
     * @return
     */
    @Override
    public User getResult() {
        return user;
    }
}
