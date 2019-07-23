package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.Invoice;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {

    private JdbcTemplate jdbcTemplate;

    // prepared statements

    private static final String INSERT_INVOICE_SQL =
            "insert into invoice (name, street, city, state, zipcode, item_type, item_id, unit_price, " +
                    "quantity, subtotal, tax, processing_fee, total) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_INVOICE_SQL =
            "select * from invoice where invoice_id = ?";

    private static final String SELECT_ALL_INVOICE_SQL =
            "select * from invoice";

    private static final String UPDATE_INVOICE_SQL =
            "update invoice set name = ?, street = ?, city = ?, state = ?," +
                    " zipcode = ?, item_type = ?, item_id = ?, unit_price = ?," +
                    " quantity = ?, subtotal = ?, tax = ?, processing_fee = ?," +
                    " total = ? where invoice_id = ?";

    private static final String DELETE_INVOICE_SQL =
            "delete from invoice where invoice_id = ?";

    // constructor

    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //implement methods

    // adds an invoice to the database
    @Override
    @Transactional
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(
                INSERT_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipCode(),
                invoice.getItemType(),
                invoice.getItemId(),
                invoice.getUnitPrice(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessingFee(),
                invoice.getTotal()
        );

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoice.setInvoiceId(id);

        return invoice;
    }

    // retrieves an invoice with a matching id
    @Override
    public Invoice getInvoice(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, id);
        } catch (EmptyResultDataAccessException e) {
            // if there is no match for this album id, return null
            return null;
        }
    }

    // retrieves a list of all invoices
    // needed to retrieve all invoices during testing setup
    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICE_SQL, this::mapRowToInvoice);
    }

    // updates an invoice with a matching id
    // needed to amend invoice in case of poor user input or customer requests a change in order
    @Override
    public void updateInvoice(Invoice invoice) {
        int largestId = jdbcTemplate.queryForObject("SELECT MAX(invoice_id) FROM invoice", Integer.class);

        if (invoice.getInvoiceId() > largestId) {
            throw new IllegalArgumentException("The invoice id provided does not exist.");
        }
        jdbcTemplate.update(
                UPDATE_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipCode(),
                invoice.getItemType(),
                invoice.getItemId(),
                invoice.getUnitPrice(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessingFee(),
                invoice.getTotal(),
                invoice.getInvoiceId()
        );

    }

    // deletes an invoice with a matching id
    // needed to delete invoices during testing setup
    @Override
    public void deleteInvoice(int id) {
        int largestId = jdbcTemplate.queryForObject("SELECT MAX(invoice_id) FROM invoice", Integer.class);

        if (id > largestId) {
            throw new IllegalArgumentException("The id provided does not exist.");
        }
        jdbcTemplate.update(DELETE_INVOICE_SQL, id);
    }

    // Row Mapper

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setName(rs.getString("name"));
        invoice.setStreet(rs.getString("street"));
        invoice.setCity(rs.getString("city"));
        invoice.setState(rs.getString("state"));
        invoice.setZipCode(rs.getString("zipcode"));
        invoice.setItemType(rs.getString("item_type"));
        invoice.setItemId(rs.getInt("item_id"));
        invoice.setUnitPrice(rs.getBigDecimal("unit_price"));
        invoice.setQuantity(rs.getInt("quantity"));
        invoice.setSubtotal(rs.getBigDecimal("subtotal"));
        invoice.setTax(rs.getBigDecimal("tax"));
        invoice.setProcessingFee(rs.getBigDecimal("processing_fee"));
        invoice.setTotal(rs.getBigDecimal("total"));

        return invoice;
    }

}



