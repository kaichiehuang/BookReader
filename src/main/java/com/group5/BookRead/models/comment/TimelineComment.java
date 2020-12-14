package com.group5.BookRead.models;

import java.sql.Timestamp;

public class TimelineComment {

    private int id;
    private String content; // cna be emoty if the type is like
    private String type; // like or comment
    private int userId;
    private int timelineId;
    private Timestamp timestamp;

    public TimelineComment(
            final int id,
            final String content,
            final String type,
            final int userId,
            final int timelineId,
            final Timestamp timestamp) {
        this.id = id;
        this.content = content;
        this.type = type;
        this.userId = userId;
        this.timelineId = timelineId;
        this.timestamp = timestamp;
    }

    public TimelineComment(
            final String content,
            final String type,
            final int userId,
            final int timelineId) {
        this.content = content;
        this.type = type;
        this.userId = userId;
        this.timelineId = timelineId;
    }

    /**
     *
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     */
    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     *
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     */
    public int getTimelineId() {
        return timelineId;
    }

    /**
     *
     * @param timelineId
     */
    public void setTimelineId(final int timelineId) {
        this.timelineId = timelineId;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "TimelineComment{"
                + "id=" + id
                + ", content='" + content + '\''
                + ", type='" + type + '\''
                + ", userId=" + userId
                + ", timelineId=" + timelineId + '}';
    }
}
