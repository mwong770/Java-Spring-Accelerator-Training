package com.company.mariawongu1capstone.controller;

import com.company.mariawongu1capstone.exception.NotFoundException;
import com.company.mariawongu1capstone.service.InvoiceService;
import com.company.mariawongu1capstone.viewmodel.InvoiceViewModel;
import com.company.mariawongu1capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class InvoiceInventoryController {

    @Autowired
    InvoiceService invoiceService;

    //CREATE
    @RequestMapping(value="/invoices", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel createInvoice(@RequestBody @Valid InvoiceViewModel invoice) {
        return invoiceService.saveInvoice(invoice);
    }


    //RETRIEVE
    @RequestMapping(value="/invoices/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public InvoiceViewModel getInvoice(@PathVariable("id") int itemId) {
        InvoiceViewModel item = invoiceService.findInvoice(itemId);
        if (item == null)
            throw new NotFoundException("Item could not be retrieved for id " + itemId);
        return item;
    }

    //UPDATE
    @RequestMapping(value="/invoices/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInvoice(@PathVariable("id") int itemId, @RequestBody @Valid InvoiceViewModel itemViewModel) {
        if (itemViewModel.getInvoiceId() == 0)
            itemViewModel.setInvoiceId(itemId);
        if (itemId != itemViewModel.getInvoiceId()) {
            throw new IllegalArgumentException("Item ID on path must match the ID in the Item object");
        }
        invoiceService.updateInvoice(itemViewModel);
    }
}
/*
 Invoice addInvoice(Invoice invoice);

    Invoice getInvoice(int id);

    // needed to retrieve all invoices during testing setup
    List<Invoice> getAllInvoices();

    // needed to amend invoice in case of poor user input or customer requests a change in order
    void updateInvoice(Invoice invoice);

    // needed to delete invoices during testing setup
    void deleteInvoice(int id);
 */

