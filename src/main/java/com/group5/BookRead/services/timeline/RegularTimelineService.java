package com.group5.BookRead.services.timeline;

import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.repositories.TimelineRepository;
import com.group5.BookRead.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegularTimelineService implements TimelineService {


    @Autowired
    TimelineRepository timelineRepository;

    @Autowired
    UserService  userService;

    /**
     * store a timeline
     * @param timeline
     * @return
     */
    @Override
    public Timeline store(final Timeline timeline)
            throws SQLIntegrityConstraintViolationException {
        if (timelineRepository.insert(timeline) == 1) {
            return timelineRepository.findByAll(
                    timeline.getContent(),
                    timeline.getType(),
                    timeline.getUserId());
        }
        return null;
    }

    /**
     * get all timeline actvities
     * @return
     */
    @Override
    public List<ResponseTimeline> getTimelines() throws Exception {
        List<Timeline> ls = timelineRepository.getTimelines();
        if (ls == null) {
            throw new Exception("Error in getting timelines in DB");
        }
        List<ResponseTimeline> res = new ArrayList<>();
        for (Timeline timeline : ls) {
            String username = userService.findByUserId(
                    timeline.getUserId()).getUsername();
            res.add(new ResponseTimeline(
                    timeline.getId(),
                    timeline.getUserId(),
                    timeline.getContent(),
                    timeline.getType(),
                    timeline.getTimestamp(),
                    username
                    ));
        }
        return res;
    }
}
