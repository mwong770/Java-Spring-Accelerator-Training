package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

// USE MOCKS ********************

@Component
public class InvoiceService {

    ConsoleDao consoleDao;
    GameDao gameDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    TShirtDao tShirtDao;

    @Autowired
    public InvoiceService(ConsoleDao consoleDao, GameDao gameDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao, SalesTaxRateDao salesTaxRateDao, TShirtDao tShirtDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.tShirtDao = tShirtDao;
    }

    //
    // Invoice API
    //

    public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {

        invoiceViewModel = calculateTotal(invoiceViewModel);
        amendQuantityInDB(invoiceViewModel.getItemType(), invoiceViewModel.getItemId(), invoiceViewModel.getQuantity(), "subtract");

        Invoice invoice = new Invoice();
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipCode(invoiceViewModel.getZipCode());
        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());

        invoice.setUnitPrice(invoiceViewModel.getUnitPrice());

        invoice.setQuantity(invoiceViewModel.getQuantity());

        invoice.setSubtotal(invoiceViewModel.getSubtotal());
        invoice.setTax(invoiceViewModel.getTax());
        invoice.setProcessingFee(invoiceViewModel.getProcessingFee());
        invoice.setTotal(invoiceViewModel.getTotal());

        invoice = invoiceDao.addInvoice(invoice);
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());

        invoiceViewModel = getItemDetails(invoiceViewModel);
        return invoiceViewModel;
    }

    public InvoiceViewModel calculateTotal(InvoiceViewModel invoiceViewModel) {

        invoiceViewModel = getItemDetails(invoiceViewModel);

        MathContext mc = new MathContext(2);

        // **** don't make data type a decimal to prevent data entry of decimal, want integer ****
        // **** though look into setScale(0) ****
        BigDecimal quantityAsDecimal = new BigDecimal(invoiceViewModel.getQuantity()).setScale(2);

        BigDecimal subtotal = (invoiceViewModel.getUnitPrice().multiply(quantityAsDecimal)).setScale(2, RoundingMode.HALF_UP);
        BigDecimal taxRate = salesTaxRateDao.getSalesTaxRate(invoiceViewModel.getState());
        BigDecimal processingFee = processingFeeDao.getProcessingFee(invoiceViewModel.getItemType());
        BigDecimal additionalFee = new BigDecimal(00).setScale(2);
        if (invoiceViewModel.getQuantity() > 10) {
            additionalFee = new BigDecimal(15.49).setScale(2, RoundingMode.HALF_UP);
            processingFee = processingFee.add(additionalFee).setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal total = (subtotal.multiply(taxRate, mc)).add(processingFee).add(subtotal).setScale(2, RoundingMode.HALF_UP);

        invoiceViewModel.setSubtotal(subtotal);
        invoiceViewModel.setTax((subtotal.multiply(taxRate)).setScale(2, RoundingMode.HALF_UP));
        invoiceViewModel.setProcessingFee(processingFee);
        invoiceViewModel.setTotal(total);

        return invoiceViewModel;

    }

    public InvoiceViewModel getItemDetails(InvoiceViewModel invoiceViewModel) {

        Object item;
        BigDecimal price = new BigDecimal(0).setScale(2);
        switch (invoiceViewModel.getItemType()) {
            case "Consoles":
                Console cItem = consoleDao.getConsole(invoiceViewModel.getItemId());
                price = cItem.getPrice();
                item = cItem;
                break;
            case "Games":
                Game gItem = gameDao.getGame(invoiceViewModel.getItemId());
                price = gItem.getPrice();
                item = gItem;
                break;
            case "T-Shirts":
                TShirt tItem = tShirtDao.getTShirt(invoiceViewModel.getItemId());
                price = tItem.getPrice();
                item = tItem;
                break;
            default:
                throw new IllegalArgumentException("You must select a valid item type.");
        }
        invoiceViewModel.setItem(item);
        invoiceViewModel.setUnitPrice(price);

        return invoiceViewModel;
    }

    // ADD TEST FOR THIS **************************
    // TEST IF THROWS ERROR **************************
    public void amendQuantityInDB(String itemType, int itemId, int quantity, String action) {
        System.out.println(itemType + ", " + itemId + ", " + quantity + ", " + action);
        Object item;
        switch (itemType) {
            case "Consoles":
                Console cItem = consoleDao.getConsole(itemId);
                if (action == "subtract") {
                    if (quantity <= cItem.getQuantity()) {
                        cItem.setQuantity(cItem.getQuantity() - quantity);
                        consoleDao.updateConsole(cItem);
                    } else {
                        // throw error - we don't have that many items ??
                    }
                } else if (action == "add") {
                    cItem.setQuantity(cItem.getQuantity() + quantity);
                    consoleDao.updateConsole(cItem);
                } else {
                    // throw error - server error - invalid action programmatically ???
                }
                break;
            case "Games":
                Game gItem = gameDao.getGame(itemId);
                if (action == "subtract") {
                    if (quantity <= gItem.getQuantity()) {
                        gItem.setQuantity(gItem.getQuantity() - quantity);
                        gameDao.updateGame(gItem);
                    } else {
                        // throw error - we don't have that many items ??
                    }
                } else if (action == "add") {
                    gItem.setQuantity(gItem.getQuantity() + quantity);
                    gameDao.updateGame(gItem);
                } else {
                    // throw error - server error - invalid action programmatically ???
                }
                break;
            case "T-Shirts":
                TShirt tItem = tShirtDao.getTShirt(itemId);
                if (action == "subtract") {
                    if (quantity <= tItem.getQuantity()) {
                        tItem.setQuantity(tItem.getQuantity() - quantity);
                        tShirtDao.updateTShirt(tItem);
                    } else {
                        // throw error - we don't have that many items ??
                    }
                } else if (action == "add") {
                    tItem.setQuantity(tItem.getQuantity() + quantity);
                    tShirtDao.updateTShirt(tItem);
                } else {
                    // throw error - server error - invalid action programmatically ???
                }
                break;
            default:
                throw new IllegalArgumentException("You must select a valid item type.");
        }
    }

    public InvoiceViewModel findInvoice(int id) {
        Invoice invoice = invoiceDao.getInvoice(id);
        if(invoice == null )
            return null;
        else
            return buildInvoiceViewModel(invoice);
    }

    public List<InvoiceViewModel> findAllInvoices() {

        List<Invoice> invoices = invoiceDao.getAllInvoices();

        List<InvoiceViewModel> invoiceViewModels = new ArrayList<>();

        for (Invoice i: invoices) {
            invoiceViewModels.add(buildInvoiceViewModel(i));
        }
        return invoiceViewModels;

    }

    public void updateInvoice(InvoiceViewModel invoiceViewModel) {

        invoiceViewModel = calculateTotal(invoiceViewModel);

        String action;
        int quantity;
        int originalQuantity = invoiceDao.getInvoice(invoiceViewModel.getInvoiceId()).getQuantity();
        if (originalQuantity < invoiceViewModel.getQuantity()) {
            action = "subtract";
            quantity = invoiceViewModel.getQuantity() - originalQuantity;
        } else {
            action = "add";
            quantity = originalQuantity - invoiceViewModel.getQuantity();
        }

        amendQuantityInDB(invoiceViewModel.getItemType(), invoiceViewModel.getItemId(), quantity, action);

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(invoiceViewModel.getInvoiceId());
        invoice.setName(invoiceViewModel.getName());
        invoice.setStreet(invoiceViewModel.getStreet());
        invoice.setCity(invoiceViewModel.getCity());
        invoice.setState(invoiceViewModel.getState());
        invoice.setZipCode(invoiceViewModel.getZipCode());

        invoice.setItemType(invoiceViewModel.getItemType());
        invoice.setItemId(invoiceViewModel.getItemId());
        invoice.setUnitPrice(invoiceViewModel.getUnitPrice());
        invoice.setQuantity(invoiceViewModel.getQuantity());

        invoice.setSubtotal(invoiceViewModel.getSubtotal());
        invoice.setTax(invoiceViewModel.getTax());
        invoice.setProcessingFee(invoiceViewModel.getProcessingFee());
        invoice.setTotal(invoiceViewModel.getTotal());

        invoiceDao.updateInvoice(invoice);

    }

    public void removeInvoice(int id) {
        invoiceDao.deleteInvoice(id);
    }

    // Build View Model

    private InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel invoiceViewModel = new InvoiceViewModel();
        invoiceViewModel.setInvoiceId(invoice.getInvoiceId());
        invoiceViewModel.setName(invoice.getName());
        invoiceViewModel.setStreet(invoice.getStreet());
        invoiceViewModel.setCity(invoice.getCity());
        invoiceViewModel.setState(invoice.getState());
        invoiceViewModel.setZipCode(invoice.getZipCode());
        invoiceViewModel.setItemType(invoice.getItemType());
        invoiceViewModel.setItemId(invoice.getItemId());
        invoiceViewModel.setUnitPrice(invoice.getUnitPrice());
        invoiceViewModel.setQuantity(invoice.getQuantity());
        invoiceViewModel.setSubtotal(invoice.getSubtotal());
        
        invoiceViewModel.setTax(invoice.getTax());
        invoiceViewModel.setProcessingFee(invoice.getProcessingFee());
        invoiceViewModel.setTotal(invoice.getTotal());

        invoiceViewModel = getItemDetails(invoiceViewModel);

        return invoiceViewModel;
    }
}
