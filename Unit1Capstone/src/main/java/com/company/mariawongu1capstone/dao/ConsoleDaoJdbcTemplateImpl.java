package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.Console;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao {

    private JdbcTemplate jdbcTemplate;

    // prepared statements

    private static final String INSERT_CONSOLE_SQL =
            "insert into console (model, manufacturer, memory_amount, processor, price, quantity) values (?, ?, ?, ?, ?, ?)";

    private static final String SELECT_CONSOLE_SQL =
            "select * from console where console_id = ?";

    private static final String SELECT_ALL_CONSOLE_SQL =
            "select * from console";

    private static final String UPDATE_CONSOLE_SQL =
            "update console set model = ?, manufacturer = ?, memory_amount = ?, processor = ?, price = ?, quantity = ? where console_id = ?";

    private static final String DELETE_CONSOLE_SQL =
            "delete from console where console_id = ?";

    private static final String SELECT_CONSOLE_BY_MANUFACTURER_SQL =
            "select * from console where manufacturer =? ";

    // constructor

    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implement methods

    // adds a console to the database
    @Override
    @Transactional
    public Console addConsole(Console console) {

        jdbcTemplate.update(
                INSERT_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        console.setConsoleId(id);

        return console;
    }

    // retrieves a console from the databased based on an id
    @Override
    public Console getConsole(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_CONSOLE_SQL, this::mapRowToConsole, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    // retrieves a list of all consoles
    @Override
    public List<Console> getAllConsoles() {
        return jdbcTemplate.query(SELECT_ALL_CONSOLE_SQL, this::mapRowToConsole);
    }

    // updates a console with a matching id
    @Override
    @Transactional
    public void updateConsole(Console console) {

        int largestId = jdbcTemplate.queryForObject("SELECT MAX(console_id) FROM console", Integer.class);

        if (console.getConsoleId() > largestId) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }

        jdbcTemplate.update(
                UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemoryAmount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity(),
                console.getConsoleId()
        );
    }

    // deletes a console with a matching id
    @Override
    @Transactional
    public void deleteConsole(int id) {
        int largestId = jdbcTemplate.queryForObject("SELECT MAX(console_id) FROM console", Integer.class);

        if (id > largestId) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }

        jdbcTemplate.update(DELETE_CONSOLE_SQL, id);
    }

    // retrieves a list of consoles with a matching manufacturer
    @Override
    public List<Console> findConsolesByManufacturer(String manufacturer) {
        return jdbcTemplate.query(SELECT_CONSOLE_BY_MANUFACTURER_SQL, this::mapRowToConsole, manufacturer);
    }

    // Row Mapper

    private Console mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Console console = new Console();
        console.setConsoleId(rs.getInt("console_id"));
        console.setModel(rs.getString("model"));
        console.setManufacturer(rs.getString("manufacturer"));
        console.setMemoryAmount(rs.getString("memory_amount"));
        console.setProcessor(rs.getString("processor"));
        console.setPrice(rs.getBigDecimal("price"));
        console.setQuantity(rs.getInt("quantity"));

        return console;
    }

}
