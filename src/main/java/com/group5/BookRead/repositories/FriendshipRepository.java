package com.group5.BookRead.repositories;

import com.group5.BookRead.models.Friendship;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface FriendshipRepository {
    int insert(Friendship friendship)
            throws SQLIntegrityConstraintViolationException;
    List<Friendship> findAllByUserId(int userId);

}
