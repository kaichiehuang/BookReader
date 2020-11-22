package com.group5.BookRead.services.comment;

import com.group5.BookRead.models.Comment;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CommentService {

    List<Comment> getComments(int bookId) throws Exception;
    Comment save(Comment comment)
            throws SQLIntegrityConstraintViolationException;
    Comment getComment(int userId, int bookId);
    
}
