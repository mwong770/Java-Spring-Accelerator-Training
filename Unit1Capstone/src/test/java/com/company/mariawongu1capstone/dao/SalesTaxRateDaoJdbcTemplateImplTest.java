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
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxRateDaoJdbcTemplateImplTest {

    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    TShirtDao tShirtDao;
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

    // tests getSalesTaxRate()
    @Test
    public void getSalesTaxRate() {

        BigDecimal rate = salesTaxRateDao.getSalesTaxRate("AK");
        assertEquals(new BigDecimal(0.06).setScale(2, RoundingMode.HALF_UP), rate);

        rate = salesTaxRateDao.getSalesTaxRate("AL");
        assertEquals(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP), rate);
    }

}
