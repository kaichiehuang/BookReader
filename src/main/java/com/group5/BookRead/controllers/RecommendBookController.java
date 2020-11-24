package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.services.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class RecommendBookController extends BookController {

    @Autowired
    private UserService userService;

    /**
     * recommend a book to a friend
     * @param json body object
     * @param friend's username string
     * @param response response object
     * @return success msg in json
     */
    @PostMapping(value = "/book/{username}",
            consumes = "application/json", produces = "application/json")
    public String recommendBook(
            @RequestBody final Map<String, String> json,
            @PathVariable final String username,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();

            // get parameters
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());
            int bookId = Integer.parseInt(json.get("bookId"));

            // validate friendship
//            List<String> friendList = userService.findFriends(userId);
//            if (!friendList.contains(username)) {
//                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
//                return "{\"msg\":\"Request failed because
//            you are not friend with this user\"}";
//            }

            // validate excluded book
//            List<Integer> excludedList = bookServiceSelector
//            .getExcludedBooks(userId);
//            if (excludedList.contains(bookId)) {
//                response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
//                return "{\"msg\":\"Cannot recommend this book
//            because the other user has excluded it\"}";
//            }

            // add to recommended
            int friendId = userService.findByUsername(username).getId();
            Book book = bookServiceSelector.getBook(bookId);
            bookServiceSelector.addBookToShelf(book, "recommended", friendId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
