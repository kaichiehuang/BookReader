package com.group5.BookRead.controllers;

import java.util.List;

import com.group5.BookRead.services.BookServiceSelector;
import com.group5.BookRead.services.bookAPI.BookAPI;
import com.group5.BookRead.services.bookAPI.BookFromAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchBookController {
    @Autowired
    BookAPI bookAPI;

    @Autowired
    BookServiceSelector bookServiceSelector;

    /**
     * Redirects to searchBook page
     * Uses externalBookAPI service to search books
     * @param query
     * @param model
     * @return searchbook html page
     */
    @GetMapping("/search")
    public String search(@RequestParam("term") final String query,
                         final Model model) {
//        SecurityContext context = SecurityContextHolder.getContext();
//        context.getAuthentication().getPrincipal();
        List<BookFromAPI> foundBooks = bookAPI.getBooks(query);
        model.addAttribute("books", foundBooks);
        return "searchBook";
    }

}
