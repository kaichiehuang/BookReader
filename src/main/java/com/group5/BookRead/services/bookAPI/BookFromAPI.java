package com.group5.BookRead.services.bookAPI;

public class BookFromAPI {

    int page;
    String authors;
    String title;
    String description;
    String link;

    public BookFromAPI(final int page,
                       final String authors,
                       final String title,
                       final String description,
                       final String link) {
        this.page = page;
        this.authors = authors;
        this.title = title;
        this.description = description;
        this.link = link;
    }

    /**
     *  stringify the book obeject
     * @return
     */
    @Override
    public String toString() {
        return "BookFromAPI{"
                + "page=" + page
                + ", authors='" + authors + '\''
                + ", title='" + title + '\''
                + ", description='" + description + '\''
                + ", link='" + link + '\''
                + '}';
    }

    public String getAuthors() {
        if (authors.length() > 2)
            return authors.substring(1, authors.length()-1).replace("\"","");
        else
            return authors;
    }

    public String getTitle() {
        return title;
    }

    public int getPage() {
        return page;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
