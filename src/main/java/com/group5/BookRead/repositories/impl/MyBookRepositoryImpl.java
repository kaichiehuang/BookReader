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

import com.group5.BookRead.models.MyBook;
import com.group5.BookRead.repositories.MyBookRepository;

@Repository
public class MyBookRepositoryImpl implements MyBookRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;

    class MyBookRowMapper implements RowMapper<MyBook> {

        @Override
        public MyBook mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            MyBook mb = new MyBook();
            mb.setId(rs.getInt("id"));
            mb.setBookId(rs.getInt("book_id"));
            mb.setBookshelfId(rs.getInt("bookshelf_id"));
            mb.setUserId(rs.getInt("user_id"));
            mb.setProgress(rs.getInt("progress"));
            return mb;
        }

    }

    /**  insert mybook
     * @param mybook
     * @return status code
     */
    @Override
    public int insert(final MyBook mybook) throws
        SQLIntegrityConstraintViolationException {
        return jdbcTemplate.update("insert into MyBook (book_id, "
                + "user_id, bookshelf_id, progress) " + "values(?, ?, ?, ?)",
            new Object[] {mybook.getBookId(), mybook.getUserId(),
                    mybook.getBookshelfId(), mybook.getProgress()
                });
    }

    /**  find all mybook of a user by userId
     * @param id
     * @return mybookList
     */
    @Override
    public List<MyBook> findAllByUserId(final int id) {
        try {
            List<MyBook> myBookList = jdbcTemplate.query(
                    "select * from MyBook " + "where user_id = ?",
                new Object[] {id},
                new MyBookRowMapper());
            return myBookList;
        } catch (Exception e) {
            return null;
        }
    }

    /**  find all mybook by userId and bookshelfId
     * @param userId
     * @param bookshelfId
     * @return mybookList
     */
    @Override
    public List<MyBook> findAllByUserIdAndShelfId(
            final int userId, final int bookshelfId) {
        try {
            List<MyBook> myBookList = jdbcTemplate.query(
                    "select * from MyBook " + "where user_id = ?"
                            + " and bookshelf_id = ?",
                new Object[] {userId, bookshelfId},
                new MyBookRowMapper());
            return myBookList;
        } catch (Exception e) {
            return null;
        }
    }

    /**  find all mybooks by userId and bookId
     * @param userId
     * @param bookId
     * @return mybookList
     */
    @Override
    public List<MyBook> findAllMybooks(
            final int userId, final int bookId) {
        try {
            List<MyBook> myBookList = jdbcTemplate.query(
                    "select * from MyBook " + "where user_id = ?"
                            + " and book_id = ?",
                new Object[] {userId, bookId},
                new MyBookRowMapper());
            return myBookList;
        } catch (Exception e) {
            return null;
        }
    }

    /**  find all mybook by mybook id
     * @param id
     * @return mybookList
     */
    @Override
    public List<MyBook> findAllByBookId(final int id) {
        try {
            List<MyBook> myBookList = jdbcTemplate.query(
                    "select * from MyBook " + "where book_id = ?",
                new Object[] {id},
                new MyBookRowMapper());
            return myBookList;
        } catch (Exception e) {
            return null;
        }
    }

    /**  find mybook id by other ids (bookId, userId, bookshelfId)
     * @param bookId
     * @param userId
     * @param bookshelfId
     * @return id
     */
    @Override
    public MyBook findByAllIds(final int bookId,
            final int userId, final int bookshelfId) {
        try {
            MyBook book = jdbcTemplate.queryForObject("select * from MyBook "
                    + "where book_id = ? and user_id = ? and bookshelf_id = ?",
                new Object[] {bookId, userId,
                    bookshelfId}, new MyBookRowMapper());
            return book;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**  update mybook
     * @param mybook
     * @return status code
     */
    @Override
    public int update(final MyBook mb) {
        try{
            return jdbcTemplate.update("update MyBook " + "set book_id = ?,"
                + "user_id = ?, bookshelf_id = ?, progress = ? "
                + "where id = ?",
            new Object[] {
                    mb.getBookId(), mb.getUserId(),
                    mb.getBookshelfId(), mb.getProgress(), mb.getId()
            });
        } catch (Exception e){
            System.out.println(e);
            System.exit(1);
            return 0;
        }
        
    }

    /**  delete mybook by id
     * @param id
     * @return status code
     */
    @Override
    public int deleteById(final int id) {
        return jdbcTemplate.update("delete from MyBook where id = ?",
                new Object[] {id});
    }

    /**  find mybook by id
     * @param id
     * @return mybook
     */
    @Override
    public MyBook findById(final int id) {
        System.err.println("MyBookRepositoryImpl:159 - not implement");
        System.exit(1);
        return null;
    }

    /**  find mybook by all other ids
     * @param bookshelfId
     * @param userId
     * @param bookId
     * @return mybook
     */
    @Override
    public MyBook findById(final int bookshelfId,
            final int userId, final int bookId) {
        System.err.println("MyBookRepositoryImpl:172 - not implement");
        System.exit(1);
        return null;
    }

    /**  find mybook by username, shelfName, and bookId
     * @param username
     * @param bookshelfName
     * @param bookId
     * @return mybook
     */
    @Override
    public MyBook findByUsernameAndBookShelfnameAndBookId(
            final String username, final String bookshelfName,
            final String bookId) {
        System.err.println("MyBookRepositoryImpl:185 - not implement");
        System.exit(1);
        return null;
    }

    /**
     * find book by username, bookshelf, and bookId
     * @param username
     * @param bookshelfName
     * @param bookId
     * @return MyBook object
     */
    @Override
    public MyBook findByUsernameAndBookShelfnameAndBookId(
            final String username,
            final String bookshelfName,
            final int bookId) {
        System.err.println("MyBookRepositoryImpl:200 - not implement");
        System.exit(1);
        return null;
    }

    /**
     * delete book by userId, bookshelf, and bookId
     * @param userId
     * @param shelfId
     * @param bookId
     * @return
     */
    @Override
    public int deleteByUserIdAndBookshelfIdAndBookId(
            final int userId,
            final int shelfId,
            final int bookId) {
        return jdbcTemplate.update("delete from MyBook where user_id = ? "
                        + "and book_id = ? and bookshelf_id = ?",
                new Object[] {userId, bookId, shelfId});
    }

    /**
     * find by username and booshelf and bookid
     * @param userId
     * @param shelfName
     * @param bookId
     * @return
     */
    @Override
    public MyBook findByUserAndBookShelfAndBookId(final String userid, 
        final String shelfName, final String bookId) {
        // TODO Auto-generated method stub
        return null;
    }

}
