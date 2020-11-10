package com.group5.BookRead.models;

public class Bookshelf {

    private int id;
    private int userId;
    private String name;

    public Bookshelf() {
        super();
        this.id = 0;
        this.userId = 0;
        this.name = "";

    }

    public Bookshelf(int id, int userId, String name) {
        super();
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public final void setId(int id) {
        this.id = id;
    }

    public final int getUserId() {
        return userId;
    }

    public final void setUserId(int userId) {
        this.userId = userId;
    }

    public final String getName() {
        return name;
    }

    public final void setName(String name) {
        this.name = name;
    }

    @Override
    public final String toString() {
        return "Bookshelf [id=" + id + ", user_id=" + userId + ", "
                + "name=" + name + "]";
    }



}
