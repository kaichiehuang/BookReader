package com.group5.BookRead.services.comment;

import java.sql.Timestamp;

public class ResponseComment {
    private int id;
    private int userId;
    private int bookId;
    private int rating = 0;
    private String content;
    private Timestamp timestamp;
    private String username;

    public ResponseComment(
            final int id,
            final int userId,
            final int bookId,
            final int rating,
            final String content,
            final Timestamp timestamp,
            final String username) {
        this.id = id;
        this.userId = userId;
        this.bookId = bookId;
        this.rating = rating;
        this.content = content;
        this.timestamp = timestamp;
        this.username = username;

    }

    /**
     * Get id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Set id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Get userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Set userId
     * @param userId
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }

    /**
     * Get bookId
     * @return bookId
     */
    public int getBookId() {
        return bookId;
    }

    /**
     * Set bookId
     * @param bookId
     */
    public void setBookId(final int bookId) {
        this.bookId = bookId;
    }

    /**
     * Get rating
     * @return rating
     */
    public int getRating() {
        return rating;
    }

    /**
     * Set rating
     * @param rating
     */
    public void setRating(final int rating) {
        this.rating = rating;
    }

    /**
     * Get content
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Set content
     * @param content
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * Get timestamp
     * @return timestamp
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * Set timestamp
     * @param timestamp
     */
    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Get username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Set username
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * string
     * @return responseComment
     */
    @Override
    public String toString() {
        return "ResponseComment{"
                + "id=" + id
                + ", userId=" + userId
                + ", bookId=" + bookId
                + ", rating=" + rating
                + ", content='" + content + '\''
                + ", timestamp=" + timestamp
                + ", username='" + username + '\''
                + '}';
    }
}
