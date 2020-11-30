package com.group5.BookRead.models;

public class Friendship {

    private int id;
    private int userId;
    private int friendId;

    public Friendship() {
    }

    public Friendship(final int userId, final int friendId) {
        super();
        this.userId = userId;
        this.friendId = friendId;
    }

    /**  get friendship id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**  get user id
     * @return id
     */
    public int getUserId() {
        return userId;
    }
    /**  get friend id
     * @return id
     */
    public int getFriendId() {
        return friendId;
    }
    /**  set friendship id
     * @return id
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**  set user id
     * @return void
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }
    /**  set friend id
     * @return void
     */
    public void setFriendId(final int friendId) {
        this.friendId = friendId;
    }
}
