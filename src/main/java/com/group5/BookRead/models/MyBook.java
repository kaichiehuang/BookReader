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

    public MyBook(final int id, final int bookId, final int uId,
            final int shelfId, final double progress) {
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
    public final void setId(final int id) {
        this.id = id;
    }
    public final int getBookId() {
        return bookId;
    }
    public final void setBookId(final int bookId) {
        this.bookId = bookId;
    }
    public final int getUserId() {
        return userId;
    }
    public final void setUserId(final int userId) {
        this.userId = userId;
    }
    public final int getBookshelfId() {
        return bookshelfId;
    }
    public final void setBookshelfId(final int bookshelfId) {
        this.bookshelfId = bookshelfId;
    }
    public final double getProgress() {
        return progress;
    }
    public final void setProgress(final double progress) {
        this.progress = progress;
    }

    @Override
    public final String toString() {
        return "MyBook [id=" + id + ", bookId=" + bookId
                + ", userId=" + userId + ", bookshelfId="
                + bookshelfId + ", progress=" + progress + "]";
    }

}
