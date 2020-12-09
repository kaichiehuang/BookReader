package com.group5.BookRead.controllers;

import com.group5.BookRead.models.AuthenticationRequest;
import com.group5.BookRead.models.AuthenticationResponse;
import com.group5.BookRead.models.User;
import com.group5.BookRead.services.user.MyUserDetailsService;
import com.group5.BookRead.services.user.MyUserPrincipal;
import com.group5.BookRead.services.user.UserService;
import com.group5.BookRead.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
    private UserService userService;
    /**
     * Returns a jwt token after user logs in
     * @param authenticationRequest
     * @return jwt token
     */
    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(
            @RequestBody final AuthenticationRequest authenticationRequest)
            throws Exception {

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
                userService.findByUsername(
                        authenticationRequest.getUsername()));

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    /**
     * Sign up
     * @param newUser
     * @return jwt token
     */

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody final User newUser)
            throws Exception {
        User user = userService.createUser(
                newUser.getUsername(),
                newUser.getPassword());
        if (user != null) {
            final UserDetails userDetails = new MyUserPrincipal(user);
            final String jwt = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        }
        throw new Exception("Create new account failed");
    }



}
