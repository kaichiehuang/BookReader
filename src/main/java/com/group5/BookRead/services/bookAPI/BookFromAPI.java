package com.group5.BookRead.services.bookAPI;

public class BookFromAPI {

    int page;
    String authors;
    String title;
    String description;
    String link;

    /**
     * Constructor for creating Book from API
     * @param page
     * @param authors
     * @param title
     * @param description
     * @param link
     */
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
     *  stringify the book object
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

    /**
     * Get authors from BookFromAPI object
     * @return authors list without brackets and quotes
     */
    public String getAuthors() {
        if (authors.length() > 2) {
            return authors.substring(1, authors.length() - 1).replace("\"", "");
        } else {
            return authors;
        }
    }

    /**
     * Get title from BookFromAPI object
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get page from BookFromAPI object
     * @return page
     */
    public int getPage() {
        return page;
    }

    /**
     * Get description from BookFromAPI object
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get link from BookFromAPI object
     * @return link for image
     */
    public String getLink() {
        return link;
    }
}
