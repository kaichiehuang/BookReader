package com.group5.BookRead.models;

public class Bookshelf {
	
	private int id;
	private int user_id;
	private String name;
	
	public Bookshelf() {
		super();
	}

	public Bookshelf(int id, int user_id, String name) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bookshelf [id=" + id + ", user_id=" + user_id + ", name=" + name + "]";
	}
	
	
	
}
