package com.group5.BookRead.controllers;
import java.util.*;
import com.group5.BookRead.services.bookAPI.BookFromAPI;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SearchBookController {
    @GetMapping("/search")
    public String search(@RequestParam("term") String query, final Model model) {
        System.out.println(query);
        model.addAttribute("books", new BookFromAPI(500, "author", "title", "description", "link").toString());
        return "searchBook";
    }

}
