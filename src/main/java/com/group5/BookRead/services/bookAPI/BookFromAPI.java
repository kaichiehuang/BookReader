package com.group5.BookRead.services.bookAPI;

public class BookFromAPI {

    int page;
    String authors;
    String title;
    String description;
    String link;

    public BookFromAPI(int page, String authors, String title, String description, String link) {
        this.page = page;
        this.authors = authors;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    @Override
    public String toString() {
        return "BookFromAPI{" +
                "page=" + page +
                ", authors='" + authors + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
