package com.group5.BookRead.controllers;

import java.util.Map;
import java.util.List;

import com.group5.BookRead.models.Bookshelf;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class ExcludeBookController extends BookController {

    /**
     * exclude a book
     * @param json body object
     * @param response response object
     * @return success msg in json
     */
    @PostMapping(value = "/book/exclude",
            consumes = "application/json", produces = "application/json")
    public String excludeBook(
            @RequestBody final Map<String, String> json,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();

            // get parameters
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());
            int bookId = Integer.parseInt(json.get("bookId"));

            // add to excluded
            bookServiceDecorator.addToExcluded(bookId, userId);
            // remove from all shelves
            List<Bookshelf> shelves = bookServiceDecorator
                    .getBookShelves(userId);
            for (int i = 0; i < shelves.size(); i++) {
                bookServiceDecorator.remove(
                        bookId, userId, shelves.get(i).getName());
            }

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
