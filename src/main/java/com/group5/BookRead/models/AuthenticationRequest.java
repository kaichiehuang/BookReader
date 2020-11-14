package com.group5.BookRead.models;

public class AuthenticationRequest {

    private String username;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * <p> Get username
     * </p>
     * @return username
     * @since 1.0
     */
    public String getUsername() {
        return username;
    }
    /**
     * <p> Set username
     * </p>
     * @param username
     * @since 1.0
     */
    public void setUsername(final String username) {
        this.username = username;
    }
    /**
     * <p> Get password
     * </p>
     * @since 1.0
     */
    public String getPassword() {
        return password;
    }
    /**
     * <p> Set password
     * </p>
     * @param password
     * @since 1.0
     */
    public void setPassword(final String password) {
        this.password = password;
    }
}
