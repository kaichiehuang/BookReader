package com.group5.BookRead.services.settings;

import com.group5.BookRead.services.user.UserService;

public class UserSettings implements Settings{

    private UserService userService;
    public String defaultBookshelf;

    public UserSettings(final UserService userService, 
        final int userId) {
        this.defaultBookshelf = userService
            .findByUserId(userId).getDefaultBookshelf();
    };

    public UserSettings(final UserSettings settings){
        this.defaultBookshelf = settings.defaultBookshelf;
    }

    /**  Clone user settings
     * @return UserSetting
     */
    @Override
    public Settings clone() {
        return new UserSettings(this);
    }
}
