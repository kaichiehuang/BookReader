package com.group5.BookRead.models;

public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(final String jwt) {
        this.jwt = jwt;
    }
    /**
     * <p> Get jwt token
     * </p>
     * @since 1.0
     */
    public String getJwt() {
        return jwt;
    }
}
