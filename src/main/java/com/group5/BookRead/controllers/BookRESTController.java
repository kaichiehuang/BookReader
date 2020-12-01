package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.group5.BookRead.models.Book;

@RestController
public class BookRESTController extends BookController {
    /**
     * <p> move book between shelf restful api
     * </p>
     * @param json body object
     * @param dstShelf destination bookshelf
     * @param response response object
     * @return response message
     * @since 1.0
     */
    @PutMapping(value = "/book/shelf/{dstShelf}",
        consumes = "application/json", produces = "application/json")
    public String moveBookInBookeshelf(
        @RequestBody final Map<String, String> json,
        @PathVariable final String dstShelf,
        final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());
            int bookId = Integer.parseInt(json.get("bookId"));
            String srcShelf = json.get("srcShelf");

            // Book bookFromDb = bookServiceSelector.getBook(bookId);
            // bookServiceSelector.removeBook(bookId, srcShelf, userId);
            // bookServiceSelector.addBookToShelf(bookFromDb, dstShelf, userId);

            bookHelperService.moveBook(srcShelf, dstShelf, userId, bookId);
            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> move book between shelf restful api
     * </p>
     * @param book object
     * @param dstShelf destination bookshelf
     * @param response response object
     * @return response message
     * @since 1.0
     */
    @PostMapping(value = "/book/shelf/{dstShelf}",
        consumes = "application/json", produces = "application/json")
    public String addBook(
            @RequestBody final Book book,
            @PathVariable final String dstShelf,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

            System.out.println("book " + book);
            bookServiceSelector.addBookToShelf(book, dstShelf.toLowerCase(),
                userId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
