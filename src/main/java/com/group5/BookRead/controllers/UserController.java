package com.group5.BookRead.controllers;

import com.group5.BookRead.models.AuthenticationRequest;
import com.group5.BookRead.models.AuthenticationResponse;
import com.group5.BookRead.models.User;
import com.group5.BookRead.services.UserServiceSelector;
import com.group5.BookRead.services.user.MyUserDetailsService;
import com.group5.BookRead.services.user.MyUserPrincipal;
import com.group5.BookRead.services.user.UserService;
import com.group5.BookRead.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private UserServiceSelector userServiceSelector;

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword())
            );

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = new MyUserPrincipal(
                userServiceSelector.getUser(
                        authenticationRequest.getUsername()));

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User newUser) throws Exception {
        if (userServiceSelector.createUser(newUser)) {
            return "Success";
        }
        throw new Exception("Create new account failed");
    }



}
