package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// Implements the PublisherDAO interface
// Inserts, modifies and queries data from book_store db
@Repository
public class PublisherDaoJdbcTemplateImpl implements PublisherDao{

    // Prepared statement strings

    private static final String INSERT_PUBLISHER_SQL =
            "insert into publisher (name, street, city, state, postal_code, phone, email) values (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_PUBLISHER_SQL =
            "select * from publisher where publisher_id = ?";

    private static final String SELECT_ALL_PUBLISHERS_SQL =
            "select * from publisher";

    private static final String DELETE_PUBLISHER_SQL =
            "delete from publisher where publisher_id = ?";

    private static final String UPDATE_PUBLISHER_SQL =
            "update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, email = ? where publisher_id = ?";

    private JdbcTemplate jdbcTemplate;

    // constructor

    // tells Spring to create an instance of JdbcTemplate and pass it into our
    // constructor when our class is instantiated (constructor injection)
    @Autowired
    public PublisherDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    // implementations of method declarations in PublisherDao

    /**
     *
     * gets one publisher by the id passed in
     * @param id
     * @return Publisher object
     */
    @Override
    public Publisher getPublisher(int id) {
        try {

            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowToPublisher, id);

        } catch (EmptyResultDataAccessException e) {
            // if nothing is returned just catch the exception and return null
            return null;
        }
    }

    /**
     *
     * gets all publishers in the db
     * @return list of Publisher objects
     */
    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(SELECT_ALL_PUBLISHERS_SQL, this::mapRowToPublisher);
    }

    /**
     *
     * adds a new publisher to the db
     * @param publisher
     * @return Publisher with an id
     */
    @Override
    public Publisher addPublisher(Publisher publisher) {
        jdbcTemplate.update(INSERT_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail());

        // returns id of the last row we just inserted
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);

        publisher.setPublisherId(id);

        return publisher;
    }

    /**
     *
     * updates a publisher with new data passed in
     * @param publisher
     */
    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(UPDATE_PUBLISHER_SQL,
                publisher.getName(),
                publisher.getStreet(),
                publisher.getCity(),
                publisher.getState(),
                publisher.getPostalCode(),
                publisher.getPhone(),
                publisher.getEmail(),
                publisher.getPublisherId());

    }

    /**
     *
     * deletes the publisher with the id passed in
     * @param id
     */
    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER_SQL, id);
    }

    /**
     *
     * maps column names obtained from ResultSet to the Publisher object via setters and returns it
     * @param rs
     * @param rowNum
     * @return Publisher object
     * @throws SQLException
     */
    private Publisher mapRowToPublisher(ResultSet rs, int rowNum) throws SQLException {
        Publisher publisher = new Publisher();
        publisher.setPublisherId(rs.getInt("publisher_id"));
        publisher.setName(rs.getString("name"));
        publisher.setStreet(rs.getString("street"));
        publisher.setCity(rs.getString("city"));
        publisher.setState(rs.getString("state"));
        publisher.setPostalCode(rs.getString("postal_code"));
        publisher.setPhone(rs.getString("phone"));
        publisher.setEmail(rs.getString("email"));
        return publisher;
    }

}
