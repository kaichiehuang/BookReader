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

    public Bookshelf(final int id, final int userId, final String name) {
        super();
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public final int getId() {
        return id;
    }

    public final void setId(final int id) {
        this.id = id;
    }

    public final int getUserId() {
        return userId;
    }

    public final void setUserId(final int userId) {
        this.userId = userId;
    }

    public final String getName() {
        return name;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    @Override
    public final String toString() {
        return "Bookshelf [id=" + id + ", user_id=" + userId + ", "
                + "name=" + name + "]";
    }



}
