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

    public Book(final int id, final String name, final String author,
            final int page, final String summary) {
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
    public final void setId(final int id) {
        this.id = id;
    }
    public final String getName() {
        return name;
    }
    public final void setName(final String name) {
        this.name = name;
    }
    public final String getAuthor() {
        return author;
    }
    public final void setAuthor(final String author) {
        this.author = author;
    }
    public final int getPage() {
        return page;
    }
    public final void setPage(final int page) {
        this.page = page;
    }
    public final String getSummary() {
        return summary;
    }
    public final void setSummary(final String summary) {
        this.summary = summary;
    }

    @Override
    public final String toString() {
        return "Book [id=" + id + ", name=" + name + ", author=" + author + ", "
                + "page=" + page + ", summary=" + summary
                + "]";
    }

}
