package com.group5.BookRead.models;

public class User {

    private int id;
    private String usernme;
    private String password;

    public User() {
        super();
    }

    public User(final int id, final String usernme, final String password) {
        super();
        this.id = id;
        this.usernme = usernme;
        this.password = password;
    }

    public final int getId() {
        return id;
    }
    public final void setId(final int id) {
        this.id = id;
    }
    public final String getUsernme() {
        return usernme;
    }
    public final void setUsernme(final String usernme) {
        this.usernme = usernme;
    }
    public final String getPassword() {
        return password;
    }
    public final void setPassword(final String password) {
        this.password = password;
    }

    @Override
    public final String toString() {
        return "User [id=" + id + ", usernme="
                + usernme + ", password="
                + password + "]";
    }
}
