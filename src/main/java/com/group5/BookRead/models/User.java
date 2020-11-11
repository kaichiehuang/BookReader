package com.group5.BookRead.models;

public class User {

    private int id;
    private String usernme;
    private String password;

    public User() {
        super();
    }

    public User(final int id, final String usernme, final String password) {
        super();
        this.id = id;
        this.usernme = usernme;
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
    /**  get username
     * @return username
     */
    public String getUsernme() {
        return usernme;
    }
    /**  set username
     * @param username
     */
    public void setUsernme(final String usernme) {
        this.usernme = usernme;
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
                + usernme + ", password="
                + password + "]";
    }
}
