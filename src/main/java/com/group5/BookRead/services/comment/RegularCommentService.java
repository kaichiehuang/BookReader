package com.group5.BookRead.services.comment;

import com.group5.BookRead.models.Comment;
import com.group5.BookRead.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class RegularCommentService implements CommentService {


    @Autowired
    CommentRepository commentRepository;

    /**
     * get comments on the book
     * @param bookId
     * @return
     */
    @Override
    public List<Comment> getComments(final int bookId) throws Exception {
        List<Comment> comments = commentRepository.getCommentsByBookId(bookId);
        if (comments == null) {
            throw new Exception("can not load comments");
        }
        return comments;
    }

    /**
     * Create a comment on the book
     * @param comment
     * @return
     */
    @Override
    public Comment save(final Comment comment)
            throws SQLIntegrityConstraintViolationException {

        commentRepository.insert(comment);
        return commentRepository.getCommentByUserIdAndBookId(
                comment.getUserId(),
                comment.getBookId());
    }

    /**
     * get the comment created by the user on the book
     * @param userId
     * @param bookId
     * @return
     */
    @Override
    public Comment getComment(final int userId, final int bookId) {
        return commentRepository.getCommentByUserIdAndBookId(userId, bookId);
    }
}
