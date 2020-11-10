package com.group5.BookRead.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.MyBookRepository;
import com.group5.BookRead.repositories.impl.BookshelfRepositoryImpl.BookshelfRowMapper;

@Repository
public class MyBookRepositoryImpl implements MyBookRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class MyBookRowMapper implements RowMapper <MyBook> {

		@Override
		public MyBook mapRow(ResultSet rs, int rowNum) throws SQLException {
			MyBook mb = new MyBook();
			mb.setId(rs.getInt("id"));
			mb.setBook_id(rs.getInt("book_id"));
			mb.setBookshelf_id(rs.getInt("bookshelf_id"));
			mb.setUser_id(rs.getInt("user_id"));
			mb.setProgress(rs.getInt("progress"));
			return mb;
		}
		
	}
	
	@Override
	public int insert(MyBook mybook) throws SQLIntegrityConstraintViolationException {
		return jdbcTemplate.update("insert into MyBook (book_id, user_id, bookshelf_id, progress) " + "values(?, ?, ?, ?)",
			new Object[] {
				mybook.getBook_id(), mybook.getUser_id(), mybook.getBookshelf_id(), mybook.getProgress()
			});
	}

	@Override
	public List<MyBook> findAllByUserId(int id) {
		try {
			List<MyBook> myBookList = jdbcTemplate.query("select * from MyBook "+"where user_id = ?", 
				new Object[] {
					id
				}, 
				new MyBookRowMapper());
			return myBookList;
					
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<MyBook> findAllByUserIdAndShelfId(int user_id, int bookshelf_id) {
		try {
			List<MyBook> myBookList = jdbcTemplate.query("select * from MyBook "+"where user_id = ? and bookshelf_id = ?", 
				new Object[] {
					user_id, bookshelf_id
				}, 
				new MyBookRowMapper());
			return myBookList;
					
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<MyBook> findAllByBookId(int id) {
		try {
			List<MyBook> myBookList = jdbcTemplate.query("select * from MyBook "+"where book_id = ?", 
				new Object[] {
					id
				}, 
				new MyBookRowMapper());
			return myBookList;
					
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public int findIdByAllIds(int book_id, int user_id, int bookshelf_id) {
		try {
			int id = jdbcTemplate.queryForObject("select id from MyBook "+"where book_id = ? and user_id = ? and bookshelf_id = ?",
				new Object[] {
					book_id, user_id, bookshelf_id	
				}, 
				int.class);
			return id;
					
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}

	@Override
	public int update(MyBook mb) {
		return jdbcTemplate.update("update MyBook "+"set book_id = ?, user_id = ?, bookshelf_id = ?, progress = ? "+"where id = ?", 
			new Object[] {	
				mb.getBook_id(), mb.getUser_id(), mb.getBookshelf_id(), mb.getProgress(), mb.getId()
			});
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("delete from MyBook where id = ?", new Object[] {
				id	
			});
	}

	@Override
	public MyBook findById(int id) {
		return null;
	}

	@Override
	public MyBook findById(int bookshelf_id, int user_id, int book_id) {
		return null;
	}

	@Override
	public MyBook findByUsernameAndBookShelfnameAndBookId(String username, String bookshelfName, String bookId) {
		return null;
	}

}
