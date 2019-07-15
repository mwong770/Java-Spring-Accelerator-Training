package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// Data Access Object concrete class - implements the AuthorDAO interface
// Inserts, modifies and queries data from book_store db
@Repository
public class AuthorDaoJdbcTemplateImpl implements AuthorDao {

    // Prepared statement strings

    private static final String INSERT_AUTHOR_SQL =
            "insert into author (first_name, last_name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_AUTHOR_SQL =
            "select * from author where author_id = ?";

    private static final String SELECT_ALL_AUTHORS_SQL =
            "select * from author";

    private static final String DELETE_AUTHOR_SQL =
            "delete from author where author_id = ?";

    private static final String UPDATE_AUTHOR_SQL =
            "update author set first_name = ?, last_name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? where author_id = ?";

    private JdbcTemplate jdbcTemplate;

    // constructor

    // constructor injection - tells Spring to create an instance of JdbcTemplate and
    // pass it into our constructor when our class is instantiated
    @Autowired
    public AuthorDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implementations of method declarations in AuthorDao

    /**
     * gets one author by the id passed in
     *
     * @param id
     * @return Author object
     */
    @Override
    public Author getAuthor(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_AUTHOR_SQL, this::mapRowToAuthor, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    /**
     * gets all authors in the db
     *
     * @return list of Author objects
     */
    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SELECT_ALL_AUTHORS_SQL, this::mapRowToAuthor);
    }

    /**
     * adds a new author to the db
     *
     * @param author
     * @return Author with an id
     */
    @Override
    public Author addAuthor(Author author) {
        jdbcTemplate.update(INSERT_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail());

        // returns AUTO_INCREMENT id of the last row we just inserted
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        author.setAuthorId(id);

        return author;
    }

    /**
     * updates an author with new data passed in
     *
     * @param author
     */
    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR_SQL,
                author.getFirstName(),
                author.getLastName(),
                author.getStreet(),
                author.getCity(),
                author.getState(),
                author.getPostalCode(),
                author.getPhone(),
                author.getEmail(),
                author.getAuthorId());
    }

    /**
     * deletes the author with the id passed in
     *
     * @param id
     */
    @Override
    public void deleteAuthor(int id) {
        jdbcTemplate.update(DELETE_AUTHOR_SQL, id);
    }

    /**
     * maps column names obtained from ResultSet to the Author object via setters and returns it
     *
     * @param rs
     * @param rowNum
     * @return Author object
     * @throws SQLException
     */
    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException {
        Author author = new Author();
        author.setAuthorId(rs.getInt("author_id"));
        author.setFirstName(rs.getString("first_name"));
        author.setLastName(rs.getString("last_name"));
        author.setStreet(rs.getString("street"));
        author.setCity(rs.getString("city"));
        author.setState(rs.getString("state"));
        author.setPostalCode(rs.getString("postal_code"));
        author.setPhone(rs.getString("phone"));
        author.setEmail(rs.getString("email"));
        return author;
    }

}
