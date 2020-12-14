package com.group5.BookRead.services.comment;

import com.group5.BookRead.models.comment.Comment;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CommentService {

    List<ResponseComment> getComments(int bookId) throws Exception;
    Comment save(Comment comment)
            throws SQLIntegrityConstraintViolationException;
    Comment getComment(int userId, int bookId);

//    int insert(Comment comment);
//
//    Comment findById(int id);
}
