package com.group5.BookRead.repositories;

import com.group5.BookRead.models.Timeline;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface TimelineRepository {
    Timeline insert(Timeline timeline)
            throws SQLIntegrityConstraintViolationException;

    List<Timeline> getTimelines();

}
