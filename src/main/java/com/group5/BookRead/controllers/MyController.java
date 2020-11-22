package com.group5.BookRead.controllers;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Comment;
import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.models.User;
import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.comment.CommentService;
import com.group5.BookRead.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {


    @Autowired
    BookServiceSelector bookServiceSelector;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    /**
     * populate viewBook page woth book ionfo and comments
     * @param bookId
     * @param model
     * @return
     */
    @GetMapping("/book")
    public String bookPage(@RequestParam("id") final int bookId,
                         final Model model,
                         final HttpServletResponse response) {
        try {
            List<Comment> comments = commentService.getComments(bookId);
            Book book = bookServiceSelector.getBook(bookId);
            model.addAttribute("book", book);
            model.addAttribute("comments", comments);
            return "viewBook";
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "{\"msg\":\"failure\"}";
        }
    }


    /**
     * create a new comment
     * @param body
     * @param bookId
     * @param response
     */
    @PostMapping ("/book/review")
    public String writeReview(@RequestBody final Map<String, Object> body,
                              @RequestParam final int bookId,
                              final HttpServletResponse response) {
        SecurityContext context = SecurityContextHolder.getContext();
        int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

        int rating = (int) body.get("rating");
        String text = (String) body.get("text");
        try {
            Comment comment = new Comment(userId, bookId, rating, text);
            Comment savedComment = commentService.save(comment);

            // store to a timeline
            Book book = bookServiceSelector.getBook(bookId);
            User user = userService.findByUserId(userId);
            String content = String.format("%s writes on book \"%s\": %s | "
                            + "score:%d",
                    user.getUsername(),
                    book.getName(),
                    savedComment.getText(),
                    savedComment.getRating());

            new Timeline(userId, content, "review");
            response.setStatus(HttpServletResponse.SC_CREATED);
            return "{\"msg\":\"success\"}";
        } catch (Exception  e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "{\"msg\":\"failure\"}";
        }

    }

}
