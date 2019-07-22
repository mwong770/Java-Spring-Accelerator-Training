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
public class TShirtDaoJdbcTemplateImplTest {

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
    public void addGetDeleteTShirt() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("small");
        tShirt.setColor("blue");
        tShirt.setDescription("my blue shirt");
        tShirt.setPrice(new BigDecimal(15.00).setScale(2));
        tShirt.setQuantity(2);

        tShirt = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirt.gettShirtId());
        assertEquals(tShirt1,tShirt);

        tShirtDao.deleteTShirt(tShirt.gettShirtId());
        tShirt1 = tShirtDao.getTShirt(tShirt.gettShirtId());
        assertNull(tShirt1);
    }

    @Test
    public void updateTShirt() {

        TShirt tShirt = new TShirt();
        tShirt.setSize("small");
        tShirt.setColor("blue");
        tShirt.setDescription("my blue shirt");
        tShirt.setPrice(new BigDecimal(15.00).setScale(2));
        tShirt.setQuantity(2);

        tShirt = tShirtDao.addTShirt(tShirt);

        tShirt.setDescription("my ugly blue shirt");
        tShirt.setPrice(new BigDecimal(5.00).setScale(2));
        tShirtDao.updateTShirt(tShirt);

        TShirt tShirt1  = tShirtDao.getTShirt(tShirt.gettShirtId());
        assertEquals(tShirt1,tShirt);
    }

    @Test
    public void getAllTShirts() {
        TShirt tShirt = new TShirt();
        tShirt.setSize("large");
        tShirt.setColor("black");
        tShirt.setDescription("my favorite shirt");
        tShirt.setPrice(new BigDecimal(55.00).setScale(2));
        tShirt.setQuantity(10);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("small");
        tShirt.setColor("blue");
        tShirt.setDescription("my blue shirt");
        tShirt.setPrice(new BigDecimal(15.00).setScale(2));
        tShirt.setQuantity(2);

        tShirtDao.addTShirt(tShirt);

        List<TShirt> tList = tShirtDao.getAllTShirts();
        assertEquals(2, tList.size());
    }

    @Test
    public void findTShirtsByColor()
    {
        TShirt tShirt = new TShirt();
        tShirt.setSize("large");
        tShirt.setColor("black");
        tShirt.setDescription("my favorite shirt");
        tShirt.setPrice(new BigDecimal(55.00).setScale(2));
        tShirt.setQuantity(10);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("small");
        tShirt.setColor("blue");
        tShirt.setDescription("my blue shirt");
        tShirt.setPrice(new BigDecimal(15.00).setScale(2));
        tShirt.setQuantity(2);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("medium");
        tShirt.setColor("blue");
        tShirt.setDescription("my other blue shirt");
        tShirt.setPrice(new BigDecimal(10.00).setScale(2));
        tShirt.setQuantity(4);

        tShirtDao.addTShirt(tShirt);

        List<TShirt> tList = tShirtDao.findTShirtsByColor("blue");
        assertEquals(2, tList.size());

        tList = tShirtDao.findTShirtsByColor("black");
        assertEquals(1, tList.size());

        tList = tShirtDao.findTShirtsByColor("green");
        assertEquals(0, tList.size());

    }
    
    @Test
    public void findTShirtsBySize()
    {
        TShirt tShirt = new TShirt();
        tShirt.setSize("large");
        tShirt.setColor("black");
        tShirt.setDescription("my favorite shirt");
        tShirt.setPrice(new BigDecimal(55.00).setScale(2));
        tShirt.setQuantity(10);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("small");
        tShirt.setColor("blue");
        tShirt.setDescription("my blue shirt");
        tShirt.setPrice(new BigDecimal(15.00).setScale(2));
        tShirt.setQuantity(2);

        tShirtDao.addTShirt(tShirt);

        tShirt = new TShirt();
        tShirt.setSize("large");
        tShirt.setColor("blue");
        tShirt.setDescription("my other blue shirt");
        tShirt.setPrice(new BigDecimal(10.00).setScale(2));
        tShirt.setQuantity(4);

        tShirtDao.addTShirt(tShirt);

        List<TShirt> tList = tShirtDao.findTShirtsBySize("large");
        assertEquals(2, tList.size());

        tList = tShirtDao.findTShirtsBySize("small");
        assertEquals(1, tList.size());

        tList = tShirtDao.findTShirtsBySize("medium");
        assertEquals(0, tList.size());

    }


}
