package com.group5.BookRead.services.user;

import com.group5.BookRead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "settings")
public class UserSettings implements Settings{

    private String defaultBookshelf;

    @Autowired
    private UserRepository userRepository;

    public UserSettings() {
        this.defaultBookshelf = null;
    };

    public void setDefaultBookshelf(final int userId) {
        this.defaultBookshelf = userRepository.
                findById(userId).getDefaultBookshelf();
    }

    public UserSettings(final UserSettings settings) {
        this.defaultBookshelf = settings.defaultBookshelf;

    }

    /**  get default bookshelf
     * @return String
     */
    public String getDefaultBookshelf() {
        return defaultBookshelf;
    }

    /**  Clone user settings
     * @return UserSetting
     */
    @Override
    public Settings clone() {
        return new UserSettings(this);
    }
}
