package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.ConsoleViewModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ConsoleServiceTest {

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

    //public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel) {
    //public ConsoleViewModel findConsoleById(int id) {
    @Test
    public void saveFindConsole() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        ConsoleViewModel fromService  = consoleService.findConsoleById(consoleVM.getConsoleId());
        assertEquals(consoleVM, fromService);
//        assertEquals(consoleVM.toString(), fromService.toString());

    }

    //public List<Console> findAllConsoles() {
    @Test
    public void findAllConsoles() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 2");
        consoleVM.setManufacturer("manufacturer 2");
        consoleVM.setMemoryAmount("little memory");
        consoleVM.setProcessor("worst processor");
        consoleVM.setPrice(new BigDecimal(50.00).setScale(2));
        consoleVM.setQuantity(1);

        consoleVM = consoleService.saveConsole(consoleVM);

        List<ConsoleViewModel> fromService = consoleService.findAllConsoles();

        assertEquals(2, fromService.size());

    }

    //public void updateConsole(ConsoleViewModel consoleViewModel) {
    @Test
    public void updateConsole() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        consoleVM.setPrice(new BigDecimal(150.00).setScale(2));
        consoleVM.setQuantity(30);

        consoleService.updateConsole(consoleVM);

        ConsoleViewModel fromService  = consoleService.findConsoleById(consoleVM.getConsoleId());
        assertEquals(new BigDecimal(150.00).setScale(2), fromService.getPrice());
        assertEquals(30, fromService.getQuantity());

    }


    //public void removeConsole(int id) {
    @Test
    public void removeConsole() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("model 3");
        consoleVM.setManufacturer("manufacturer 3");
        consoleVM.setMemoryAmount("moderate memory");
        consoleVM.setProcessor("moderate processor");
        consoleVM.setPrice(new BigDecimal(70.00).setScale(2));
        consoleVM.setQuantity(4);

        consoleVM = consoleService.saveConsole(consoleVM);

        List<ConsoleViewModel> fromService = consoleService.findAllConsoles();

        assertEquals(1, fromService.size());

        consoleService.removeConsole(fromService.get(0).getConsoleId());

        fromService = consoleService.findAllConsoles();

        assertEquals(0, fromService.size());

    }


//    //public List<ConsoleViewModel> findConsolesByManufacturer(String manufacturer) {
    @Test
    public void findConsolesByManufacturer()
    {

        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("Manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        consoleVM = new ConsoleViewModel();
        consoleVM.setModel("model 2");
        consoleVM.setManufacturer("Manufacturer 2");
        consoleVM.setMemoryAmount("little memory");
        consoleVM.setProcessor("worse processor");
        consoleVM.setPrice(new BigDecimal(1.00).setScale(2));
        consoleVM.setQuantity(1);

        consoleVM = consoleService.saveConsole(consoleVM);

        consoleVM = new ConsoleViewModel();
        consoleVM.setModel("model 3");
        consoleVM.setManufacturer("Manufacturer 1");
        consoleVM.setMemoryAmount("medium memory");
        consoleVM.setProcessor("medium processor");
        consoleVM.setPrice(new BigDecimal(50.00).setScale(2));
        consoleVM.setQuantity(5);

        consoleVM = consoleService.saveConsole(consoleVM);

        List<ConsoleViewModel> cList = consoleService.findConsolesByManufacturer("Manufacturer 1");
        assertEquals(2,cList.size());
        assertEquals("Manufacturer 1", cList.get(0).getManufacturer());

        cList = consoleService.findConsolesByManufacturer("Manufacturer 2");
        assertEquals(1,cList.size());
        assertEquals("Manufacturer 2", cList.get(0).getManufacturer());

        cList = consoleService.findConsolesByManufacturer("Unknown Manufacturer");
        assertEquals(0,cList.size());

    }

}
