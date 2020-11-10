package com.group5.BookRead.controllers;
import java.util.*;

import com.group5.BookRead.services.ExternalBookAPI;
import com.group5.BookRead.services.bookAPI.BookFromAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SearchBookController {
    @Autowired
    ExternalBookAPI externalBookAPI;

    @GetMapping("/search")
//    @ResponseStatus(HttpStatus.OK)
//    @ResponseBody
    public String search(@RequestParam("term") String query, final Model model) {
        System.out.println(query);
        List<BookFromAPI> foundBooks = externalBookAPI.searchBook(query);
        System.out.println("num books: " + foundBooks.size());
        BookFromAPI secondBook = foundBooks.get(1);
        System.out.println(secondBook.title);
        model.addAttribute("books", foundBooks);
        return "searchBook";
    }

}
