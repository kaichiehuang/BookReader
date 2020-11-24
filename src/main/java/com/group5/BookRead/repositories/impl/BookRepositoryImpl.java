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

import com.group5.BookRead.models.Book;
import com.group5.BookRead.repositories.BookRepository;


@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    class BookRowMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(final ResultSet rs, final int rowNum)
                throws SQLException {
            Book book = new Book();
            book.setBookIdentifier(rs.getString("book_identifier"));
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setPage(rs.getInt("page"));
            book.setDescription(rs.getString("summary"));
            return book;
        }

    }

    /**  insert book
     * @param book
     * @return status code
     */
    @Override
    public int insert(final Book book) {
        return jdbcTemplate.update("insert into Book (title, author, "
                        + "page, summary,book_identifier, link) "
                        + "values(?, ?, ?, ?, ?,?)",
                new Object[] {
                        book.getTitle(), book.getAuthor(),
                        book.getPage(), book.getDescription(),
                        book.getBookIdentifier(),
                        book.getLink()}
                        );
    }

//    /**
//     *  store object
//     * @param book
//     * @return
//     * @throws SQLIntegrityConstraintViolationException
//     */
//    @Override
//    public Book save(final Book book) throws
//            SQLIntegrityConstraintViolationException {
//        return jdbcTemplate.queryForObject("insert into Book (title, author, "
//                        + "page, summary,book_identifier, link) "
//                        + "values(?, ?, ?, ?, ?,?)",
//                new Object[] {
//                        book.getTitle(), book.getAuthor(),
//                        book.getPage(), book.getDescription(),
//                        book.getBookIdentifier(),
//                        book.getLink()
//                }, new BookRowMapper());
//    }

    /**  find all books
     * @return bookList
     */
    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("select * from Book", new BookRowMapper());
    }

    /**  find book by book id
     * @param id
     * @return book
     */
    @Override
    public Book findById(final int id) {
        try {
            Book book = jdbcTemplate.queryForObject("select * from Book "
                    + "where id = ?",
                new Object[] {id},
                new BookRowMapper());
            return book;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**  find book by book name and author
     * @param name
     * @param author
     * @return book
     */
    @Override
    public Book findByNameAndAuthor(final String name,
            final String author) {
        try {
            Book book = jdbcTemplate.queryForObject("select * from Book "
                    + "where title = ? and author = ?",
                new Object[] {name, author},
                new BookRowMapper());
            return book;

        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /** find book id by book name and author
     * @param name
     * @param author
     * @return id
     */
    @Override
    public int findIdByNameAndAuthor(final String name,
            final String author) {
        try {
            int id = jdbcTemplate.queryForObject("select id from Book "
                    + "where title = ? and author = ?",
                    new Object[] {name, author}, int.class);
            return id;

        } catch (EmptyResultDataAccessException e) {
            return -1;
        }
    }

    /**  update book fields
     * @param book
     * @return status code
     */
    @Override
    public int update(final Book book) {
        return jdbcTemplate.update("update Book " + "set title = ?, "
                + "author = ?, page = ?, summary = ? " + "where id = ?",
                new Object[] {book.getTitle(), book.getAuthor(),
                        book.getPage(), book.getDescription(), book.getId()});

    }

    /**  delete book by book id
     * @param id
     * @return status code
     */
    @Override
    public int deleteById(final int id) {
        return jdbcTemplate.update("delete from Book where id = ?",
                new Object[]{id});

    }

    /**
     * find by identifier
     * @param identifier
     * @return
     */
    @Override
    public Book findByIdentifier(final String book_identifier) {

        try {
            Book book = jdbcTemplate.queryForObject("select * from Book "
                            + "where book_identifier = ?",
                    new Object[] {book_identifier},
                    new BookRowMapper());
            return book;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


}
