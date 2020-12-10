package com.group5.BookRead.controllers;

import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.models.TimelineComment;
import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.User;
import com.group5.BookRead.models.Comment;
import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.bookAPI.BookAPI;
import com.group5.BookRead.services.bookAPI.BookFromAPI;
import com.group5.BookRead.services.comment.CommentService;
import com.group5.BookRead.services.comment.ResponseComment;
import com.group5.BookRead.services.timeline.ResponseTimeline;
import com.group5.BookRead.services.timeline.TimelineService;
import com.group5.BookRead.services.timelineComment.TimelineCommentResponse;
import com.group5.BookRead.services.timelineComment.TimelineCommentService;
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
import org.springframework.web.bind.annotation.PutMapping;
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

    @Autowired
    TimelineCommentService timelineCommentService;


    /**
     * populate viewBook page with book info and comments
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
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());

            List<ResponseTimeline> timelines = timelineService
                    .getTimelines(userId);
            System.out.println("currentUser: " + userId + " timeline page:");
            for (ResponseTimeline t : timelines) {
                System.out.println(t);
            }
//            System.out.printf("acquired timelines: %s\n", timelines);
            model.addAttribute("timelines", timelines);
            return "timeline";
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
    @GetMapping("/timeline/{userId}")
    public String timelineForUser(
            final Model model,
            @PathVariable final String userId,
            final HttpServletResponse response) {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int currentUser = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());
            int id = Integer.parseInt(userId);
            List<ResponseTimeline> timelines = timelineService
                    .getTimelinesByUser(id, currentUser);
            for (ResponseTimeline t : timelines) {
                System.out.println(t);
            }
            model.addAttribute("timelines", timelines);
            return "timeline";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "{\"msg\":\"failure\"}";
        }
    }

    /**
     * adding a like
     */
    @PostMapping(value = "/timeline/{postId}/like",
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody Response like(
            @PathVariable final String postId,
            final HttpServletResponse response) {
        Response res = new Response();
        try {
            int[] arr = getIds(postId);
            int userId = arr[0];
            int timelineId = arr[1];

            System.out.printf("userId: %d timelineId: %d\n",
                    userId, timelineId);

            TimelineComment saved = timelineCommentService.save(
                    new TimelineComment(
                            "", "like", userId, timelineId));
            System.out.printf("SAVED TimelineComment:  %s\n", saved);
            res.setSuccess(true);
            res.setMsg(saved.toString());
            response.setStatus(HttpServletResponse.SC_CREATED);
            return res;
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return res;
        }
    }

    private int[] getIds(final String id) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());
            int parsedId = Integer.parseInt(id);
            return new int[]{userId, parsedId};
        } catch (Exception e) {
            throw new Exception("userid or timelineid is wrong formatted");
        }
    }

    /**
     * removing an like
     */
    @PutMapping(value = "/timeline/{postId}/unlike",
            consumes = "application/json",
            produces = "application/json")

    public @ResponseBody Response unlike(
            @PathVariable final String postId,
            final HttpServletResponse response) {


        Response res = new Response();
        try {

            int[] arr = getIds(postId);

            int userId = arr[0];
            int timelineId = arr[1];
            System.out.printf("userId: %d timelineId: %d\n",
                    userId, timelineId);

            timelineCommentService.removeById(
                    userId, timelineId, "like");

            res.setSuccess(true);
            response.setStatus(HttpServletResponse.SC_CREATED);
            return res;
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return res;
        }

    }



    /**
     * create a new comment
     */
    @PostMapping(value = "/timeline/{postId}/comment",
            consumes = "application/json",
            produces = "application/json")
    public @ResponseBody
    TimelineCommentResponse writeComment(
            @RequestBody final Map<String, Object> body,
            @PathVariable final String postId,
            final HttpServletResponse response) {
        Response res = new Response();
        try {
            int[] arr = getIds(postId);
            int userId = arr[0];
            int timelineId = arr[1];
            String content = (String) body.get("comment");
            System.out.printf("userId: %d timelineId: %d content: %s\n",
                    userId, timelineId, content);
            TimelineComment saved = timelineCommentService.save(
                    new TimelineComment(
                            content, "comment", userId, timelineId));
            System.out.printf("SAVED TimelineComment:  %s\n", saved);
            response.setStatus(HttpServletResponse.SC_CREATED);

            User commentBy = userService.findByUserId(userId);

            return new TimelineCommentResponse(
                    saved.getId(),
                    saved.getUserId(),
                    saved.getContent(),
                    saved.getType(),
                    saved.getTimestamp(),
                    commentBy.getUsername());
        } catch (Exception e) {
            res.setMsg(e.getMessage());
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return null;
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
}
