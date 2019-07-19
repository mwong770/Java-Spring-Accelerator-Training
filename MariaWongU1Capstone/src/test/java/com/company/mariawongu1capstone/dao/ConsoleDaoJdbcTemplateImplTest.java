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
public class ConsoleDaoJdbcTemplateImplTest {

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
    public void addGetDeleteConsole() {

        Console console = new Console();
        console.setModel("model 1");
        console.setManufacturer("Manufacturer 1");
        console.setMemoryAmount("lots of memory");
        console.setProcessor("best processor");
        console.setPrice(new BigDecimal(100.00).setScale(2));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        Console console1 = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console, console1);

        consoleDao.deleteConsole(console.getConsoleId());
        console1 = consoleDao.getConsole(console.getConsoleId());
        assertNull(console1);
    }


    @Test
    public void updateConsole() {

        Console console = new Console();
        console.setModel("model 1");
        console.setManufacturer("Manufacturer 1");
        console.setMemoryAmount("lots of memory");
        console.setProcessor("best processor");
        console.setPrice(new BigDecimal(100.00).setScale(2));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        console.setPrice(new BigDecimal(150.00).setScale(2));
        console.setQuantity(30);

        consoleDao.updateConsole(console);

        Console console1  = consoleDao.getConsole(console.getConsoleId());
        assertEquals(console, console1);
    }

    @Test
    public void getAllConsoles() {

        Console console = new Console();
        console.setModel("model 1");
        console.setManufacturer("Manufacturer 1");
        console.setMemoryAmount("lots of memory");
        console.setProcessor("best processor");
        console.setPrice(new BigDecimal(100.00).setScale(2));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        console = new Console();
        console.setModel("model 2");
        console.setManufacturer("Manufacturer 2");
        console.setMemoryAmount("little memory");
        console.setProcessor("worse processor");
        console.setPrice(new BigDecimal(1.00).setScale(2));
        console.setQuantity(1);

        console = consoleDao.addConsole(console);

        List<Console> cList = consoleDao.getAllConsoles();
        assertEquals(2, cList.size());
    }

    @Test
    public void findConsolesByManufacturer()
    {
        Console console = new Console();
        console.setModel("model 1");
        console.setManufacturer("Manufacturer 1");
        console.setMemoryAmount("lots of memory");
        console.setProcessor("best processor");
        console.setPrice(new BigDecimal(100.00).setScale(2));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        console = new Console();
        console.setModel("model 2");
        console.setManufacturer("Manufacturer 2");
        console.setMemoryAmount("little memory");
        console.setProcessor("worse processor");
        console.setPrice(new BigDecimal(1.00).setScale(2));
        console.setQuantity(1);

        console = consoleDao.addConsole(console);

        console = new Console();
        console.setModel("model 3");
        console.setManufacturer("Manufacturer 1");
        console.setMemoryAmount("medium memory");
        console.setProcessor("medium processor");
        console.setPrice(new BigDecimal(50.00).setScale(2));
        console.setQuantity(5);

        console = consoleDao.addConsole(console);

        List<Console> cList = consoleDao.findConsolesByManufacturer("Manufacturer 1");
        assertEquals(2,cList.size());

        cList = consoleDao.findConsolesByManufacturer("Manufacturer 2");
        assertEquals(1,cList.size());

        cList = consoleDao.findConsolesByManufacturer("Unknown Manufacturer");
        assertEquals(0,cList.size());

    }

}

/*
    Console addConsole(Console console);
    Console getConsole(int id);
    void deleteConsole(int id);

    void updateConsole(Console console);

    List<Console> getAllConsoles();

    List<Console> findConsolesByManufacturer(String manufacturer);
 */