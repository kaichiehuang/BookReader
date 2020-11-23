package com.group5.BookRead.repositories.impl;

import com.group5.BookRead.models.Timeline;
import com.group5.BookRead.repositories.TimelineRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            Timestamp time = rs.getTimestamp("created_time");
            int userId = rs.getInt("user_id");
            String type = rs.getString("type");
            String content = rs.getString("content");

            Timeline timeline = new Timeline(id, userId, content, type, time);
            return timeline;
        }

    }

    /**
     *  store the timeline
     * @param timeline
     * @return
     * @throws SQLIntegrityConstraintViolationException
     */
    @Override
    public Timeline insert(final Timeline timeline)
            throws SQLIntegrityConstraintViolationException {
        return jdbcTemplate.queryForObject(
                "insert into Timeline(user_id, content, type) "
                        + "values(?, ?, ?)",
                new Object[] {
                        timeline.getUserId(),
                        timeline.getContent(),
                        timeline.getType()},
                new TimelineRowMapper());
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
}
