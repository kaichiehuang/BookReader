package com.group5.BookRead.repositories.impl;

import com.group5.BookRead.models.FriendRequest;
import com.group5.BookRead.repositories.FriendRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public class FriendRequestRepositoryImpl implements FriendRequestRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class FriendRequestRowMapper implements RowMapper<FriendRequest> {
        @Override
        public FriendRequest mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            FriendRequest friendRequest = new FriendRequest();
            friendRequest.setId(rs.getInt("id"));
            friendRequest.setRequestedFriendId(rs.getInt("requestedFriend_id"));
            friendRequest.setUserId(rs.getInt("user_id"));
            return friendRequest;
        }
    }

    /**  insert friend request
     * @param friendRequest
     * @return status code
     */
    @Override
    public int insert(final FriendRequest friendRequest)
            throws SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into FriendRequest (user_id, "
                        + "requestedFriend_id) " + "values(?, ?)",
                new Object[] {friendRequest.getUserId(),
                        friendRequest.getRequestedFriendId()});
    }

    /**  find a friend request object with userId and requestedFriendId
     * @param userId
     * @param requestedFriendId
     * @return FriendRequest
     */
    @Override
    public FriendRequest findByUserIdAndRequestedFriendId(
            final int userId, final int requestedFriendId) {
        try {
            FriendRequest friendRequest = jdbcTemplate.queryForObject(
                    "select * from FriendRequest " + "where user_id = ?"
                            + " and requestedFriend_id = ?",
                    new Object[] {userId, requestedFriendId},
                    new FriendRequestRepositoryImpl.FriendRequestRowMapper());
            return friendRequest;
        } catch (Exception e) {
            return null;
        }
    }

    /**  find all friend request object of one user with userId
     * @param userId
     * @return List<FriendRequest>
     */
    @Override
    public List<FriendRequest> findAllByUserId(final int userId) {
        try {
            List<FriendRequest> friendRequests = jdbcTemplate.query("select * "
                            + "from FriendRequest " + "where user_id = ?",
                    new Object[] {userId},
                    new FriendRequestRepositoryImpl.FriendRequestRowMapper());
            return friendRequests;
        } catch (Exception e) {
            return null;
        }
    }

    /**  delete a friend request with accepted user id and user id
     * @param acceptedUserId
     * @param userId
     * @return status code
     */
    @Override
    public int deleteByUserIdAndFriendId(
            final int friendId, final int userId) {
        return jdbcTemplate.update("delete from FriendRequest "
                        + "where user_id = ? and requestedFriend_id = ?",
                    new Object[] {friendId, userId});
    }

    /**  find all friend request object towards one user with userId
     * @param userId
     * @return List<FriendRequest>
     */
    @Override
    public List<FriendRequest> findAllByFriendId(final int userId) {
        try {
            List<FriendRequest> friendRequests = jdbcTemplate.query("select * "
                            + "from FriendRequest "
                            + "where requestedFriend_id = ?",
                    new Object[] {userId},
                    new FriendRequestRepositoryImpl.FriendRequestRowMapper());
            return friendRequests;
        } catch (Exception e) {
            return null;
        }
    }

}
