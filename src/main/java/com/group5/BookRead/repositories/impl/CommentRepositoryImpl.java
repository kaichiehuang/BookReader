package com.group5.BookRead.repositories.impl;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.Comment;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
            String text = rs.getString("text");
            Timestamp time = rs.getTimestamp("timestamp");
            int userId = rs.getInt("userId");
            int bookId = rs.getInt("bookId");
            Comment comment = new Comment(id, userId, bookId, rating, text, time);
            return comment;
        }

    }


    /**
     * create a new comment
     * @param comment
     * @return
     * @throws SQLIntegrityConstraintViolationException
     */
    @Override
    public Comment insert(final Comment comment) throws SQLIntegrityConstraintViolationException {
        return jdbcTemplate.queryForObject(
                "insert into Comment(userId, bookId, rating, text) "
                        + "values(?, ?, ?, ?) returning *",
                new Object[] {
                        comment.getUserId(),
                        comment.getBookId(),
                        comment.getRating(),
                        comment.getText()}, new CommentRowMapper());
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
                    "select * from Comment " + "where book_id = ?",
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
    public Comment getCommentByUserIdAndBookId(final int userId,
                                               final int bookId)
            throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject("select * "
                        + "from Comment "
                        + "where userId = ? and bookId = ?",
                        new Object[] {userId, bookId},
                        new CommentRowMapper());
    }
}
