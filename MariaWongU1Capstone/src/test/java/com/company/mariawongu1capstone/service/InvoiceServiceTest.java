package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.ConsoleViewModel;
import com.company.mariawongu1capstone.viewmodel.GameViewModel;
import com.company.mariawongu1capstone.viewmodel.InvoiceViewModel;
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

// USE MOCKS ********************

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

    @Test
    public void saveFindInvoice() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        // this will get turned into a mock
        consoleVM = consoleService.saveConsole(consoleVM);

        // create invoice view model
        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(consoleVM.getConsoleId());
        invoiceVM.setQuantity(2);

        // save invoice adds the item to the view model
        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        InvoiceViewModel fromService  = invoiceService.findInvoice(invoiceVM.getInvoiceId());

        assertEquals(invoiceVM, fromService);

    }

    //public List<InvoiceViewModel> findAllInvoices() {
    @Test
    public void findAllInvoices() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        GameViewModel gameVM = new GameViewModel();

        gameVM.setTitle("title 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(consoleVM.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("Mary");
        invoiceVM.setStreet("Mary's street");
        invoiceVM.setCity("Mary's city");
        invoiceVM.setState("TX");
        invoiceVM.setZipCode("67890");
        invoiceVM.setItemType("Games");
        invoiceVM.setItemId(gameVM.getGameId());
        invoiceVM.setQuantity(1);

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        List<InvoiceViewModel> fromService = invoiceService.findAllInvoices();

        assertEquals(2, fromService.size());

    }

    //public void updateInvoice(InvoiceViewModel invoiceViewModel) {
    @Test
    public void updateInvoice() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(consoleVM.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        invoiceVM.setProcessingFee(new BigDecimal(15.00).setScale(2));
        invoiceVM.setTotal(new BigDecimal(120.00).setScale(2));

        invoiceVM.setStreet("John's new street");
        invoiceVM.setZipCode("34567");

        invoiceService.updateInvoice(invoiceVM);

        InvoiceViewModel fromService  = invoiceService.findInvoice(invoiceVM.getInvoiceId());
        assertEquals("John's new street", fromService.getStreet());
        assertEquals("34567", fromService.getZipCode());

    }

    @Test
    public void getItemDetails() {
        Console console = new Console();

        console.setModel("model 1");
        console.setManufacturer("manufacturer 1");
        console.setMemoryAmount("lots of memory");
        console.setProcessor("best processor");
        console.setPrice(new BigDecimal(100.00).setScale(2));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(console.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceVM = invoiceService.getItemDetails(invoiceVM);

        assertEquals(console.toString(), invoiceVM.getItem().toString());
        assertEquals(new BigDecimal(100.00).setScale(2), invoiceVM.getUnitPrice());

        // checks if works for a different item type

        TShirt tShirt = new TShirt();
        tShirt.setSize("small");
        tShirt.setColor("blue");
        tShirt.setDescription("my blue shirt");
        tShirt.setPrice(new BigDecimal(15.00).setScale(2));
        tShirt.setQuantity(2);

        tShirt = tShirtDao.addTShirt(tShirt);

        InvoiceViewModel invoiceVMWithGame = new InvoiceViewModel();
        invoiceVMWithGame = new InvoiceViewModel();
        invoiceVMWithGame.setName("Mary");
        invoiceVMWithGame.setStreet("Mary's street");
        invoiceVMWithGame.setCity("Mary's city");
        invoiceVMWithGame.setState("TX");
        invoiceVMWithGame.setZipCode("67890");
        invoiceVMWithGame.setItemType("T-Shirts");
        invoiceVMWithGame.setItemId(tShirt.gettShirtId());
        invoiceVMWithGame.setQuantity(1);

        invoiceVMWithGame = invoiceService.getItemDetails(invoiceVMWithGame);

        assertEquals(tShirt.toString(), invoiceVMWithGame.getItem().toString());
        assertEquals(new BigDecimal(15.00).setScale(2), invoiceVMWithGame.getUnitPrice());
    }

    @Test
    public void calculateTotal() {

        Console consoleVM = new Console();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(2);

        // this will get turned into a mock
        consoleVM = consoleDao.addConsole(consoleVM);

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(consoleVM.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceVM = invoiceService.calculateTotal(invoiceVM);

        // tests quantity below 10

        //subtotal (unitPrice * quantity) (100 * 2)
        assertEquals(new BigDecimal(200.00).setScale(2), invoiceVM.getSubtotal());
        //tax (subtotal * taxRate AZ) (200 * 0.04 = 8)
        assertEquals(new BigDecimal(8.00).setScale(2), invoiceVM.getTax());
        //processing fee for Consoles (0 additional fee)
        assertEquals(new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP), invoiceVM.getProcessingFee());
        //total (subtotal + tax + processing fee)
        assertEquals(new BigDecimal(222.99).setScale(2, RoundingMode.HALF_UP), invoiceVM.getTotal());

        InvoiceViewModel invoiceVMWithAddedFee = new InvoiceViewModel();

        invoiceVMWithAddedFee.setName("John");
        invoiceVMWithAddedFee.setStreet("John's street");
        invoiceVMWithAddedFee.setCity("John's city");
        invoiceVMWithAddedFee.setState("AZ");
        invoiceVMWithAddedFee.setZipCode("12345");
        invoiceVMWithAddedFee.setItemType("Consoles");
        invoiceVMWithAddedFee.setItemId(consoleVM.getConsoleId());
        invoiceVMWithAddedFee.setQuantity(12);

        invoiceVMWithAddedFee = invoiceService.calculateTotal(invoiceVMWithAddedFee);

        // tests quantity above 10

        //subtotal (unitPrice * quantity) (100 * 12)
        assertEquals(new BigDecimal(1200.00).setScale(2), invoiceVMWithAddedFee.getSubtotal());
        //tax (subtotal * taxRate AZ) (1200 * 0.04 = 8)
        assertEquals(new BigDecimal(48.00).setScale(2), invoiceVMWithAddedFee.getTax());
        //processing fee for Consoles (14.99 + 15.49 additional fee = )
        assertEquals(new BigDecimal(30.48).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getProcessingFee().setScale(2, RoundingMode.HALF_UP));
        //total (subtotal + tax + processing fee)
        assertEquals(new BigDecimal(1278.48).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getTotal().setScale(2, RoundingMode.HALF_UP));

    }

    @Test
    public void amendQuantityInDB() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model x");
        consoleVM.setManufacturer("manufacturer x");
        consoleVM.setMemoryAmount("x memory");
        consoleVM.setProcessor("x processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        invoiceService.amendQuantityInDB("Consoles", consoleVM.getConsoleId(), 10, "add");

        int newQuantity = consoleDao.getConsole(consoleVM.getConsoleId()).getQuantity();

        assertEquals(20, newQuantity);

        invoiceService.amendQuantityInDB("Consoles", consoleVM.getConsoleId(), 5, "subtract");

        newQuantity = consoleDao.getConsole(consoleVM.getConsoleId()).getQuantity();

        assertEquals(15, newQuantity);
        
    }

    /*
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
     */

    //public void removeInvoice(int id) {
    @Test
    public void removeInvoice() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("John");
        invoiceVM.setStreet("John's street");
        invoiceVM.setCity("John's city");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("12345");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(consoleVM.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        List<InvoiceViewModel> fromService = invoiceService.findAllInvoices();

        assertEquals(1, fromService.size());

        invoiceService.removeInvoice(fromService.get(0).getInvoiceId());

        fromService = invoiceService.findAllInvoices();

        assertEquals(0, fromService.size());

    }

}

