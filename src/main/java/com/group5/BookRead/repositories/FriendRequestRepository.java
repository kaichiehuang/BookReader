package com.group5.BookRead.repositories;

import com.group5.BookRead.models.FriendRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface FriendRequestRepository {
    int insert(FriendRequest friendRequest)
            throws SQLIntegrityConstraintViolationException;
    FriendRequest findByUserIdAndRequestedFriendId(int userId,
                                                   int requestedFriendId);
    List<FriendRequest> findAllByUserId(int userId);
    int deleteByUserIdAndFriendId(int friendId, int userId);

    List<FriendRequest> findAllByFriendId(int userId);
}
