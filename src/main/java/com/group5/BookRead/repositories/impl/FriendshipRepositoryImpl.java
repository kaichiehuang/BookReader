package com.group5.BookRead.repositories.impl;

import com.group5.BookRead.models.Friendship;
import com.group5.BookRead.repositories.FriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Repository
public class FriendshipRepositoryImpl implements FriendshipRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class FriendshipRowMapper implements RowMapper<Friendship> {

        @Override
        public Friendship mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            Friendship friendship = new Friendship();
            friendship.setId(rs.getInt("id"));
            friendship.setFriendId(rs.getInt("friend_id"));
            friendship.setUserId(rs.getInt("user_id"));
            return friendship;
        }
    }

    /**  insert friendship
     * @param friendship
     * @return status code
     */
    @Override
    @Transactional
    public int insert(final Friendship friendship) throws
            SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into Friendship (user_id, "
            + "friend_id) " + "values(?, ?)",
                new Object[]{friendship.getUserId(),
                        friendship.getFriendId()});
    }

    /**  find all friendship of a user by userId
     * @param userId
     * @return friendships
     */
    @Override
    public List<Friendship> findAllByUserId(final int userId) {
        try {
            List<Friendship> friendships = jdbcTemplate.query(
                    "select * from Friendship" + " where user_id = ?",
                    new Object[] {userId},
                    new FriendshipRepositoryImpl.FriendshipRowMapper());
            return friendships;
        } catch (Exception e) {
            return null;
        }
    }
}
