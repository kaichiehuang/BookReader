package com.group5.BookRead.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping
    public String defaultPage() {
        return "This is the default page without logging in.";
    }

    @GetMapping("/login")
    public String login() {
        return "if you see this you are logged in";
    }
}
