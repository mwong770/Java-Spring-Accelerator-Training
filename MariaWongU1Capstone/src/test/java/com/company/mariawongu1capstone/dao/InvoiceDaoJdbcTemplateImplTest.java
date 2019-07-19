package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoJdbcTemplateImplTest {

    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    TShirtDao tShirtDao;

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

    @Test
    public void addGetDeleteInvoice() {
        Invoice invoice = new Invoice();
        invoice.setName("John");
        invoice.setStreet("John's street");
        invoice.setCity("John's city");
        invoice.setState("AZ");
        invoice.setZipCode("12345");
        invoice.setItemType("Console");
        invoice.setItemId(0);
        invoice.setUnitPrice(new BigDecimal(50.00).setScale(2));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal(100.00).setScale(2));
        invoice.setTax(new BigDecimal(5.00).setScale(2));
        invoice.setProcessingFee(new BigDecimal(10.00).setScale(2));
        invoice.setTotal(new BigDecimal(115.00).setScale(2));

        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice1,invoice);

        invoiceDao.deleteInvoice(invoice.getInvoiceId());
        invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertNull(invoice1);
    }

    @Test
    public void updateInvoice() {

        Invoice invoice = new Invoice();
        invoice.setName("John");
        invoice.setStreet("John's street");
        invoice.setCity("John's city");
        invoice.setState("AZ");
        invoice.setZipCode("12345");
        invoice.setItemType("Console");
        invoice.setItemId(0);
        invoice.setUnitPrice(new BigDecimal(50.00).setScale(2));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal(100.00).setScale(2));
        invoice.setTax(new BigDecimal(5.00).setScale(2));
        invoice.setProcessingFee(new BigDecimal(10.00).setScale(2));
        invoice.setTotal(new BigDecimal(115.00).setScale(2));

        invoice = invoiceDao.addInvoice(invoice);

        invoice.setTax(new BigDecimal(15.00).setScale(2));
        invoice.setProcessingFee(new BigDecimal(15.00).setScale(2));
        invoice.setTotal(new BigDecimal(130.00).setScale(2));
        invoiceDao.updateInvoice(invoice);

        Invoice invoice1  = invoiceDao.getInvoice(invoice.getInvoiceId());
        assertEquals(invoice,invoice1);
    }

    @Test
    public void getAllInvoices() {
        Invoice invoice = new Invoice();
        invoice.setName("John");
        invoice.setStreet("John's street");
        invoice.setCity("John's city");
        invoice.setState("AZ");
        invoice.setZipCode("12345");
        invoice.setItemType("Console");
        invoice.setItemId(0);
        invoice.setUnitPrice(new BigDecimal(50.00).setScale(2));
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal(100.00).setScale(2));
        invoice.setTax(new BigDecimal(5.00).setScale(2));
        invoice.setProcessingFee(new BigDecimal(10.00).setScale(2));
        invoice.setTotal(new BigDecimal(115.00).setScale(2));

        invoice = invoiceDao.addInvoice(invoice);

        invoice = new Invoice();
        invoice.setName("Mary");
        invoice.setStreet("Mary's street");
        invoice.setCity("Mary's city");
        invoice.setState("TX");
        invoice.setZipCode("67890");
        invoice.setItemType("Game");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal(250.00).setScale(2));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal(250.00).setScale(2));
        invoice.setTax(new BigDecimal(10.00).setScale(2));
        invoice.setProcessingFee(new BigDecimal(20.00).setScale(2));
        invoice.setTotal(new BigDecimal(280.00).setScale(2));

        invoice = invoiceDao.addInvoice(invoice);

        List<Invoice> iList = invoiceDao.getAllInvoices();
        assertEquals(2, iList.size());
    }


}