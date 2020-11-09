package com.group5.BookRead.controllers;

import java.util.*; 

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class BookRESTController extends BookBaseController{
    @PutMapping(value = "/book/shelf/{dst_shelf}", consumes = "application/json", produces = "application/json")
    public String moveBookInBookeshelf(
        @RequestBody final Map<String, String> json, 
        @PathVariable final String dst_shelf, HttpServletResponse response) {
        int book_id = Integer.parseInt(json.get("book_id"));
        String src_shelf = json.get("src_shelf");
        
        MockupBook book = BookBaseController.removeMockupBook(book_id, src_shelf);
        BookBaseController.bookshelfs.get(dst_shelf).add(book);
        
        response.setStatus(HttpServletResponse.SC_OK);

        return "{\"msg\":\"success\"}";
    }
}
