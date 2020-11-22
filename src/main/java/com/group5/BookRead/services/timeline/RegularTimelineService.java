package com.group5.BookRead.services.timeline;

import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.repositories.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class RegularTimelineService implements TimelineService {


    @Autowired
    TimelineRepository timelineRepository;

    /**
     * store a tomeline
     * @param timeline
     * @return
     */
    @Override
    public Timeline store(final Timeline timeline)
            throws SQLIntegrityConstraintViolationException {
        return timelineRepository.insert(timeline);
    }

    /**
     * get all timeline actvities
     * @return
     */
    @Override
    public List<Timeline> getTimelines() throws Exception {
        List<Timeline> ls = timelineRepository.getTimelines();
        if (ls == null) {
            throw new Exception("Error in getting timelines in DB");
        }
        return ls;
    }
}
