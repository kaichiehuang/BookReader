package com.group5.BookRead.services.user.userBuilder;

import com.group5.BookRead.models.User;
import org.springframework.stereotype.Component;

@Component
public class BuilderDirector {

    UserBuilder userBuilder;

    public BuilderDirector(final UserBuilder userBuilder) {
        this.userBuilder = userBuilder;
    }

    /**
     * create user based on the builder type
     * @param username
     * @param password
     * @return
     */
    public User makeUser(final String username, final String password)
            throws Exception {
        userBuilder.createUser(username, password);
        userBuilder.createFavoriteBookshelf();
        userBuilder.createRecommendedBookshelf();
        userBuilder.createReadingBookshelf();
        userBuilder.createReadBookShelf();
        userBuilder.createWantToReadBookshelf();
        return userBuilder.getResult();
    }
}

