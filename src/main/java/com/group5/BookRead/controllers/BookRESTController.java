package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

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
        final HttpServletResponse response) {
        try {
            int bookId = Integer.parseInt(json.get("bookId"));
            String srcShelf = json.get("srcShelf");

            Book book = bookServiceSelector.removeBook(bookId, srcShelf,
                DUMMYID);
            bookServiceSelector.addBookToShelf(book, dstShelf, DUMMYID);

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * <p> move book between shelf restful api
     * </p>
     * @param json body object
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
        @RequestHeader("Authorization") final String auth,
        final HttpServletResponse response) {
        try {
            //System.out.println(auth);
            String[] splited = auth.split(" ");

            bookServiceSelector.addBookToShelf(book, dstShelf.toLowerCase(),
                Integer.parseInt(splited[1]));

            response.setStatus(HttpServletResponse.SC_OK);

            return "{\"msg\":\"success\"}";
        } catch (Exception e) {
            throw e;
        }
    }
}
