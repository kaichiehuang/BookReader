package com.group5.BookRead.models.comment;

import java.sql.Timestamp;

public class Comment {

    private int id;
    private int userId;
    private int bookId;
    private int rating = 0;
    private String content;
    private Timestamp timestamp;

    public Comment(final int userId,
                       final int bookId,
                       final int rating,
                       final String text) {
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.content = text;
    }

    public Comment(final int id,
                       final int userId,
                       final int bookId,
                       final int rating,
                       final String text,
                       final Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.content = text;
        this.timestamp = timestamp;
    }

    /**
     * get id of the comment
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * get id of the user who writes the comment
     * @return
     */
    public int getUserId() {
        return userId;
    }


    /**
     * get the bookid of the comment
     * @return
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * get the rating
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     * get the text of the review
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * get the time when the review is created
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }



    /**
     *  to string
     * @return
     */
    @Override
    public String toString() {
        return "Comment{"
                + "id=" + id
                + ", userId=" + userId
                + ", bookId=" + bookId
                + ", rating=" + rating
                + ", content='" + content + '\''
                + ", timestamp=" + timestamp
                + '}';
    }
}

