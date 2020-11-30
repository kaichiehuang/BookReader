package com.group5.BookRead.services.friend;

import com.group5.BookRead.models.FriendRequest;
import com.group5.BookRead.models.Friendship;
import com.group5.BookRead.repositories.FriendRequestRepository;
import com.group5.BookRead.repositories.FriendshipRepository;
import com.group5.BookRead.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "friend")
public class FriendshipService {

    @Autowired
    private FriendshipRepository friendshipRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    public FriendshipService(final FriendshipRepository friendshipRepository) {
        this.friendshipRepository = friendshipRepository;
    }

    /**
     *  Get a list of user's friend by userId
     * @param userId
     * @return List<String>
     */
    public List<String> getFriends(final int userId) {
        List<Friendship> friendships =
                friendshipRepository.findAllByUserId(userId);

        System.out.println(friendships);

        List<String> friends = new ArrayList<>();

        for (Friendship friendship : friendships) {
            friends.add(userRepository.findById(friendship.getFriendId()).getUsername());
        }
        return friends;
    }

    /**
     *  Request a friendship to another user
     * @param userId
     * @param requestFriendName
     * @return FriendRequest
     *
     */
    public FriendRequest requestFriendship(final int userId, final String requestFriendName) {
        try {
            int requestedFriendId = userRepository.findIdByUsername(requestFriendName);
            FriendRequest friendRequest = new FriendRequest(userId, requestedFriendId);
            friendRequestRepository.insert(friendRequest);
            FriendRequest storedFriendRequest = friendRequestRepository.findByUserIdAndRequestedFriendId(userId, requestedFriendId);
            return storedFriendRequest;
        } catch (SQLIntegrityConstraintViolationException throwables) {
            return null;
        }
    }

    /**
     *  accept friendship
     * @param userId
     * @param acceptedFriendName
     * @return void
     *
     */
    public void acceptFriendship(final int userId, final String acceptedFriendName) throws SQLIntegrityConstraintViolationException, UserDidNotRequestToBeFriendException {
        int acceptedFriendId = userRepository.findIdByUsername(acceptedFriendName);
        //first delete entry from friend request
        if (friendRequestRepository.deleteByUserIdAndAcceptedFriendId(acceptedFriendId, userId) == 0) {
            throw new UserDidNotRequestToBeFriendException(acceptedFriendName + " did not request to be a friend with you");
        }
        //add each other as friend
        Friendship f1 = new Friendship(userId, acceptedFriendId);
        Friendship f2 = new Friendship(acceptedFriendId, userId);
        friendshipRepository.insert(f1);
        friendshipRepository.insert(f2);
    }
}
