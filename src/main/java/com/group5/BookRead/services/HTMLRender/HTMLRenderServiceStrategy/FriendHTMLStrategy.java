package com.group5.BookRead.services.HTMLRender.HTMLRenderServiceStrategy;

import java.util.List;

import com.group5.BookRead.services.HTMLRender.HTMLRenderService;
import com.group5.BookRead.services.HTMLRender.StrategyName;
import com.group5.BookRead.services.friend.FriendshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class FriendHTMLStrategy implements HTMLRenderService {

    @Autowired
    FriendshipService friendshipService;

    @Override
    public String renderPage(final Model model) {
        SecurityContext context = SecurityContextHolder.getContext();

        int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

        List<String> friendList = friendshipService.getFriends(userId);

        //System.out.println(bookshelfs);
        model.addAttribute("friends", friendList);
        return "friends";
    }

    @Override
    public StrategyName getStrategyName() {
        return StrategyName.FriendHTMLStrategy;
    }
}
