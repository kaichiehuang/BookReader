package com.group5.BookRead.models;

public class FriendRequest {

    private int id;
    private int userId;
    private int requestedFriendId;

    public FriendRequest() {
    }

    public FriendRequest(final int userId, final int requestedFriendId) {
        super();
        this.userId = userId;
        this.requestedFriendId = requestedFriendId;
    }

    /**  get friend request id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**  set friend request id
     * @return void
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**  get user id
     * @return id
     */
    public int getUserId() {
        return userId;
    }
    /**  set user id
     * @return id
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }
    /**  get requested friend id
     * @return id
     */
    public int getRequestedFriendId() {
        return requestedFriendId;
    }
    /**  set requested friend id
     * @return id
     */
    public void setRequestedFriendId(final int requestedFriendId) {
        this.requestedFriendId = requestedFriendId;
    }
}
