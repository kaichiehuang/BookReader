package com.group5.BookRead.repositories;

import com.group5.BookRead.models.TimelineComment;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface TimelineCommentRepository {

    int insert(TimelineComment timelineComment)
            throws SQLIntegrityConstraintViolationException;

    TimelineComment findById(int id);
    List<TimelineComment> getTimelineCommentsByTimelineId(
            int timelineId, String type);
    List<TimelineComment> getTimelineCommentsByTimelineIdAndUserId(
            int timelineId, int userId, String type);
    int removeById(int id);
}
