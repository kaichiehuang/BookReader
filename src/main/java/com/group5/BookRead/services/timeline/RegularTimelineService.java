package com.group5.BookRead.services.timeline;

import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.models.comment.TimelineComment;
import com.group5.BookRead.models.User;
import com.group5.BookRead.repositories.TimelineRepository;
import com.group5.BookRead.services.friend.FriendshipService;
import com.group5.BookRead.services.timelineComment.TimelineCommentResponse;
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
    FriendshipService friendshipService;

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
     * get all timeline actvities for one user
     * @param userId the id of the user whose acticities are pulled
     * @param currentUser  the id of the current user
     * who pulls the activities of another user
     * @return timeline
     */
    @Override
    public List<ResponseTimeline> getTimelinesByUser(
            final int userId, final int currentUser)
            throws Exception {
        List<Timeline> ls = timelineRepository.getTimelinesByUserId(userId);
        if (ls == null) {
            throw new Exception("Error in getting timelines in DB");
        }
        return convert(ls, currentUser);
    }

    /**
     * @return
     */
    List<ResponseTimeline> convert(
            final List<Timeline> ls, final int userId) {
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


            List<TimelineCommentResponse> responseComments = new ArrayList<>();
            for (TimelineComment c : comments) {
                User commentBy = userService.findByUserId(
                        c.getUserId());
                TimelineCommentResponse cur = new TimelineCommentResponse(
                        c.getId(),
                        commentBy.getId(),
                        c.getContent(),
                        c.getType(),
                        c.getTimestamp(),
                        commentBy.getUsername());
                responseComments.add(cur);
            }

            List<TimelineComment> likedByUser = timelineCommentService
                    .getTimelineCommentsByTimelineIdAndUserId(
                            userId, timeline.getId(), "like");
            boolean liked = likedByUser.size() > 0;

            res.add(new ResponseTimeline(
                    timeline.getId(),
                    timeline.getUserId(),
                    timeline.getContent(),
                    timeline.getType(),
                    timeline.getTimestamp(),
                    username, responseComments, liked, likes.size()
            ));
        }
        return res;
    }

    /**
     * get all timeline actvities
     * @return
     */
    @Override
    public List<ResponseTimeline> getTimelines(final int userId)
            throws Exception {


        List<Integer> friendsIds = friendshipService.getFriendIds(userId);
        // include current user;
        friendsIds.add(userId);
        List<Timeline> ls = timelineRepository
                .getTimelinesByUserIds(friendsIds);
        if (ls == null) {
            throw new Exception("Error in getting timelines in DB");
        }

        return convert(ls, userId);
    }
}
