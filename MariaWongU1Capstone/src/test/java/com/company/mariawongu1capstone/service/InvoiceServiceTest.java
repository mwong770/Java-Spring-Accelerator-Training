package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.ConsoleViewModel;
import com.company.mariawongu1capstone.viewmodel.InvoiceViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;


// ***** NEED TO SET PROCESSING AND STATE FEES PROGRAMMATICALLY ******

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceServiceTest {

    @Autowired
    ConsoleService consoleService;
    @Autowired
    GameService gameService;
    @Autowired
    InvoiceService invoiceService;
    @Autowired
    TShirtService tShirtService;

    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    TShirtDao tShirtDao;
    @Autowired
    ProcessingFeeDao processingFeeDao;
    @Autowired
    SalesTaxRateDao salesTaxRateDao;


    // clear console, game, invoice, and tshirt tables in database
    @Before
    public void setUp() throws Exception {

        List<Console> consoles = consoleDao.getAllConsoles();
        for (Console c : consoles) {
            consoleDao.deleteConsole(c.getConsoleId());
        }

        List<Game> games = gameDao.getAllGames();
        for (Game g : games) {
            gameDao.deleteGame(g.getGameId());
        }

        List<Invoice> invoices = invoiceDao.getAllInvoices();
        for (Invoice i : invoices) {
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }

        List<TShirt> tShirts = tShirtDao.getAllTShirts();
        for (TShirt t : tShirts) {
            tShirtDao.deleteTShirt(t.gettShirtId());
        }
    }

    // ***** NEED TO SET PROCESSING AND STATE FEES PROGRAMMATICALLY ******

    //public InvoiceViewModel saveInvoice(InvoiceViewModel invoiceViewModel) {
    //public InvoiceViewModel findInvoice(int id) {
    @Test
    public void saveFindInvoice() {

        //save item to get item type and id
        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        int itemId = consoleVM.getConsoleId();

        // create invoice view model
        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(itemId);
        invoiceVM.setUnitPrice(new BigDecimal(50.00).setScale(2));
        invoiceVM.setQuantity(2);
        invoiceVM.setSubtotal(new BigDecimal(100.00).setScale(2));
        invoiceVM.setTax(new BigDecimal(5.00).setScale(2));
        invoiceVM.setProcessingFee(new BigDecimal(10.00).setScale(2));
        invoiceVM.setTotal(new BigDecimal(115.00).setScale(2));

        // save invoice adds the item to the view model
        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        InvoiceViewModel fromService  = invoiceService.findInvoice(invoiceVM.getInvoiceId());

        assertEquals(invoiceVM, fromService);
//        assertEquals(consoleVM.toString(), fromService.toString());

    }

    // ***** NEED TO SET PROCESSING AND STATE FEES PROGRAMMATICALLY ******

    //public List<InvoiceViewModel> findAllInvoices() {
    @Test
    public void findAllInvoices() {

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(0);
        invoiceVM.setUnitPrice(new BigDecimal(50.00).setScale(2));
        invoiceVM.setQuantity(2);
        invoiceVM.setSubtotal(new BigDecimal(100.00).setScale(2));
        invoiceVM.setTax(new BigDecimal(5.00).setScale(2));
        invoiceVM.setProcessingFee(new BigDecimal(10.00).setScale(2));
        invoiceVM.setTotal(new BigDecimal(115.00).setScale(2));

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("Mary");
        invoiceVM.setStreet("Mary's street");
        invoiceVM.setCity("Mary's city");
        invoiceVM.setState("TX");
        invoiceVM.setZipCode("67890");
        invoiceVM.setItemType("Games");
        invoiceVM.setItemId(1);
        invoiceVM.setUnitPrice(new BigDecimal(250.00).setScale(2));
        invoiceVM.setQuantity(1);
        invoiceVM.setSubtotal(new BigDecimal(250.00).setScale(2));
        invoiceVM.setTax(new BigDecimal(10.00).setScale(2));
        invoiceVM.setProcessingFee(new BigDecimal(20.00).setScale(2));
        invoiceVM.setTotal(new BigDecimal(280.00).setScale(2));

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        List<InvoiceViewModel> fromService = invoiceService.findAllInvoices();

        assertEquals(2, fromService.size());

    }

    // ***** NEED TO SET PROCESSING AND STATE FEES PROGRAMMATICALLY ******

    //public void updateInvoice(InvoiceViewModel invoiceViewModel) {
    @Test
    public void updateInvoice() {

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(0);
        invoiceVM.setUnitPrice(new BigDecimal(50.00).setScale(2));
        invoiceVM.setQuantity(2);
        invoiceVM.setSubtotal(new BigDecimal(100.00).setScale(2));
        invoiceVM.setTax(new BigDecimal(5.00).setScale(2));
        invoiceVM.setProcessingFee(new BigDecimal(10.00).setScale(2));
        invoiceVM.setTotal(new BigDecimal(115.00).setScale(2));

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        invoiceVM.setProcessingFee(new BigDecimal(15.00).setScale(2));
        invoiceVM.setTotal(new BigDecimal(120.00).setScale(2));

        invoiceService.updateInvoice(invoiceVM);

        InvoiceViewModel fromService  = invoiceService.findInvoice(invoiceVM.getInvoiceId());
        assertEquals(new BigDecimal(15.00).setScale(2), fromService.getProcessingFee());
        assertEquals(new BigDecimal(120.00).setScale(2), fromService.getTotal());

    }

    // ***** NEED TO SET PROCESSING AND STATE FEES PROGRAMMATICALLY ******

    //public void removeInvoice(int id) {
    @Test
    public void removeInvoice() {

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(0);
        invoiceVM.setUnitPrice(new BigDecimal(50.00).setScale(2));
        invoiceVM.setQuantity(2);
        invoiceVM.setSubtotal(new BigDecimal(100.00).setScale(2));
        invoiceVM.setTax(new BigDecimal(5.00).setScale(2));
        invoiceVM.setProcessingFee(new BigDecimal(10.00).setScale(2));
        invoiceVM.setTotal(new BigDecimal(115.00).setScale(2));

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        List<InvoiceViewModel> fromService = invoiceService.findAllInvoices();

        assertEquals(1, fromService.size());

        invoiceService.removeInvoice(fromService.get(0).getInvoiceId());

        fromService = invoiceService.findAllInvoices();

        assertEquals(0, fromService.size());

    }


}
