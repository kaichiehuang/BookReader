package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.group5.BookRead.services.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class ExcludeBookController extends BookController {

    @Autowired
    private UserService userService;

    /**
     * exclude a book
     * @param json body object
     * @param response response object
     * @return success msg in json
     */
    @PostMapping(value = "/book/exclude}",
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
//            bookServiceSelector.addToExcluded(bookId, userId);
            bookServiceSelector.removeBook(bookId, "recommended", userId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
