package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.TShirtViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;

// USE MOCKS ********************

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtServiceTest {

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

    //public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel) {
    //public TShirtViewModel findTShirtById(int id) {
    @Test
    public void saveFindTShirt()
    {
        TShirtViewModel tShirtVM = new TShirtViewModel();

        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my blue shirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2));
        tShirtVM.setQuantity(2);
        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        TShirtViewModel fromService  = tShirtService.findTShirtById(tShirtVM.gettShirtId());

        //viewmodel.TShirtViewModel cannot be cast to model.TShirt

        assertEquals(tShirtVM, fromService);
//        assertEquals(tShirtVM.toString(), fromService.toString());

    }

    //public List<TShirt> findAllTShirts() {
    @Test
    public void findAllTShirts() {

        TShirtViewModel tShirtVM = new TShirtViewModel();

        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my blue shirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2));
        tShirtVM.setQuantity(2);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();

        tShirtVM.setSize("large");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("my favorite shirt");
        tShirtVM.setPrice(new BigDecimal(55.00).setScale(2));
        tShirtVM.setQuantity(10);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        List<TShirtViewModel> fromService = tShirtService.findAllTShirts();

        assertEquals(2, fromService.size());

    }

    //public void updateTShirt(TShirtViewModel tShirtViewModel) {
    @Test
    public void updateTShirt() {
        TShirtViewModel tShirtVM = new TShirtViewModel();

        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my blue shirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2));
        tShirtVM.setQuantity(2);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        tShirtVM.setDescription("my ugly blue shirt");
        tShirtVM.setPrice(new BigDecimal(5.00).setScale(2));

        tShirtService.updateTShirt(tShirtVM);

        TShirtViewModel fromService  = tShirtService.findTShirtById(tShirtVM.gettShirtId());

//        assertEquals(tShirtVM, fromService);

        assertEquals("my ugly blue shirt", fromService.getDescription());
        assertEquals(new BigDecimal(5.00).setScale(2), fromService.getPrice());
    }

    //public void removeTShirt(int id) {
    @Test
    public void removeTShirt() {

        TShirtViewModel tShirtVM = new TShirtViewModel();

        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my blue shirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2));
        tShirtVM.setQuantity(2);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        List<TShirtViewModel> fromService = tShirtService.findAllTShirts();

        assertEquals(1, fromService.size());

        tShirtService.removeTShirt(fromService.get(0).gettShirtId());

        fromService = tShirtService.findAllTShirts();

        assertEquals(0, fromService.size());

    }

    //public List<TShirtViewModel> findTShirtsByColor(String color) {
    @Test
    public void findTShirtsByColor() {

        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("large");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("my favorite shirt");
        tShirtVM.setPrice(new BigDecimal(55.00).setScale(2));
        tShirtVM.setQuantity(10);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my blue shirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2));
        tShirtVM.setQuantity(2);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("large");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my other blue shirt");
        tShirtVM.setPrice(new BigDecimal(10.00).setScale(2));
        tShirtVM.setQuantity(4);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        List<TShirtViewModel> tList = tShirtService.findTShirtsByColor("blue");
        assertEquals(2,tList.size());
        assertEquals("blue", tList.get(0).getColor());

        tList = tShirtService.findTShirtsByColor("black");
        assertEquals(1,tList.size());
        assertEquals("black", tList.get(0).getColor());

        tList = tShirtService.findTShirtsByColor("orange");
        assertEquals(0,tList.size());

    }

    //public List<TShirtViewModel> findTShirtsBySize(String size) {
    @Test
    public void findTShirtsBySize() {

        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("large");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("my favorite shirt");
        tShirtVM.setPrice(new BigDecimal(55.00).setScale(2));
        tShirtVM.setQuantity(10);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my blue shirt");
        tShirtVM.setPrice(new BigDecimal(15.00).setScale(2));
        tShirtVM.setQuantity(2);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("large");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("my other blue shirt");
        tShirtVM.setPrice(new BigDecimal(10.00).setScale(2));
        tShirtVM.setQuantity(4);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        List<TShirtViewModel> tList = tShirtService.findTShirtsBySize("large");
        assertEquals(2,tList.size());
        assertEquals("large", tList.get(0).getSize());

        tList = tShirtService.findTShirtsBySize("small");
        assertEquals(1,tList.size());
        assertEquals("small", tList.get(0).getSize());

        tList = tShirtService.findTShirtsBySize("medium");
        assertEquals(0,tList.size());

    }

}
