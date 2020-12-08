package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.book.BookDecorator.BookServiceDecorator;


@RestController
public class TrackProgressController {
    @Autowired
    @Qualifier("basicDecoratedBookService")
    BookServiceDecorator bookServiceDecorator;

    @Autowired
    BookServiceSelector bookServiceSelector;

    public static final double PROGRESS_PERCENTAGE = 100.0;

    
    /**
     * get current progress of mybook
     * @param json param object
     * @param response response object
     * @return progress in json
     */
    @GetMapping("/book/progress")
    public String getProgress(
            @RequestParam final Map<String, String> json,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            // get parameters
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());
            int bookId = Integer.parseInt(json.get("bookId"));
            String bookshelf = json.get("bookshelf");

            // get total page and previous progress
            int bookshelfId = bookServiceDecorator.getShelf(
                    bookshelf, userId).getId();

            MyBook mbook = bookServiceDecorator.getMyBook(
                    userId, bookshelfId, bookId);
            double progress = bookServiceDecorator.getMyBook(
                    userId, bookshelfId, bookId).getProgress();

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"progress\":\"" + progress + "\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * update current progress of mybook in all relevant shelves
     * @param json body object
     * @param response response object
     * @return success msg in json
     */
    @PostMapping(value = "/book/progress",
            consumes = "application/json", produces = "application/json")
    public String updateProgress(
            @RequestBody final Map<String, String> json,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();

            // get parameters
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());
            int bookId = Integer.parseInt(json.get("bookId"));
            int curPage = Integer.parseInt(json.get("curPage"));

            // get total page and current progress
            Book bookFromDb = bookServiceSelector.getBook(bookId);
            int totalPage = bookFromDb.getPage();

            double curProgress = PROGRESS_PERCENTAGE * curPage / totalPage;

            // update progress
//            bookServiceDecorator.updateProgress(
//                    userId, bookId, curProgress);

            // manage shelves
            // srcShelf = want to read, reading, or read
//            String srcShelf = bookServiceDecorator.getReadingShelf(
//                    userId, bookId);
//            String dstShelf = srcShelf;
//            if (curProgress != 100) {
//                dstShelf = "reading";
//            }
//            else if (curProgress == 100 ) {
//                dstShelf = "read";
//            }
//            bookServiceSelector.addBookToShelf(
//                    bookFromDb, dstShelf, userId);
//            bookServiceSelector.removeBook(bookId, srcShelf, userId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
