package com.group5.BookRead.models;

public class Bookshelf  {

    private int id;
    private int userId;
    private String name;

    public Bookshelf() {
        this.id = 0;
        this.userId = 0;
        this.name = "";
    }

    public Bookshelf(final int userId, final String name) {
        this.userId = userId;
        this.name = name;
    }

    public Bookshelf(final int id, final int userId,
            final String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    /**  get bookshelf id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**  set bookshelf id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }
    /**  get bookshelf's user id
     * @return userId
     */
    public int getUserId() {
        return userId;
    }
    /**  set bookshelf's userId
     * @param userId
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }
    /** get bookshelf name
     * @return name
     */
    public String getName() {
        return name;
    }
    /**  set bookshelf name
     * @param name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**  serialize bookshelf to string
     * @return string
     */
    @Override
    public String toString() {
        return "Bookshelf [id=" + id + ", user_id=" + userId + ", "
                + "name=" + name + "]";
    }
}
