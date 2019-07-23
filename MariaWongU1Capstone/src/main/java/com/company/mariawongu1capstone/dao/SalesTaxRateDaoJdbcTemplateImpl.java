package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.SalesTaxRate;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalesTaxRateDaoJdbcTemplateImpl implements SalesTaxRateDao {

    private JdbcTemplate jdbcTemplate;

    // prepared statements

    private static final String SELECT_STATE_TAX_RATE_SQL =
            "select * from sales_tax_rate where state = ?";

    // constructor

    public SalesTaxRateDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // implement methods

    // retrieves a sales tax rate needed to calculate invoice total
    @Override
    public BigDecimal getSalesTaxRate(String state) {
        try {
            SalesTaxRate row = jdbcTemplate.queryForObject(SELECT_STATE_TAX_RATE_SQL, this::mapRowToSalesTaxRate, state);
            return row.getRate();
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    // Row Mapper

    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException {
        SalesTaxRate salesTaxRate = new SalesTaxRate();
        salesTaxRate.setState(rs.getString("state"));
        salesTaxRate.setRate(rs.getBigDecimal("rate"));

        return salesTaxRate;
    }

}
