package com.group5.BookRead.models;

public class User {

    private int id;
    private String usernme;
    private String password;

    public User() {
        super();
    }

    public User(int id, String usernme, String password) {
        super();
        this.id = id;
        this.usernme = usernme;
        this.password = password;
    }

    public final int getId() {
        return id;
    }
    public final void setId(int id) {
        this.id = id;
    }
    public final String getUsernme() {
        return usernme;
    }
    public final void setUsernme(String usernme) {
        this.usernme = usernme;
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
                + usernme + ", password="
                + password + "]";
    }
}
