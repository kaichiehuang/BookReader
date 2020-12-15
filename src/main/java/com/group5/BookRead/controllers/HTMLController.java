package com.group5.BookRead.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.group5.BookRead.services.HTMLRender.HTMLRenderService;
import com.group5.BookRead.services.HTMLRender.HTMLRenderServiceStrategyFactory;
import com.group5.BookRead.services.HTMLRender.StrategyName;
import com.group5.BookRead.services.book.DecoratorChainException;

@Controller
public class HTMLController extends BookController {

    @Autowired
    private HTMLRenderServiceStrategyFactory strategyFactory;

    /**
     * <p>
     * get login page
     * </p>
     * 
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/login")
    public String getLoginPage(final Model model) 
        throws DecoratorChainException {
        HTMLRenderService strategy = 
            strategyFactory.findStrategy(StrategyName.LoginHTMLStrategy);
        return strategy.renderPage(model);
    }

    /**
     * <p>
     * logout
     * </p>
     * 
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/logout")
    public String logout(final Model model) throws DecoratorChainException {
        HTMLRenderService strategy = 
            strategyFactory.findStrategy(StrategyName.LoginHTMLStrategy);
        return strategy.renderPage(model);
    }

    /**
     * <p> get network page
     * </p>
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/network")
    public String renderNetworkPage(final Model model) 
        throws DecoratorChainException {
        HTMLRenderService strategy = 
            strategyFactory.findStrategy(StrategyName.NetworkHTMLStrategy);
        return strategy.renderPage(model);
    }

    /**
     * <p> get friends list page
     * </p>
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/friends")
    public String renderFriendsPage(final Model model) 
        throws DecoratorChainException {
        HTMLRenderService strategy = 
            strategyFactory.findStrategy(StrategyName.FriendHTMLStrategy);
        return strategy.renderPage(model);
    }

    /**
     * <p> get friend request page
     * </p>
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/request")
    public String renderFriendRequestPage(final Model model) 
        throws DecoratorChainException {
        HTMLRenderService strategy = 
            strategyFactory.findStrategy(StrategyName.FriendRequestHTMLStrategy);
        return strategy.renderPage(model);
    }

    /**
     * <p>
     * get Book shelf items
     * </p>
     * 
     * @param model Thymyleaf model
     * @return value to be outputed
     * @throws DecoratorChainException
     * @since 1.0
     */
    @GetMapping("/book/shelf")
    public String getBookBookshelf(final Model model) 
        throws DecoratorChainException {

        HTMLRenderService strategy = 
            strategyFactory.findStrategy(StrategyName.BookshelfHTMLStrategy);
        return strategy.renderPage(model);
    }



    /**
     * <p>
     * get Book shelf items
     * </p>
     * 
     * @param model Thymyleaf model
     * @return value to be outputed
     * @throws DecoratorChainException
     * @since 1.0
     */
    @GetMapping("/timeline")
    public String timeline(final Model model) 
        throws DecoratorChainException {

        HTMLRenderService strategy = 
            strategyFactory.findStrategy(StrategyName.TimelineHTMLStrategy);
        return strategy.renderPage(model);
    }

}
