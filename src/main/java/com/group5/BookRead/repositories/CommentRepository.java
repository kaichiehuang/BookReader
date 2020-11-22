package com.group5.BookRead.repositories;


import com.group5.BookRead.models.Comment;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface CommentRepository {

    Comment insert(Comment comment) throws SQLIntegrityConstraintViolationException;

    List<Comment> getCommentsByBookId(int bookId);

    Comment getCommentByUserIdAndBookId(int userId, int bookId);
}
