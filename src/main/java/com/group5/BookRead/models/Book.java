package com.group5.BookRead.models;

public class Book {

	private int id;
	private String name;
	private String author;
	private int page;
	private String summary = "No available summary";

	public Book() {
		super();
	}
	
	public Book(int id, String name, String author, int page, String summary) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.page = page;
		if (summary != null && summary != "") {
			this.summary = summary;
		}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", page=" + page + ", summary=" + summary
			+ "]";
	}

}
