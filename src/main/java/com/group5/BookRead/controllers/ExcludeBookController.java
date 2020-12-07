package com.group5.BookRead.controllers;

import java.util.Map;

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
            bookServiceSelector.addToExcluded(bookId, userId);
            // remove from all shelves
            bookServiceSelector.removeBook(bookId, "recommended", userId);
            bookServiceSelector.removeBook(bookId, "want to read", userId);
            bookServiceSelector.removeBook(bookId, "reading", userId);
            bookServiceSelector.removeBook(bookId, "read", userId);
            bookServiceSelector.removeBook(bookId, "favorites", userId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
