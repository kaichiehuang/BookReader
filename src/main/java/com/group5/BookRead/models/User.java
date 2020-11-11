package com.group5.BookRead.models;

public class User {

    private int id;
    private String username;
    private String password;

    public User() {
        super();
    }

    public User(final int id, final String usernme, final String password) {
        super();
        this.id = id;
        this.username = usernme;
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
    public final void setUsername(String usernme) {
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
