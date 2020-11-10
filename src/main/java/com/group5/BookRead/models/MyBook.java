package com.group5.BookRead.models;

public class MyBook {

    private int id;
    private int bookId;
    private int userId;
    private int bookshelfId;
    private double progress = 0;

    public MyBook() {
        super();
    }

    public MyBook(int id, int bookId, int uId, int shelfId, double progress) {
        super();
        this.id = id;
        this.bookId = bookId;
        this.userId = uId;
        this.bookshelfId = shelfId;
        this.progress = progress;
    }

    public final int getId() {
        return id;
    }
    public final void setId(int id) {
        this.id = id;
    }
    public final int getBookId() {
        return bookId;
    }
    public final void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public final int getUserId() {
        return userId;
    }
    public final void setUserId(int userId) {
        this.userId = userId;
    }
    public final int getBookshelfId() {
        return bookshelfId;
    }
    public final void setBookshelfId(int bookshelfId) {
        this.bookshelfId = bookshelfId;
    }
    public final double getProgress() {
        return progress;
    }
    public final void setProgress(double progress) {
        this.progress = progress;
    }

    @Override
    public final String toString() {
        return "MyBook [id=" + id + ", bookId=" + bookId
                + ", userId=" + userId + ", bookshelfId="
                + bookshelfId + ", progress=" + progress + "]";
    }

}
