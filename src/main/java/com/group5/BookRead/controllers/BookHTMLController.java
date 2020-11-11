package com.group5.BookRead.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


// import com.group5.BookRead.services.BookService;

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
    public String getBookBookshelf(@RequestParam(name = "shelf", required = true,
        defaultValue = "All") final String shelf, final Model model) {
        // TODO: based on the implementation of bookshelf service
        Map<String, Book> bookshelfs = bookshelfServiceSelector.findAll(type);
        // List<MockupBook> books = get("Read");
        model.addAttribute("bookshelfs", bookshelfs);
        return "bookshelf";
    }
}
