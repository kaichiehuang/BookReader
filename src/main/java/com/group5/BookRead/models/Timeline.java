package com.group5.BookRead.models;

import java.sql.Timestamp;

public class Timeline {

    private int id;
    private int userId;
    private String content;
    private String type;
    private Timestamp timestamp;

    public Timeline(final int userId, final String content, final String type) {
        this.userId = userId;
        this.content = content;
        this.type = type;
    }

    public Timeline(final int id,
                    final int userId,
                    final String content,
                    final String type,
                    final Timestamp timestamp) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.timestamp = timestamp;
    }

    /**
     * tostring
     * @return
     */
    @Override
    public String toString() {
        return "{" + "id=" + id
                + ", userId=" + userId
                + ", content='" + content + '\''
                + ", type='" + type + '\''
                + ", timestamp=" + timestamp + '}';
    }

    /**
     * get the id of activity
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * get the userid of the activity
     * @return
     */
    public int getUserId() {
        return userId;
    }


    /**
     * get the content of activity
     * @return
     */
    public String getContent() {
        return content;
    }


    /**
     * get the type: progress or review
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * get the time the activity is posted
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

}
