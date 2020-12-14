package com.group5.BookRead.services.timelineComment;

import com.group5.BookRead.models.comment.TimelineComment;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface TimelineCommentService {

    TimelineComment save(TimelineComment timelineComment) throws Exception,
            SQLIntegrityConstraintViolationException;
    TimelineComment getTimelineCommentById(int id);
    List<TimelineComment> getTimelineCommentsByTimelineIdAndType(
            int timelineId, String type);
    List<TimelineComment> getTimelineCommentsByTimelineIdAndUserId(
            int timelineId, int userId, String type);
    int removeById(int userId, int timelineId, String type) throws Exception;
}
