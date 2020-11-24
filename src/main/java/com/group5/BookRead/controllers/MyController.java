package com.group5.BookRead.controllers;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Comment;
import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.models.User;
import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.bookAPI.BookAPI;
import com.group5.BookRead.services.bookAPI.BookFromAPI;
import com.group5.BookRead.services.comment.CommentService;
import com.group5.BookRead.services.comment.ResponseComment;
import com.group5.BookRead.services.timeline.ResponseTimeline;
import com.group5.BookRead.services.timeline.TimelineService;
import com.group5.BookRead.services.user.UserService;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MyController {


    @Autowired
    BookServiceSelector bookServiceSelector;

    @Autowired
    BookAPI bookAPI;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @Autowired
    TimelineService timelineService;

    /**
     * populate viewBook page woth book ionfo and comments
     * @param identifier
     * @param model
     * @return
     */
    @GetMapping("/book")
    public String bookPage(@RequestParam("id") final String identifier,
                         final Model model,
                         final HttpServletResponse response) {

        System.out.println("identifier: " + identifier);

        try {
            //
            Book book = bookServiceSelector.getBook(identifier);
            if (book != null) {
                List<ResponseComment> comments = commentService.getComments(
                        book.getId());
                System.out.println("viewing book: " + book);
                model.addAttribute("book", book);
                model.addAttribute("comments", comments);
            } else {
                BookFromAPI bookFromAPI = bookAPI.getBook(identifier);
                System.out.println(bookFromAPI);
                model.addAttribute("book", bookFromAPI);
                model.addAttribute("comments",
                        new ArrayList<ResponseComment>());
            }

            return "viewBook";
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "{\"msg\":\"failure\"}";
        }
    }

    /**
     *  get all activities to post on timeline
     * @param model
     * @param response
     * @return
     */
    @GetMapping("/timeline")
    public String bookPage(
                           final Model model,
                           final HttpServletResponse response) {
        try {
            List<ResponseTimeline> timelines = timelineService.getTimelines();
            System.out.println(timelines);
            model.addAttribute("timelines", timelines);
            return "timeline";
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    @PostMapping(value = "/books/{bookId}/review",
            consumes = "application/json",
            produces = "application/json")

    public @ResponseBody Response writeReview(
            @RequestBody final Map<String, Object> body,
            @PathVariable final String bookId,
            final HttpServletResponse response) {
        SecurityContext context = SecurityContextHolder.getContext();
        int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

        Response res = new Response();

        try {
            String str = (String) body.get("rating");
            int rating = Integer.parseInt(str);
            String text = (String) body.get("review");
            System.out.println(rating + " " + text);
            Book bookFromDB = bookServiceSelector.getBook(bookId);
            if (bookFromDB == null) {

                String title = (String) body.get("title");
                String author = (String) body.get("author");
                String description = (String) body.get("description");

                int page = Integer.parseInt((String) body.get("page"));
                String link = body.get("link").toString();
                System.out.println(description);
                System.out.println(link);

                link = StringEscapeUtils.unescapeHtml(link);
                description = StringEscapeUtils.unescapeHtml(description);
                System.out.println(link);
                System.out.println(description);


                Book newBook = new Book(
                        title,
                        author,
                        page,
                        description,
                        bookId,
                        link);

                bookFromDB = bookServiceSelector.storeBook(newBook);
            }


            System.out.printf("rating: %d text: %s userId:%d\n",
                    rating,
                    text,
                    userId);
            System.out.println(bookFromDB);



            Comment comment = new Comment(
                    userId,
                    bookFromDB.getId(),
                    rating,
                    text);
            Comment savedComment = commentService.save(comment);

            // store to a timeline
            Book book = bookServiceSelector.getBook(bookFromDB.getId());
            User user = userService.findByUserId(userId);
            String content = String.format("%s writes on book \"%s\": %s | "
                            + "score:%d",
                    user.getUsername(),
                    book.getTitle(),
                    savedComment.getContent(),
                    savedComment.getRating());

            Timeline timeline = new Timeline(userId, content, "review");
            timelineService.store(timeline);

            response.setStatus(HttpServletResponse.SC_CREATED);

            res.success = true;
            return res;
        } catch (Exception  e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return res;
        }

    }


//    @PostMapping ("/book/progress")
//    public String postProgress(@RequestBody final Map<String, Object> body,
//                              @RequestParam final int bookId,
//                              final HttpServletResponse response) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        int userId = Integer.parseInt(context.getAuthentication()
//                .getPrincipal().toString());
//
//        int progress = (int) body.get("progress");
//
//
//        System.out.printf("progress: %d : %s userId:%d\n",
//                userId);
//        try {
//            Comment comment = new Comment(userId, bookId, rating, text);
//            Comment savedComment = commentService.save(comment);
//
//            // store to a timeline
//            Book book = bookServiceSelector.getBook(bookId);
//            User user = userService.findByUserId(userId);
//            String content = String.format("%s writes on book \"%s\": %s | "
//                            + "score:%d",
//                    user.getUsername(),
//                    book.getTitle(),
//                    savedComment.getText(),
//                    savedComment.getRating());
//
//            Timeline timeline = new Timeline(userId, content, "review");
//            timelineService.store(timeline);
//
//            response.setStatus(HttpServletResponse.SC_CREATED);
//            return "{\"msg\":\"success\"}";
//        } catch (Exception  e) {
//            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            return "{\"msg\":\"failure\"}";
//        }
//    }

}
