package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.exception.InsufficientQuantityException;
import com.company.mariawongu1capstone.exception.ValueNotSupportedException;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.ConsoleInvoiceViewModel;
import com.company.mariawongu1capstone.viewmodel.GameInvoiceViewModel;
import com.company.mariawongu1capstone.viewmodel.InvoiceViewModel;
import com.company.mariawongu1capstone.viewmodel.TShirtInvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

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

        return invoiceViewModel;
    }

    public InvoiceViewModel calculateTotal(InvoiceViewModel invoiceViewModel) {

        invoiceViewModel = getItemDetails(invoiceViewModel);

        MathContext mc = new MathContext(2);

        BigDecimal quantityAsDecimal = new BigDecimal(invoiceViewModel.getQuantity()).setScale(2);

        BigDecimal subtotal = (invoiceViewModel.getUnitPrice().multiply(quantityAsDecimal)).setScale(2, RoundingMode.HALF_UP);

        BigDecimal taxRate = salesTaxRateDao.getSalesTaxRate(invoiceViewModel.getState());

        if (taxRate == null) {
            throw new IllegalArgumentException("The state code provided does not exist.");
        }

        BigDecimal processingFee = processingFeeDao.getProcessingFee(invoiceViewModel.getItemType());

        BigDecimal additionalFee = new BigDecimal(00).setScale(2);
        if (invoiceViewModel.getQuantity() > 10) {
            additionalFee = new BigDecimal(15.49).setScale(2, RoundingMode.HALF_UP);
            processingFee = processingFee.add(additionalFee).setScale(2, RoundingMode.HALF_UP);
        }
        BigDecimal total = (subtotal.multiply(taxRate, mc)).add(processingFee).add(subtotal).setScale(2, RoundingMode.HALF_UP);

        // max supported by database requirement of decimal(5,2)
        BigDecimal maxSalesSupported = new BigDecimal(999.99).setScale(2, RoundingMode.FLOOR);

        // if total greater than 999.99
        if (total.compareTo(maxSalesSupported) >= 1) {
            throw new ValueNotSupportedException("A sale transaction that large cannot be supported. Please divide your order into multiple orders.");
        }
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

                ConsoleInvoiceViewModel console = new ConsoleInvoiceViewModel();
                console.setManufacturer(cItem.getManufacturer());
                console.setModel(cItem.getModel());
                console.setMemoryAmount(cItem.getMemoryAmount());
                console.setProcessor(cItem.getProcessor());

                item = console;
                break;
            case "Games":
                Game gItem = gameDao.getGame(invoiceViewModel.getItemId());
                price = gItem.getPrice();

                GameInvoiceViewModel game = new GameInvoiceViewModel();
                game.setTitle(gItem.getTitle());
                game.setEsrbRating(gItem.getEsrbRating());
                game.setDescription(gItem.getDescription());
                game.setStudio(gItem.getStudio());

                item = game;
                break;
            case "T-Shirts":
                TShirt tItem = tShirtDao.getTShirt(invoiceViewModel.getItemId());
                price = tItem.getPrice();

                TShirtInvoiceViewModel tShirt = new TShirtInvoiceViewModel();
                tShirt.setSize(tItem.getSize());
                tShirt.setColor(tItem.getColor());
                tShirt.setDescription(tItem.getDescription());

                item = tShirt;
                break;
            default:
                throw new IllegalArgumentException("You must select a valid item type.");
        }
        invoiceViewModel.setItem(item);
        invoiceViewModel.setUnitPrice(price);

        return invoiceViewModel;
    }


    public void amendQuantityInDB(String itemType, int itemId, int quantity, String action) {

        Object item;
        switch (itemType) {
            case "Consoles":
                Console cItem = consoleDao.getConsole(itemId);
                int amountAvailable;
                if (action == "subtract") {
                    if (quantity <= cItem.getQuantity()) {
                        cItem.setQuantity(cItem.getQuantity() - quantity);
                        consoleDao.updateConsole(cItem);
                    } else {
                        amountAvailable = cItem.getQuantity();
                        throw new InsufficientQuantityException("We do not have that quantity. We only have " + amountAvailable + " of that item.");
                    }
                } else if (action == "add") {
                    cItem.setQuantity(cItem.getQuantity() + quantity);
                    consoleDao.updateConsole(cItem);
                }
                break;
            case "Games":
                Game gItem = gameDao.getGame(itemId);
                if (action == "subtract") {
                    if (quantity <= gItem.getQuantity()) {
                        gItem.setQuantity(gItem.getQuantity() - quantity);
                        gameDao.updateGame(gItem);
                    } else {
                        amountAvailable = gItem.getQuantity();
                        throw new InsufficientQuantityException("We do not have that quantity. We only have " + amountAvailable + " of that item.");
                    }
                } else if (action == "add") {
                    gItem.setQuantity(gItem.getQuantity() + quantity);
                    gameDao.updateGame(gItem);
                }
                break;
            case "T-Shirts":
                TShirt tItem = tShirtDao.getTShirt(itemId);
                if (action == "subtract") {
                    if (quantity <= tItem.getQuantity()) {
                        tItem.setQuantity(tItem.getQuantity() - quantity);
                        tShirtDao.updateTShirt(tItem);
                    } else {
                        amountAvailable = tItem.getQuantity();
                        throw new InsufficientQuantityException("We do not have that quantity. We only have " + amountAvailable + " that item.");
                    }
                } else if (action == "add") {
                    tItem.setQuantity(tItem.getQuantity() + quantity);
                    tShirtDao.updateTShirt(tItem);
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

        Invoice originalInvoice = invoiceDao.getInvoice(invoiceViewModel.getInvoiceId());

        // thrown here to prevent null pointer exception when try to invoke methods on originalInvoice
        if (originalInvoice == null)  {
            throw new IllegalArgumentException("The id provided does not exist.");
        }
        //int originalQuantity = invoiceDao.getInvoice(invoiceViewModel.getInvoiceId()).getQuantity();
        int originalQuantity = originalInvoice.getQuantity();

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
