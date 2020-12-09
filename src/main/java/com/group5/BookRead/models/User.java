package com.group5.BookRead.models;

public class User {

    private int id;
    private String username;
    private String password;
    private String defaultBookshelf;

    /**  get default bookshelf name
     * @return defaultBookshelf
     */
    public String getDefaultBookshelf() {
        return defaultBookshelf;
    }
    /**  set default bookshelf name
     * @return defaultBookshelf
     */
    public void setDefaultBookshelf(final String defaultBookshelf) {
        this.defaultBookshelf = defaultBookshelf;
    }

    public User() {
        super();
    }

    public User(final int id, final String username, final String password) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    /**  get user id
     * @return id
     */
    public int getId() {
        return id;
    }
    /**  set user id
     * @param id
     */
    public void setId(final int id) {
        this.id = id;
    }
    public final String getUsername() {
        return username;
    }
    public final void setUsername(final String usernme) {
        this.username = usernme;
    }
    /**  get passport
     * @return passport
     */
    public String getPassword() {
        return password;
    }
    /**  set password
     * @param password
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**  serialize user to string
     * @return string
     */
    @Override
    public String toString() {
        return "User [id=" + id + ", usernme="
                + username + ", password="
                + password + "]";
    }
}
