package com.group5.BookRead.repositories;

import com.group5.BookRead.models.Timeline;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface TimelineRepository {
    int insert(Timeline timeline)
            throws SQLIntegrityConstraintViolationException;

    Timeline findById(int id);
    List<Timeline> getTimelines();

    Timeline findByAll(String content, String type, int userId);
    List<Timeline> getTimelinesByUserIds(List<Integer> ids);
    List<Timeline> getTimelinesByUserId(int id);

}
