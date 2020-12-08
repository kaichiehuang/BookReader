package com.group5.BookRead.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.services.friend.FriendshipService;
import com.group5.BookRead.services.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class RecommendBookController extends BookController {

    @Autowired
    FriendshipService friendshipService;

    @Autowired
    UserService userService;

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
            List<String> friendList = friendshipService.getFriends(userId);
            if (!friendList.contains(username)) {
                response.setStatus(HttpServletResponse.SC_OK);
                return "{\"msg\":\"Request failed because"
                        + "you are not friend with this user\"}";
            }

            // validate excluded book
            int friendId = userService.findByUsername(username).getId();
            List<Integer> excludedList = bookServiceSelector
                    .getExcludedBooks(friendId);
            if (excludedList.contains(bookId)) {
                response.setStatus(HttpServletResponse.SC_OK);
                return "{\"msg\":\"Cannot recommend this book"
                    + "because the other user has excluded it\"}";
            }

            // check if recommended before
            List<Book> books = bookServiceSelector.getBooks(
                    "recommended", friendId);
            for (Book book: books) {
                if (bookId == book.getId()) {
                    response.setStatus(HttpServletResponse.SC_OK);
                    return "{\"msg\":\"success\"}";
                }
            }

            // add to recommended
            Book book = bookServiceSelector.getBook(bookId);
            bookServiceSelector.addBookToShelf(book, "recommended", friendId);

            response.setStatus(HttpServletResponse.SC_OK);
            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
