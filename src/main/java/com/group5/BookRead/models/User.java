package com.group5.BookRead.models;

public class User {

	private int id;
	private String usernme;
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String usernme, String password) {
		super();
		this.id = id;
		this.usernme = usernme;
		this.password = password;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsernme() {
		return usernme;
	}
	public void setUsernme(String usernme) {
		this.usernme = usernme;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", usernme=" + usernme + ", password=" + password + "]";
	}
}
