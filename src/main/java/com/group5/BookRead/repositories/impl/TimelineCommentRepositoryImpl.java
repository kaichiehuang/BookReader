package com.group5.BookRead.repositories.impl;

import com.group5.BookRead.models.comment.TimelineComment;
import com.group5.BookRead.repositories.TimelineCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.util.List;

@Repository
public class TimelineCommentRepositoryImpl
        implements TimelineCommentRepository {

    final int parameterONE = 1;
    final int parameterTWO = 2;
    final int parameterTHREE = 3;
    final int parameterFOUR = 4;

    @Autowired
    JdbcTemplate jdbcTemplate;

    class TimelineCommentRowMapper implements RowMapper<TimelineComment> {

        @Override
        public TimelineComment mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            int id = rs.getInt("id");
            Timestamp time = rs.getTimestamp("time_created");
            int userId = rs.getInt("user_id");
            String type = rs.getString("type");
            String content = rs.getString("content");
            int timelineId = rs.getInt("timeline_id");

            TimelineComment timelineComment = new TimelineComment(
                    id,
                    content,
                    type,
                    userId,
                    timelineId,
                    time);
            return timelineComment;
        }
    }

    /**
     *
     * @param timelineComment
     * @return
     * @throws SQLIntegrityConstraintViolationException
     */
    @Override
    @Transactional
    public int insert(
            final TimelineComment timelineComment)
            throws SQLIntegrityConstraintViolationException {

        KeyHolder holder = new GeneratedKeyHolder();
        String query =  "insert into TimelineComment(user_id, "
                + "timeline_id, content, type) "
                + "values(?, ?, ?, ?)";
        jdbcTemplate.update(new PreparedStatementCreator() {

            /**
             *
             * @param connection
             * @return
             * @throws SQLException
             */
            @SuppressWarnings("checkstyle:MagicNumber")
            @Override
            public PreparedStatement createPreparedStatement(
                    final Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(query,
                        Statement.RETURN_GENERATED_KEYS);
                ps.setInt(parameterONE, timelineComment.getUserId());
                ps.setInt(parameterTWO, timelineComment.getTimelineId());
                ps.setString(parameterTHREE, timelineComment.getContent());
                ps.setString(parameterFOUR, timelineComment.getType());
                return ps;
            }
        }, holder);
        return holder.getKey().intValue();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public TimelineComment findById(final int id) {

        try {
            TimelineComment timeline = jdbcTemplate.queryForObject(
                    "select * from TimelineComment "
                            + "where id = ?",
                    new Object[] {id},
                    new TimelineCommentRowMapper());
            return timeline;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * get commnets on ascd order
     * @param timelineId
     * @param type
     * @return
     */
    @Override
    public List<TimelineComment> getTimelineCommentsByTimelineId(
            final int timelineId, final String type) {
        try {
            List<TimelineComment> comments = jdbcTemplate.query(
                    "select * from TimelineComment "
                            + "where type = ? and timeline_id = ? "
                            + "ORDER BY time_created ASC",
                    new Object[] {type, timelineId},
                    new TimelineCommentRowMapper());
            return comments;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * no use as of now
     * @param timelineId
     * @param userId
     * @return
     */
    @Override
    public List<TimelineComment> getTimelineCommentsByTimelineIdAndUserId(
            final int timelineId, final int userId, final String type) {
        try {
            List<TimelineComment> comments = jdbcTemplate.query(
                    "select * from TimelineComment "
                            + "where user_id = ? "
                            + "and timeline_id = ? "
                            + "and type = ? ",
                    new Object[] {userId, timelineId, type},
                    new TimelineCommentRowMapper());
            System.out.println("timelineId liked by userId: " + comments);
            return comments;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * remove a comment
     * @param id
     * @return
     */
    @Override
    @Transactional
    public int removeById(final int id) {
        return jdbcTemplate.update(
                "delete from TimelineComment where id = ?",
                new Object[] {id});
    }
}
