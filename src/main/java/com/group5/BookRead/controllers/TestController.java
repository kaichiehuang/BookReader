package com.group5.BookRead.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {
    /**
     * <p>This is the test api
     * </p>
     * @param name query stirng for name
     * @param model spring boot model
     * @return value to be outputed
     * @since 1.0
     */
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false,
        defaultValue = "World") final String name, final Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }

}
