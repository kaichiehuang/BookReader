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
    
    public FriendshipService(final FriendshipRepository friendshipRepository, final UserRepository userRepository) {
        this.friendshipRepository = friendshipRepository;
        this.userRepository = userRepository;
    }

    /**
     *  Get a list of user's friend by userId
     * @param userId
     * @return List<String>
     */
    public List<String> getFriends(final int userId) {
        List<Friendship> friendships =
                friendshipRepository.findAllByUserId(userId);
        

        List<String> friends = new ArrayList<>();

        for (Friendship friendship : friendships) {

            friends.add(userRepository.findById(friendship.
                    getFriendId()).getUsername());
        }
        return friends;
    }


    /**
     *  Get a list of user's friend by userId
     * @param userId
     * @return List<Integer>
     */
    public List<Integer> getFriendIds(final int userId) {
        List<Friendship> friendships =
                friendshipRepository.findAllByUserId(userId);
        List<Integer> friends = new ArrayList<>();

        for (Friendship friendship : friendships) {
            friends.add(friendship.getFriendId());
        }
        return friends;
    }

    /**
     *  Get a list of user's requested friends by userId
     * @param userId
     * @return List<String>
     */
    public List<String> getAllRequestedFriends(final int userId) {
        List<FriendRequest> friendRequests =
                friendRequestRepository.findAllByUserId(userId);

        List<String> friends = new ArrayList<>();

        for (FriendRequest friendRequest : friendRequests) {
            friends.add(userRepository.findById(friendRequest.
                    getRequestedFriendId()).getUsername());
        }
        return friends;
    }

    /**
     *  Get a list of user's requested friends by userId
     * @param userId
     * @return List<String>
     */
    public List<String> getAllFriendRequestsFromOthers(final int userId) {
        List<FriendRequest> friendRequests =
                friendRequestRepository.findAllByFriendId(userId);

        List<String> friends = new ArrayList<>();

        for (FriendRequest friendRequest : friendRequests) {
            friends.add(userRepository.findById(friendRequest.
                    getUserId()).getUsername());
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
    public FriendRequest requestFriendship(final int userId,
                                           final String requestFriendName) {
        try {
            int requestedFriendId =
                    userRepository.findIdByUsername(requestFriendName);
            FriendRequest friendRequest =
                    new FriendRequest(userId, requestedFriendId);
            if (friendRequestRepository.findByUserIdAndRequestedFriendId(
                    userId, requestedFriendId) != null) {
                throw new AlreadyRequestedException(
                        "Already send friend request to " + requestFriendName);
            }

            friendRequestRepository.insert(friendRequest);
            FriendRequest storedFriendRequest =
                    friendRequestRepository.findByUserIdAndRequestedFriendId(
                            userId, requestedFriendId);
            return storedFriendRequest;
        } catch (Exception e) {
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
    public void acceptFriendship(final int userId,
                                 final String acceptedFriendName)
            throws SQLIntegrityConstraintViolationException,
            UserDidNotRequestToBeFriendException {
        int acceptedFriendId =
                userRepository.findIdByUsername(acceptedFriendName);
        //first delete entry from friend request
        if (friendRequestRepository.
            deleteByUserIdAndFriendId(acceptedFriendId,
                        userId) == 0) {
            throw new UserDidNotRequestToBeFriendException(acceptedFriendName
                    + " did not request to be a friend with you");
        }
        //add each other as friend
        Friendship f1 = new Friendship(userId, acceptedFriendId);
        Friendship f2 = new Friendship(acceptedFriendId, userId);
        friendshipRepository.insert(f1);
        friendshipRepository.insert(f2);
    }

    /**
     * reject friendship
     * @param userId
     * @param rejectFriendName
     * @return void
     *
     */
    public void rejectFriendship(final int userId,
                                 final String rejectFriendName)
            throws SQLIntegrityConstraintViolationException,
            UserDidNotRequestToBeFriendException {
        int rejectFriendId =
                userRepository.findIdByUsername(rejectFriendName);
        //first delete entry from friend request
        if (friendRequestRepository.
                deleteByUserIdAndFriendId(rejectFriendId,
                        userId) == 0) {
            throw new UserDidNotRequestToBeFriendException(rejectFriendName
                    + " did not request to be a friend with you");
        }
    }
}
