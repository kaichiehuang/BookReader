package com.group5.BookRead.models;

public class MyBook {

	private int id;
	private int book_id;
	private int user_id;
	private int bookshelf_id;
	private double progress = 0;
	
	public MyBook() {
		super();
	}
	
	public MyBook(int id, int book_id, int user_id, int bookshelf_id, double progress) {
		super();
		this.id = id;
		this.book_id = book_id;
		this.user_id = user_id;
		this.bookshelf_id = bookshelf_id;
		this.progress = progress;
	}

	public MyBook(int book_id, int user_id, int bookshelf_id, double progress) {
		this.book_id = book_id;
		this.user_id = user_id;
		this.bookshelf_id = bookshelf_id;
		this.progress = progress;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getBookshelf_id() {
		return bookshelf_id;
	}
	public void setBookshelf_id(int bookshelf_id) {
		this.bookshelf_id = bookshelf_id;
	}
	public double getProgress() {
		return progress;
	}
	public void setProgress(double progress) {
		this.progress = progress;
	}
	
	@Override
	public String toString() {
		return "MyBook [id=" + id + ", book_id=" + book_id + ", user_id=" + user_id + ", bookshelf_id=" + bookshelf_id
				+ ", progress=" + progress + "]";
	}
	
}
