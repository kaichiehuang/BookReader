package com.group5.BookRead.models;

public class MyBook {

    private int id;
    private int bookId;
    private int userId;
    private int bookshelfId;
    private double progress = 0;

    public MyBook() {}

    public MyBook(
            final int id,
            final int bookId,
            final int uId,
            final int shelfId,
            final double progress) {
        this.id = id;
        this.bookId = bookId;
        this.userId = uId;
        this.bookshelfId = shelfId;
        this.progress = progress;
    }
    /*
        new constructor
     */
    public MyBook(final int bookId,
                  final int userId,
                  final int bookshelfId,
                  final double progress) {
        this.bookId = bookId;
        this.userId = userId;
        this.bookshelfId = bookshelfId;
        this.progress = progress;
    }

    public final int getId() {
        return id;
    }
    /**  set mybook id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**  get mybook's bookId
     * @return id
     */
    public int getBookId() {
        return bookId;
    }
    /**  set mybook's bookId
     * @param bookId
     */
    public void setBookId(final int bookId) {
        this.bookId = bookId;
    }
    /**  get mybook's userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }
    /**  set mybook's userId
     * @param bookId
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }
    /**  get mybook's bookshelfId
     * @return bookshelfId
     */
    public int getBookshelfId() {
        return bookshelfId;
    }
    /**  set mybook's userId
     * @param bookId
     */
    public void setBookshelfId(final int bookshelfId) {
        this.bookshelfId = bookshelfId;
    }
    /**  get mybook's progress
     * @return progress
     */
    public double getProgress() {
        return progress;
    }
    /**  set mybook's progress
     * @param progress
     */
    public void setProgress(final double progress) {
        this.progress = progress;
    }

    /**  serialize mybook to string
     * @return string
     */
    @Override
    public String toString() {
        return "MyBook [id=" + id + ", bookId=" + bookId
                + ", userId=" + userId + ", bookshelfId="
                + bookshelfId + ", progress=" + progress + "]";
    }
}

