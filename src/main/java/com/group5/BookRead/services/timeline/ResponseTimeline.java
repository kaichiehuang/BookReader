package com.group5.BookRead.services.timeline;

import com.group5.BookRead.services.comment.timelineComment.TimelineCommentResponse;

import java.sql.Timestamp;
import java.util.List;

public class ResponseTimeline {
    private int id;
    private int userId;
    private String content;
    private String type;
    private Timestamp timestamp;
    private String username;
    private List<TimelineCommentResponse> commentList;
    private boolean isLiked;
    private int likes;

    /**
     * @return
     */
    public ResponseTimeline(
            final int id,
            final int userId,
            final String content,
            final String type,
            final Timestamp timestamp,
            final String username,
            final List<TimelineCommentResponse> commentList,
            final boolean isLiked,
            final int likes) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.type = type;
        this.timestamp = timestamp;
        this.username = username;
        this.commentList = commentList;
        this.isLiked = isLiked;
        this.likes = likes;
    }

    /**
     *
     * @return
     */
    public boolean isLiked() {
        return isLiked;
    }

    /**
     *
     * @param liked
     */
    public void setLiked(final boolean liked) {
        isLiked = liked;
    }

    /**
     *
     * @return
     */
    public int getLikes() {
        return likes;
    }

    /**
     *
     * @param likes
     */
    public void setLikes(final int likes) {
        this.likes = likes;
    }

    /**
     * get comment list
     * @return
     */
    public List<TimelineCommentResponse> getCommentList() {
        return commentList;
    }

    /**
     * set comment list
     * @param commentList
     */
    public void setCommentList(
            final List<TimelineCommentResponse> commentList) {
        this.commentList = commentList;
    }

    /**
     *id
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * content
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * type
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     *timestamp
     * @return
     */
    public Timestamp getTimestamp() {
        return timestamp;
    }

    /**
     *  username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * set id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * set userid
     * @param userId
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }

    /**
     * set content
     * @param content
     */
    public void setContent(final String content) {
        this.content = content;
    }

    /**
     *  set type
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     *  set timestamp
     * @param timestamp
     */
    public void setTimestamp(final Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *  set username
     * @param username
     */
    public void setUsername(final String username) {
        this.username = username;
    }


    /**
     * to string
     */
    @Override
    public String toString() {
        return "ResponseTimeline{"
                + "id=" + id
                + ", userId=" + userId
                + ", content='" + content + '\''
                + ", type='" + type + '\''
                + ", timestamp=" + timestamp
                + ", username='" + username + '\''
                + ", commentList=" + commentList
                + ", isLiked=" + isLiked
                + ", likes=" + likes + '}';
    }
}
