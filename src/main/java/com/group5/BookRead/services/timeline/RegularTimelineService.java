package com.group5.BookRead.services.timeline;

import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.models.TimelineComment;
import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.TimelineRepository;
import com.group5.BookRead.services.timelineComment.TimelineCommentService;
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

    @Autowired
    TimelineCommentService timelineCommentService;

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
            User user = userService.findByUserId(
                    timeline.getUserId());
            String username = user.getUsername();
            List<TimelineComment> likes = timelineCommentService.
                    getTimelineCommentsByTimelineIdAndType(timeline.getId(),
                            "like");
            List<TimelineComment> comments = timelineCommentService.
                    getTimelineCommentsByTimelineIdAndType(timeline.getId(),
                            "comment");
            boolean liked = timelineCommentService.
                    getTimelineCommentsByTimelineIdAndUserId(
                    timeline.getId(), user.getId(), "like").size() > 0;

            res.add(new ResponseTimeline(
                    timeline.getId(),
                    timeline.getUserId(),
                    timeline.getContent(),
                    timeline.getType(),
                    timeline.getTimestamp(),
                    username, comments, liked, likes.size()
                    ));
        }
        return res;
    }
}
