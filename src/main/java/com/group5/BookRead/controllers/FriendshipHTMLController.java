package com.group5.BookRead.controllers;

import com.group5.BookRead.models.User;
import com.group5.BookRead.services.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.group5.BookRead.services.friend.FriendshipService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FriendshipHTMLController extends BookController {

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserService userService;

    /**
     * <p> get network page
     * </p>
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/network")
    public String renderNetworkPage(final Model model) {
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

    /**
     * <p> get friends list page
     * </p>
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/friends")
    public String renderFriendsPage(final Model model) {
        SecurityContext context = SecurityContextHolder.getContext();

        int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

        List<String> friendList = friendshipService.getFriends(userId);

        //System.out.println(bookshelfs);
        model.addAttribute("friends", friendList);
        return "friends";
    }

    /**
     * <p> get friend request page
     * </p>
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/request")
    public String renderFriendRequestPage(final Model model) {
        SecurityContext context = SecurityContextHolder.getContext();

        int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

        List<String> requestList =
                friendshipService.getAllFriendRequestsFromOthers(userId);


        model.addAttribute("requests", requestList);

        return "request";
    }
}
