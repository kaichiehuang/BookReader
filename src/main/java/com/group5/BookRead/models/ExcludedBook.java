package com.group5.BookRead.models;

public class ExcludedBook {

    private int id;
    private int userId;
    private int bookId;

    public ExcludedBook() {
        super();
    }
    public ExcludedBook(final int id, final int userId, final int bookId) {
        super();
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
    }
    /**  get excluded book id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**  set excluded book id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**  get excluded book user id
     * @return userId
     */
    public int getUserId() {
        return userId;
    }
    /**  set excluded book userId
     * @param userId
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }
    /**  get excluded book book id
     * @return bookId
     */
    public int getBookId() {
        return bookId;
    }
    /**  set excluded book bookId
     * @param bookId
     */
    public void setBookId(final int bookId) {
        this.bookId = bookId;
    }
    /**  serialize excluded book to string
     * @return string
     */
    @Override
    public String toString() {
        return "RecommendBook [id=" + id + ", userId="
                + userId + ", bookId=" + bookId + "]";
    }

}
