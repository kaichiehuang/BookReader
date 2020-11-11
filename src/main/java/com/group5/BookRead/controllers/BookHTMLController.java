package com.group5.BookRead.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.List;

import com.group5.BookRead.models.Book;

@Controller
public class BookHTMLController extends BookController {

    // // @Autowired
    // private BookService bookshelfService;
    /**
     * <p> get Book shelf items
     * </p>
     * @param name query stirng for name
     * @param model Thymyleaf model
     * @return value to be outputed
     * @since 1.0
     */
    @GetMapping("/book/shelf")
    public String getBookBookshelf(@RequestParam(name = "shelf",
        required = true, defaultValue = "All") final String shelf,
        final Model model) {

        Map<String, List<Book>> bookshelfs =
            bookServiceSelector.getBooksFromShelves(DUMMYID);
        System.out.println(bookshelfs);
        model.addAttribute("bookshelfs", bookshelfs);
        return "bookshelf";
    }
}
