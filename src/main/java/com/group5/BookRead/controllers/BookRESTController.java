package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.group5.BookRead.services.user.Settings;
import com.group5.BookRead.services.user.UserService;
import com.group5.BookRead.services.user.UserSettings;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Settings settings;

    @Autowired
    private Settings sampleSettings;

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

            System.out.println("book " + book);
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

            System.out.println("book " + book);
            bookServiceSelector.addBookToShelf(book, "want to read",
                    userId);



            if (settings == null) {
                sampleSettings.setDefaultBookshelf(userId);
            }

            settings = sampleSettings.clone();

            System.out.println(settings.getDefaultBookshelf());


            if (!settings.getDefaultBookshelf().equals("want to read")){
                System.out.println("here!!! "+ settings.getDefaultBookshelf());
                bookServiceSelector.addBookToShelf(
                    book, settings.getDefaultBookshelf(),
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
     * @param name new ookshelf name
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

            System.out.println(shelf);

            userService.setDefalultBookshelf(userId, shelf);
            sampleSettings.setDefaultBookshelf(userId);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }







}
