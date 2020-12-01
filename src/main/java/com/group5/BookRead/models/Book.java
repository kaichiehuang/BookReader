package com.group5.BookRead.models;

public class Book {

    private int id;
    private String title;
    private String author;
    private int page;
    private String summary = "No available summary";
    private String bookIdentifier;
    private String link;
//        this.page = page;
//        this.authors = authors;
//        this.title = title;
//        this.description = description;
//        this.link = link;

    public Book() {
        super();
    }

    public Book(final int id, final String title, final String author,
            final int page, final String summary) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.page = page;
        if (summary != null && summary != "") {
            this.summary = summary;
        }
    }

    public Book(final String title, final String author,
            final int page, final String summary) {
        super();
        this.title = title;
        this.author = author;
        this.page = page;
        //System.out.println(page);
        if (summary != null && summary != "") {
            this.summary = summary;
        }
    }

    public Book(final String title, final String author,
                final int page,
                final String summary,
                final String bookIdentifier,
                final String link) {

        this.title = title;
        this.author = author;
        this.page = page;
        //System.out.println(page);
        if (summary != null && summary != "") {
            this.summary = summary;
        }
        this.bookIdentifier = bookIdentifier;
        this.link = link;
    }

    /**
     *
     * @return
     */
    public String getBookIdentifier() {
        return bookIdentifier;
    }

    /**
     *
     * @param bookIdentifier
     */
    public void setBookIdentifier(final String bookIdentifier) {
        this.bookIdentifier = bookIdentifier;
    }

    /**
     *
     * @return
     */
    public String getLink() {
        return link;
    }

    /**
     * set link
     * @param link
     */
    public void setLink(final String link) {
        this.link = link;
    }

    /**  get book id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**  set book id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**  get book title
     * @return title
     */
    public String getTitle() {
        return title;
    }
    /**  set book title
     * @param title
     */
    public void setTitle(final String title) {
        this.title = title;
    }
    /**  get book author
     * @return author
     */
    public String getAuthor() {
        return author;
    }
    /**  set book author
     * @param author
     */
    public void setAuthor(final String author) {
        this.author = author;
    }
    /**  get book total page
     * @return page
     */
    public int getPage() {
        return page;
    }
    /**  set book total page
     * @param page
     */
    public void setPage(final int page) {
        this.page = page;
    }
    /**  get book summary
     * @return summary
     */
    public String getDescription() {
        return summary;
    }
    /**  set book summary
     * @param summary
     */
    public void setDescription(final String summary) {
        this.summary = summary;
    }

    /** serialize book into to string
     * @return string
     */
    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author="
                + author + "," + "link=" + link + ", "
                + "page=" + page + ", summary=" + summary
                + "]";
    }

}
