package com.group5.BookRead.repositories.impl;

import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.repositories.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class TimelineRepositoryImpl implements TimelineRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class TimelineRowMapper implements RowMapper<Timeline> {

        @Override
        public Timeline mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {

            int id = rs.getInt("id");
            Timestamp time = rs.getTimestamp("time_created");
            int userId = rs.getInt("user_id");
            String type = rs.getString("type");
            String content = rs.getString("content");

            Timeline timeline = new Timeline(id, userId, content, type, time);
            return timeline;
        }
    }


    /**
     * insert timeline
     * @param timeline
     * @return
     * @throws SQLIntegrityConstraintViolationException
     */
    @Override
    public int insert(final Timeline timeline)
            throws SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into Timeline(user_id, content, type) "
                        + "values(?, ?, ?)",
                new Object[] {
                        timeline.getUserId(),
                        timeline.getContent(),
                        timeline.getType()}
            );
    }

    /**
     * Find timeline by ID
     * @param id
     * @return
     */
    @Override
    public Timeline findById(final int id) {
        try {
            Timeline timeline = jdbcTemplate.queryForObject("select * from Timeline "
                            + "where id = ?",
                    new Object[] {id},
                    new TimelineRowMapper());
            return timeline;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     *  fer all timelines
     * @return
     */
    @Override
    public List<Timeline> getTimelines() {
        try {
            List<Timeline> timelines = jdbcTemplate.query(
                    "select * from Timeline ",
                    new Object[] {},
                    new TimelineRowMapper());
            return timelines;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * find timeline
     * @param content
     * @param type
     * @param userId
     * @return
     */
    @Override
    public Timeline findByAll(final String content,
                              final String type,
                              final int userId) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from Timeline "
                            + "where user_id = ? and type = ? and content = ? ",
                    new Object[] {userId, type, content},
                    new TimelineRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
}
