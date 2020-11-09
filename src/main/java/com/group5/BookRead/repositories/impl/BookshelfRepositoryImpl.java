package com.group5.BookRead.repositories.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import com.group5.BookRead.models.Book;
import com.group5.BookRead.models.Bookshelf;
import com.group5.BookRead.repositories.BookshelfRepository;
import com.group5.BookRead.repositories.impl.BookRepositoryImpl.BookRowMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class BookshelfRepositoryImpl implements BookshelfRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	class BookshelfRowMapper implements RowMapper <Bookshelf> {

		@Override
		public Bookshelf mapRow(ResultSet rs, int rowNum) throws SQLException {
			Bookshelf shelf = new Bookshelf();
			shelf.setId(rs.getInt("id"));
			shelf.setName(rs.getString("name"));
			shelf.setUser_id(rs.getInt("user_id"));
			return shelf;
		}
		
	}
	
	@Override
	public int insert(Bookshelf shelf) throws SQLIntegrityConstraintViolationException {
		return jdbcTemplate.update("insert into Bookshelf (user_id, name) " + "values(?, ?)",
			new Object[] {
				shelf.getUser_id(), shelf.getName()
			});
	}

	@Override
	public List<Bookshelf> findAllByUserId(int user_id) {
		try {
			List<Bookshelf> shelfList = jdbcTemplate.query("select * from Bookshelf "+"where user_id = ?", 
				new Object[] {
					user_id
				}, 
				new BookshelfRowMapper());
			return shelfList;
					
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Bookshelf findByNameAndUserId(String name, int user_id) {
		try {
			Bookshelf shelf = jdbcTemplate.queryForObject("select * from Bookshelf "+"where name = ? and user_id = ?",
				new Object[] {
					name, user_id	
				}, 
				new BookshelfRowMapper());
			return shelf;
					
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int findIdByNameAndUserId(String name, int user_id) {
		try {
			int id = jdbcTemplate.queryForObject("select id from Bookshelf "+"where name = ? and user_id = ?",
				new Object[] {
					name, user_id	
				}, 
				int.class);
			return id;
					
		} catch (EmptyResultDataAccessException e) {
			return -1;
		}
	}

	@Override
	public Bookshelf findById(int id) {
		try {
			Bookshelf shelf = jdbcTemplate.queryForObject("select * from Bookshelf "+"where id = ?",
				new Object[] {
					id
				}, 
				new BookshelfRowMapper());
			return shelf;
			
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("delete from Bookshelf where id = ?", new Object[] {
				id	
			});
	}
	
}
