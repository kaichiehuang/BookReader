package com.group5.BookRead.services.user;

import com.group5.BookRead.models.User;
import com.group5.BookRead.services.BookshelfServiceSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface UserService {


    public User createUser(String username, String password);
    public User findByUsername(String username);
    public User removeUser(int userId);

}
