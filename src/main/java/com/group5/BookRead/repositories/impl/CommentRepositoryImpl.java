package com.group5.BookRead.repositories.impl;
import com.group5.BookRead.models.comment.Comment;
import com.group5.BookRead.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class CommentRowMapper implements RowMapper<Comment> {

        @Override
        public Comment mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {

            int id = rs.getInt("id");
            int rating = rs.getInt("rating");
            String text = rs.getString("content");
            Timestamp time = rs.getTimestamp("time_created");
            int userId = rs.getInt("user_id");
            int bookId = rs.getInt("book_id");
            Comment comment = new Comment(
                    id,
                    userId,
                    bookId,
                    rating,
                    text,
                    time);
            return comment;
        }

    }

    /**
     * query for all comments
     * @param bookId
     * @return
     */
    @Override
    public List<Comment> getCommentsByBookId(final int bookId) {
        try {
            List<Comment> comments = jdbcTemplate.query(
                    "select * from Comment " + "where book_id = ? "
                            + "ORDER BY time_created DESC",
                    new Object[] {bookId},
                    new CommentRowMapper());
            return comments;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * get comment of a user on a book
     * @param userId
     * @param bookId
     * @return
     * @throws EmptyResultDataAccessException
     */
    @Override
    public List<Comment> getCommentsByUserIdAndBookId(final int userId,
                                               final int bookId)
            throws EmptyResultDataAccessException {
        return jdbcTemplate.query("select * "
                        + "from Comment "
                        + "where user_id = ? and book_id = ? "
                        + "ORDER BY time_created DESC",
                        new Object[] {userId, bookId},
                        new CommentRowMapper());
    }
    /**
     * insert comment
     * @param comment
     * @return
     */
    @Override
    @Transactional
    public int insert(final Comment comment) {
        return jdbcTemplate.update(
                "insert into Comment(user_id, book_id, rating, content) "
                        + "values(?, ?, ?, ?)",
                new Object[] {
                        comment.getUserId(),
                        comment.getBookId(),
                        comment.getRating(),
                        comment.getContent(),
                });
    };
    /**
     * Find comment by ID
     * @param id
     * @return
     */
    @Override
    public Comment findById(final int id) {
        try {
            Comment comment = jdbcTemplate.queryForObject(
                    "select * from Comment "
                            + "where id = ?",
                    new Object[]{id},
                    new CommentRowMapper());
            return comment;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
