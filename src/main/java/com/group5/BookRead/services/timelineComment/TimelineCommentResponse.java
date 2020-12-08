package com.group5.BookRead.services.timelineComment;

import java.sql.Timestamp;

public class TimelineCommentResponse {
    private int id;
    private int userId;
    private String content;
    private String type;
    private Timestamp timestamp;
    private String username;

    public TimelineCommentResponse(
            final int id,
            final int userId,
            final String content,
            final String type,
            final Timestamp timestamp,
            final String username) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.timestamp = timestamp;
        this.username = username;
    }

    /**
     * Get timeline id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * set timeline id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * get user id
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * set user id
     * @param userId
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }

    /**
     * get comment content
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * set comment content
     * @param content
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     * get type "comment"
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * set type
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * get timestamp of comment
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     * set timestamp
     * @param timestamp
     */
    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * get username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * set username
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * tostring
     * @return
     */
    @Override
    public String toString() {
        return "TimelineCommentResponse{"
                + "id=" + id
                + ", userId="
                + userId + ", content='" + content + '\''
                + ", type='" + type + '\''
                + ", timestamp=" + timestamp
                + ", username='" + username + '\'' + '}';
    }
}
