package com.group5.BookRead.services.timelineComment;

import com.group5.BookRead.models.TimelineComment;
import com.group5.BookRead.repositories.TimelineCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class RegularTimelineCommentService implements TimelineCommentService {



    @Autowired
    TimelineCommentRepository timelineCommentRepository;
    


    public RegularTimelineCommentService(final TimelineCommentRepository timelineCommentRepository) {
        this.timelineCommentRepository = timelineCommentRepository;
    }

    /**
     * save timeline comment
     * @param timelineComment
     * @return
     * @throws SQLIntegrityConstraintViolationException
     */
    @Override
    public TimelineComment save(final TimelineComment timelineComment)
            throws SQLIntegrityConstraintViolationException, Exception {
        List<TimelineComment> likes = getTimelineCommentsByTimelineIdAndUserId(
                timelineComment.getTimelineId(),
                timelineComment.getUserId(), "like");
        if (likes.size() > 0) {
            throw new Exception("You have liked the post");
        }
        int id = timelineCommentRepository.insert(timelineComment);
        return getTimelineCommentById(id);
    }

    /**
     * get comment by comment id
     * @param id
     * @return
     */
    @Override
    public TimelineComment getTimelineCommentById(final int id) {
        return timelineCommentRepository.findById(id);
    }

    /**
     * get all comments on the post
     * @param timelineId
     * @param type
     * @return
     */
    @Override
    public List<TimelineComment> getTimelineCommentsByTimelineIdAndType(
            final int timelineId, final String type) {
        return timelineCommentRepository.getTimelineCommentsByTimelineId(
                timelineId, type);
    }

    /**
     * get timeline comments from one user
     * @param timelineId
     * @param userId
     * @return
     */
    @Override
    public List<TimelineComment> getTimelineCommentsByTimelineIdAndUserId(
            final int userId, final int timelineId, final String type) {
        return timelineCommentRepository.
                getTimelineCommentsByTimelineIdAndUserId(
                timelineId, userId, type);
    }

    /**
     * remove likes only
     * @param userId
     * @param timelineId
     * @param type
     * @return
     */
    @Override
    public int removeById(final int userId,
                          final int timelineId,
                          final String type) throws Exception {
        List<TimelineComment> saved =
                getTimelineCommentsByTimelineIdAndUserId(
                        userId, timelineId, type);

        if (saved.size() == 0) {
            throw new Exception("You never liked");
        }
        TimelineComment comment = saved.get(0);
        return timelineCommentRepository.removeById(comment.getId());
    }
}
