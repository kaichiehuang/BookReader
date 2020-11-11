package com.group5.BookRead.models;

public class User {

    private int id;
    private String username;
    private String password;

    public User() {
        super();
    }

    public User(int id, String usernme, String password) {
        super();
        this.id = id;
        this.username = usernme;
        this.password = password;
    }

    public final int getId() {
        return id;
    }
    public final void setId(int id) {
        this.id = id;
    }
    public final String getUsername() {
        return username;
    }
    public final void setUsername(String usernme) {
        this.username = usernme;
    }
    public final String getPassword() {
        return password;
    }
    public final void setPassword(String password) {
        this.password = password;
    }

    @Override
    public final String toString() {
        return "User [id=" + id + ", usernme="
                + username + ", password="
                + password + "]";
    }
}
