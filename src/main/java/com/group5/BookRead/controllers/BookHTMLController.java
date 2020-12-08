package com.group5.BookRead.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;
import java.util.List;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.services.book.DecoratorChainException;

@Controller
public class BookHTMLController extends BookController {

    /**
     * <p>
     * get login page
     * </p>
     * 
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    /**
     * <p>
     * logout
     * </p>
     * 
     * @return view to be output
     * @since 1.0
     */
    @GetMapping("/logout")
    public String logout() {
        return "login";
    }

    /**
     * <p>
     * get Book shelf items
     * </p>
     * 
     * @param model Thymyleaf model
     * @return value to be outputed
     * @throws DecoratorChainException
     * @since 1.0
     */
    @GetMapping("/book/shelf")
    public String getBookBookshelf(final Model model) 
        throws DecoratorChainException {

        SecurityContext context = SecurityContextHolder.getContext();
        int userId = Integer.parseInt(context.getAuthentication()
            .getPrincipal().toString());


        Map<String, List<Book>> bookshelfs =
            bookServiceSelector.getBooksFromShelves(userId);
        //System.out.println(bookshelfs);
        model.addAttribute("bookshelfs", bookshelfs);
        return "bookshelf";
    }
}
