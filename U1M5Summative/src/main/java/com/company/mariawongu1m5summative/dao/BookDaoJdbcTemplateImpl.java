package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// Implements the BookDAO interface
// Inserts, modifies and queries data from book_store db
@Repository
public class BookDaoJdbcTemplateImpl implements BookDao {

    // Prepared statement strings

    private static final String INSERT_BOOK_SQL =
            "insert into book (isbn, publish_date, author_id, title, publisher_id, price) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_BOOK_SQL =
            "select * from book where book_id = ?";

    private static final String SELECT_ALL_BOOKS_SQL =
            "select * from book";

    private static final String DELETE_BOOK_SQL =
            "delete from book where book_id = ?";

    private static final String UPDATE_BOOK_SQL =
            "update book set isbn = ?, publish_date = ?, author_id = ?, title = ?, publisher_id = ?, price = ? where book_id = ?";

    private static final String SELECT_BOOKS_BY_AUTHOR_SQL =
            "select * from book where author_id = ?";

    private JdbcTemplate jdbcTemplate;

    // constructor

    // tells Spring to create an instance of JdbcTemplate and pass it into our
    // constructor when our class is instantiated (constructor injection)
    @Autowired
    public BookDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implementations of method declarations in BookDao

    /**
     * gets one book by the id passed in
     *
     * @param id
     * @return Book object
     */
    @Override
    public Book getBook(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_BOOK_SQL, this::mapRowToBook, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    /**
     * gets all books in the db
     *
     * @return list of Book objects
     */
    @Override
    public List<Book> getAllBooks() {
        return jdbcTemplate.query(SELECT_ALL_BOOKS_SQL, this::mapRowToBook);
    }

    /**
     * adds a new book to the db
     *
     * @param book
     * @return Book with an id
     */
    @Override
    public Book addBook(Book book) {
        jdbcTemplate.update(INSERT_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice());

        // returns id of the last row we just inserted
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        book.setBookId(id);

        return book;
    }

    /**
     * updates a book with new data passed in
     *
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update(UPDATE_BOOK_SQL,
                book.getIsbn(),
                book.getPublishDate(),
                book.getAuthorId(),
                book.getTitle(),
                book.getPublisherId(),
                book.getPrice(),
                book.getBookId());
    }

    /**
     * deletes the book with the id passed in
     *
     * @param id
     */
    @Override
    public void deleteBook(int id) {
        jdbcTemplate.update(DELETE_BOOK_SQL, id);
    }

    @Override
    public List<Book> getBooksByAuthor(int authorId) {
        return jdbcTemplate.query(SELECT_BOOKS_BY_AUTHOR_SQL, this::mapRowToBook, authorId);
    }

    /**
     * maps column names obtained from ResultSet to the Book object via setters and returns it
     *
     * @param rs
     * @param rowNum
     * @return Book object
     * @throws SQLException
     */
    private Book mapRowToBook(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setBookId(rs.getInt("book_id"));
        book.setIsbn(rs.getString("isbn"));
        book.setPublishDate(rs.getDate("publish_date").toLocalDate());
        book.setAuthorId(rs.getInt("author_id"));
        book.setTitle(rs.getString("title"));
        book.setPublisherId(rs.getInt("publisher_id"));
        book.setPrice(rs.getBigDecimal("price"));
        return book;
    }

}
