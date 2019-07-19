package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.Invoice;

import java.util.List;

// *** FIND OUT IF NEED FULL CRUD

public interface InvoiceDao {

    // standard CRUD

    Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    // needed to retrieve all invoices during testing setup
    List<Invoice> getAllInvoices();

    // needed to amend invoice in case of poor user input or customer requests a change in order
    void updateInvoice(Invoice invoice);

    // needed to delete invoices during testing setup
    void deleteInvoice(int id);

}
