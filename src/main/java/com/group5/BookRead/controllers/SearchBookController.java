package com.group5.BookRead.controllers;
import java.util.*;

import com.group5.BookRead.services.ExternalBookAPI;
import com.group5.BookRead.services.bookAPI.BookFromAPI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SearchBookController {
    @Autowired
    ExternalBookAPI externalBookAPI;

    @GetMapping("/search")
    public String search(@RequestParam("term") String query, final Model model) {
        List<BookFromAPI> foundBooks = externalBookAPI.searchBook(query);
        model.addAttribute("books", foundBooks);
        return "searchBook";
    }

}
