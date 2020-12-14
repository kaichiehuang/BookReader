package com.group5.BookRead.repositories;


import com.group5.BookRead.models.comment.Comment;

import java.util.List;

public interface CommentRepository {

    int insert(Comment comment);

    Comment findById(int id);
    List<Comment> getCommentsByBookId(int bookId);

    List<Comment> getCommentsByUserIdAndBookId(int userId, int bookId);
}
