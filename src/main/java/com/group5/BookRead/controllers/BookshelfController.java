package com.group5.BookRead.controllers;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
public class BookshelfController extends BookController {

    /**
     * <p> add customized book shelf
     * </p>
     * @param name new ookshelf name
     * @param response response object
     * @return response message
     */
    @PostMapping(value = "/book/shelf/new",
            consumes = "application/json", produces = "application/json")
        public String addCustomizedBookshelf(
                @RequestBody final String name,
                final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

            bookshelfServiceSelector.create(name, userId);
            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
