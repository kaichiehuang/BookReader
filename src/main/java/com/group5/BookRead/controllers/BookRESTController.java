package com.group5.BookRead.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

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
        int bookId = Integer.parseInt(json.get("bookId"));
        String srcShelf = json.get("srcShelf");

        // MockupBook book = BookBaseController.removeMockupBook(bookId, srcShelf);
        // BookBaseController.bookshelfs.get(dstShelf).add(book);

        Book book = bookshelfServiceSelector.deleteBook(srcShelf, bookId, DUMMYID);
        bookshelfServiceSelector.addBook(dstShelf, book, DUMMYID);

        response.setStatus(HttpServletResponse.SC_OK);

        return "{\"msg\":\"success\"}";
    }
}
