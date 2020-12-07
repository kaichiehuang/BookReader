package com.group5.BookRead.models;

public class Settings {
    private int id;
    private int userId;
    private int defaultBookShelfId;

    public Settings() {
    }

    public Settings(final int userId, final int defaultBookShelfId) {
        this.userId = userId;
        this.defaultBookShelfId = defaultBookShelfId;
    }

    /**  get settings id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**  set settings id
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
     * @return void
     */
    public void setUserId(final int userId) {
        this.userId = userId;
    }
    /**  get default bookshelf id
     * @return id
     */
    public int getDefaultBookShelfId() {
        return defaultBookShelfId;
    }

    /**  set default bookshelf id
     * @return id
     */
    public void setDefaultBookShelfId(final int defaultBookShelfId) {
        this.defaultBookShelfId = defaultBookShelfId;
    }
}
