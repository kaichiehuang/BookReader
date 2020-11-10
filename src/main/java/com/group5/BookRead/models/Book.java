package com.group5.BookRead.models;

public class Book {

    private int id;
    private String name;
    private String author;
    private int page;
    private String summary = "No available summary";

    public Book() {
        super();
    }

    public Book(int id, String name, String author, int page, String summary) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.page = page;
        if (summary != null && summary != "") {
            this.summary = summary;
        }
    }

    public final int getId() {
        return id;
    }
    public final void setId(int id) {
        this.id = id;
    }
    public final String getName() {
        return name;
    }
    public final void setName(String name) {
        this.name = name;
    }
    public final String getAuthor() {
        return author;
    }
    public final void setAuthor(String author) {
        this.author = author;
    }
    public final int getPage() {
        return page;
    }
    public final void setPage(int page) {
        this.page = page;
    }
    public final String getSummary() {
        return summary;
    }
    public final void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public final String toString() {
        return "Book [id=" + id + ", name=" + name + ", author=" + author + ", "
                + "page=" + page + ", summary=" + summary
                + "]";
    }

}
