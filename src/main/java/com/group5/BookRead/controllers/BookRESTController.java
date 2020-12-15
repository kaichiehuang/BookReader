package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.group5.BookRead.services.settings.Settings;
import com.group5.BookRead.services.settings.UserSettings;
import com.group5.BookRead.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.group5.BookRead.models.Book;

@RestController
public class BookRESTController extends BookController {

    private Settings settings;

    @Autowired
    UserService userService;
  
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
    public String moveBookInBookshelf(
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

            bookServiceDecorator.moveBook(srcShelf, dstShelf, userId, bookId);
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
    public String moveBook(
            @RequestBody final Book book,
            @PathVariable final String dstShelf,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

            bookServiceSelector.addBookToShelf(book, dstShelf.toLowerCase(),
                userId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> add book to default bookshelf and want to read
     * </p>
     * @param book object
     * @param response response object
     * @return response message
     * @since 1.0
     */
    @PutMapping(value = "/book/shelf/addBook",
            consumes = "application/json", produces = "application/json")
    public String addBook(
            @RequestBody final Book book,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());

            bookServiceSelector.addBookToShelf(book, "want to read",
                    userId);

            settings = new UserSettings(userService, userId);

            UserSettings cloneSettings = (UserSettings) settings.clone();

            if (!cloneSettings.defaultBookshelf.equals("want to read")){
                bookServiceSelector.addBookToShelf(
                    book, cloneSettings.defaultBookshelf,
                    userId);
            }
            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> add customized book shelf
     * </p>
     * @param json
     * @param response response object
     * @return response message
     */
    @PostMapping(value = "/book/shelf/new",
        consumes = "application/json", produces = "application/json")
    public String addCustomizedBookshelf (
                @RequestBody final Map<String, String> json,
                final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                .getPrincipal().toString());

            String name = json.get("customShelfName");

            bookshelfServiceSelector.create(name, userId);
            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> Set bookshelf as default bookshelf
     * </p>
     * @param shelf new bookshelf name
     * @param response response object
     * @return response message
     */
    @PostMapping(value = "/book/shelf/setDefault/{shelf}",
            consumes = "application/json", produces = "application/json")
    public String setDefaultBookshelf(
            @PathVariable final String shelf,
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());

            userService.setDefalultBookshelf(userId, shelf);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * <p> Get default bookshelf
     * </p>
     * @param response response object
     * @return response message
     */
    @GetMapping(value = "/book/shelf/getDefault",
            consumes = "application/json", produces = "application/json")
    public String getDefaultBookshelf(
            final HttpServletResponse response) throws Exception {
        try {
            SecurityContext context = SecurityContextHolder.getContext();
            int userId = Integer.parseInt(context.getAuthentication()
                    .getPrincipal().toString());

            settings = new UserSettings(userService, userId);

            UserSettings cloneSettings = (UserSettings) settings.clone();
            String defaultBookshelf = cloneSettings.defaultBookshelf;

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"defaultBookshelf\":\""+defaultBookshelf+"\"}";
        } catch (Exception e) {
            throw e;
        }
    }

}
