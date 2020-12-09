package com.group5.BookRead.services.HTMLRender.HTMLRenderServiceStrategy;

import java.util.ArrayList;
import java.util.List;

import com.group5.BookRead.models.User;
import com.group5.BookRead.services.HTMLRender.HTMLRenderService;
import com.group5.BookRead.services.HTMLRender.StrategyName;
import com.group5.BookRead.services.friend.FriendshipService;
import com.group5.BookRead.services.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class NetworkHTMLStrategy implements HTMLRenderService {
    @Autowired
    UserService userService;

    @Autowired
    FriendshipService friendshipService;

    @Override
    public String renderPage(final Model model) {
        SecurityContext context = SecurityContextHolder.getContext();

        int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());
        List<User> users = userService.findAllUsers();
        List<String> friendList = friendshipService.getFriends(userId);
        List<String> requestedFriendList =
                friendshipService.getAllRequestedFriends(userId);

        List<String> userList = new ArrayList<>();

        for (User user : users) {
            if (user.getId() != userId
                    && !friendList.contains(user.getUsername())
                    && !requestedFriendList.contains(user.getUsername())) {
                userList.add(user.getUsername());
            }
        }

        model.addAttribute("users", userList);

        return "network";
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.NetworkHTMLStrategy;
    }
}
