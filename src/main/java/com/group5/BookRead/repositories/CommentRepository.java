package com.group5.BookRead.repositories;


import com.group5.BookRead.models.Comment;

import java.util.List;

public interface CommentRepository {

    int insert(Comment comment);

    Comment findById(int id);
    List<Comment> getCommentsByBookId(int bookId);

    Comment getCommentByUserIdAndBookId(int userId, int bookId);
}
