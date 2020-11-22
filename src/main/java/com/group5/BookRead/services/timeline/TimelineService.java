package com.group5.BookRead.services.timeline;

import com.group5.BookRead.models.Timeline;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface TimelineService {
    Timeline store(Timeline timeline)
            throws SQLIntegrityConstraintViolationException;
    List<Timeline> getTimelines() throws Exception;
}
