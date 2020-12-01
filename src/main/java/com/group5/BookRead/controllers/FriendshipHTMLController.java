package com.group5.BookRead.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.group5.BookRead.services.friend.FriendshipService;

import java.util.List;

@Controller
public class FriendshipHTMLController extends BookController {

    @Autowired
    FriendshipService friendshipService;

    /**
     * <p> get network page
     * </p>
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/network")
    public String renderNetworkPage() {
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
}
