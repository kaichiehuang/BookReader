package com.group5.BookRead.services.user.userBuilder;

import com.group5.BookRead.models.User;

public interface UserBuilder {

    void createUser(String username, String password) throws Exception;

    void createFavoriteBookshelf();

    void createRecommendedBookshelf();

    void createReadingBookshelf();

    void createReadBookShelf();

    void createWantToReadBookshelf();

    User getResult();
}
