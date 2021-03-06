package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.TShirt;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao {

    private JdbcTemplate jdbcTemplate;

    // prepared statements

    private static final String INSERT_TSHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) values (?, ?, ?, ?, ?)";

    private static final String SELECT_TSHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";

    private static final String SELECT_ALL_TSHIRT_SQL =
            "select * from t_shirt";

    private static final String UPDATE_TSHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? where t_shirt_id = ?";

    private static final String DELETE_TSHIRT_SQL =
            "delete from t_shirt where t_shirt_id = ?";

    private static final String SELECT_TSHIRT_BY_COLOR_SQL =
            "select * from t_shirt where color = ?";

    private static final String SELECT_TSHIRT_BY_SIZE_SQL =
            "select * from t_shirt where size = ?";

    // constructor

    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implement methods

    // adds a tshirt to the database
    @Override
    @Transactional
    public TShirt addTShirt(TShirt tShirt) {
        jdbcTemplate.update(
                INSERT_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        tShirt.settShirtId(id);

        return tShirt;
    }

    // retrieves a tshirt with a matching id
    @Override
    public TShirt getTShirt(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TSHIRT_SQL, this::mapRowToTShirt, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    // retrieves a list of all tshirts
    @Override
    public List<TShirt> getAllTShirts() {
        return jdbcTemplate.query(SELECT_ALL_TSHIRT_SQL, this::mapRowToTShirt);
    }

    // updates a tshirt with a matching id
    @Override
    @Transactional
    public void updateTShirt(TShirt tShirt) {

        int largestId = jdbcTemplate.queryForObject("SELECT MAX(t_shirt_id) FROM t_shirt", Integer.class);

        if (tShirt.gettShirtId() > largestId) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }
        jdbcTemplate.update(
                UPDATE_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity(),
                tShirt.gettShirtId()
        );
    }

    // deletes a tshirt with a matching id
    @Override
    @Transactional
    public void deleteTShirt(int id) {
        int largestId = jdbcTemplate.queryForObject("SELECT MAX(t_shirt_id) FROM t_shirt", Integer.class);

        if (id > largestId) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }
        jdbcTemplate.update(DELETE_TSHIRT_SQL, id);
    }

    // retrieves a list of tshirts with a matching color
    @Override
    public List<TShirt> findTShirtsByColor(String color) {
        return jdbcTemplate.query(SELECT_TSHIRT_BY_COLOR_SQL, this::mapRowToTShirt, color);
    }

    // retrieves a list of tshirts with a matching size
    @Override
    public List<TShirt> findTShirtsBySize(String size) {
        return jdbcTemplate.query(SELECT_TSHIRT_BY_SIZE_SQL, this::mapRowToTShirt, size);
    }

    // Row Mapper

    private TShirt mapRowToTShirt(ResultSet rs, int rowNum) throws SQLException {
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(rs.getInt("t_shirt_id"));
        tShirt.setSize(rs.getString("size"));
        tShirt.setColor(rs.getString("color"));
        tShirt.setDescription(rs.getString("description"));
        tShirt.setPrice(rs.getBigDecimal("price"));
        tShirt.setQuantity(rs.getInt("quantity"));

        return tShirt;
    }

}

