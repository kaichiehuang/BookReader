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
    /**  get book name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**  set book name
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
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
    public String getSummary() {
        return summary;
    }
    /**  set book summary
     * @param summary
     */
    public void setSummary(final String summary) {
        this.summary = summary;
    }

    /** serialize book into to string
     * @return string
     */
    @Override
    public String toString() {
        return "Book [id=" + id + ", name=" + name + ", author=" + author + ", "
                + "page=" + page + ", summary=" + summary
                + "]";
    }

}
