package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.viewmodel.ConsoleViewModel;
import com.company.mariawongu1capstone.viewmodel.GameViewModel;
import com.company.mariawongu1capstone.viewmodel.InvoiceViewModel;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {

    ConsoleService consoleService;
    GameService gameService;
    InvoiceService invoiceService;

    ConsoleDao consoleDao;
    GameDao gameDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception {

        // configure mock objects
        setUpConsoleDaoMock();
        setUpGameDaoMock();
        setUpInvoiceDaoMock();
        setUpProcessingFeeDaoMock();
        setUpSalesTaxRateDaoMock();

        // Passes mock objects
        gameService = new GameService(gameDao);
        consoleService = new ConsoleService(consoleDao);
        invoiceService = new InvoiceService(consoleDao, gameDao, invoiceDao, processingFeeDao, salesTaxRateDao, tShirtDao);

    }

    @Test
    public void saveFindInvoice() {

        // create console view model to use id in invoice view model
        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("Switch");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemoryAmount("4GB");
        consoleVM.setProcessor("NVIDIA");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        // create invoice view model
        InvoiceViewModel invoiceVM = new InvoiceViewModel();
        invoiceVM.setName("Terry DoByne");
        invoiceVM.setStreet("2380 W US Hwy 89");
        invoiceVM.setCity("Sedona");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("86336");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(consoleVM.getConsoleId());
        invoiceVM.setQuantity(2);

        // save invoice view model
        invoiceVM = invoiceService.saveInvoice(invoiceVM); // fields get calculated ?

        InvoiceViewModel fromService  = invoiceService.findInvoice(invoiceVM.getInvoiceId()); // don't ?

        // Fix-up calculated fields in mock object from service
//        fromService.setUnitPrice(invoiceVM.getUnitPrice());
//        fromService.setSubtotal(invoiceVM.getSubtotal());
//        fromService.setTax(invoiceVM.getTax());
//        fromService.setProcessingFee(invoiceVM.getProcessingFee());
//        fromService.setTotal(invoiceVM.getTotal());

        assertEquals(invoiceVM, fromService);

    }

    @Test
    public void findAllInvoices() {

        // create console view model to use id in invoice view model
        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("Switch");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemoryAmount("4GB");
        consoleVM.setProcessor("NVIDIA");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        // create invoice view model
        InvoiceViewModel invoiceVM = new InvoiceViewModel();
        invoiceVM.setName("Terry DoByne");
        invoiceVM.setStreet("2380 W US Hwy 89");
        invoiceVM.setCity("Sedona");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("86336");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(consoleVM.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceService.saveInvoice(invoiceVM);

        // create game view model to use id in invoice view model
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("The Legend of Zelda: Link's Awakening");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        gameVM.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        // create invoice view model
        invoiceVM = new InvoiceViewModel();
        invoiceVM.setName("Mari Garcia");
        invoiceVM.setStreet("1000 Sturdivant Street");
        invoiceVM.setCity("Cary");
        invoiceVM.setState("NC");
        invoiceVM.setZipCode("27511");
        invoiceVM.setItemType("Games");
        invoiceVM.setItemId(1);
        invoiceVM.setQuantity(1);

        invoiceService.saveInvoice(invoiceVM);

        List<InvoiceViewModel> fromService = invoiceService.findAllInvoices();

        assertEquals(2, fromService.size());

    }

    @Test
    public void getItemDetails() {

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4GB");
        console.setProcessor("NVIDIA");
        console.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        InvoiceViewModel invoiceVM = new InvoiceViewModel();

        invoiceVM.setName("Terry DoByne");
        invoiceVM.setStreet("2380 W US Hwy 89");
        invoiceVM.setCity("Sedona");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("86336");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(console.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceVM = invoiceService.getItemDetails(invoiceVM);

        assertEquals(console.toString(), invoiceVM.getItem().toString());
        assertEquals(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP), invoiceVM.getUnitPrice());

        // Checks if works for a different item type

        Game game = new Game();
        game.setTitle("The Legend of Zelda: Link's Awakening");
        game.setEsrbRating("E");
        game.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        game.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        game.setStudio("Nintendo");
        game.setQuantity(10);

        game = gameDao.addGame(game);

        InvoiceViewModel invoiceVMWithGame = new InvoiceViewModel();
        invoiceVMWithGame = new InvoiceViewModel();
        invoiceVMWithGame.setName("Mari Garcia");
        invoiceVMWithGame.setStreet("1000 Sturdivant Street");
        invoiceVMWithGame.setCity("Cary");
        invoiceVMWithGame.setState("NC");
        invoiceVMWithGame.setZipCode("27511");
        invoiceVMWithGame.setItemType("Games");
        invoiceVMWithGame.setItemId(game.getGameId());
        invoiceVMWithGame.setQuantity(1);

        invoiceVMWithGame = invoiceService.getItemDetails(invoiceVMWithGame);

        assertEquals(game.toString(), invoiceVMWithGame.getItem().toString());
        assertEquals(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP), invoiceVMWithGame.getUnitPrice());
    }

    @Test
    public void calculateTotal() {

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4GB");
        console.setProcessor("NVIDIA");
        console.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        console.setQuantity(10);

        console = consoleDao.addConsole(console);

        InvoiceViewModel invoiceVM = new InvoiceViewModel();
        invoiceVM.setName("Terry DoByne");
        invoiceVM.setStreet("2380 W US Hwy 89");
        invoiceVM.setCity("Sedona");
        invoiceVM.setState("AZ");
        invoiceVM.setZipCode("86336");
        invoiceVM.setItemType("Consoles");
        invoiceVM.setItemId(console.getConsoleId());
        invoiceVM.setQuantity(2);

        invoiceVM = invoiceService.calculateTotal(invoiceVM);

        // tests quantity below 10

        //subtotal (unitPrice * quantity) (100 * 2)
        assertEquals(new BigDecimal(200.00).setScale(2, RoundingMode.HALF_UP), invoiceVM.getSubtotal());
        //tax (subtotal * taxRate AZ) (200 * 0.04 = 8)
        assertEquals(new BigDecimal(8.00).setScale(2, RoundingMode.HALF_UP), invoiceVM.getTax());
        //processing fee for Consoles (0 additional fee)
        assertEquals(new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP), invoiceVM.getProcessingFee());
        //total (subtotal + tax + processing fee)
        assertEquals(new BigDecimal(222.99).setScale(2, RoundingMode.HALF_UP), invoiceVM.getTotal());

        InvoiceViewModel invoiceVMWithAddedFee = new InvoiceViewModel();

        invoiceVMWithAddedFee.setName("Terry DoByne");
        invoiceVMWithAddedFee.setStreet("2380 W US Hwy 89");
        invoiceVMWithAddedFee.setCity("Sedona");
        invoiceVMWithAddedFee.setState("AZ");
        invoiceVMWithAddedFee.setZipCode("86336");
        invoiceVMWithAddedFee.setItemType("Consoles");
        invoiceVMWithAddedFee.setItemId(console.getConsoleId());
        invoiceVMWithAddedFee.setQuantity(12);

        invoiceVMWithAddedFee = invoiceService.calculateTotal(invoiceVMWithAddedFee);

        // tests quantity above 10

        //subtotal (unitPrice * quantity) (100 * 12)
        assertEquals(new BigDecimal(1200.00).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getSubtotal());
        //tax (subtotal * taxRate AZ) (1200 * 0.04 = 8)
        assertEquals(new BigDecimal(48.00).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getTax());
        //processing fee for Consoles (14.99 + 15.49 additional fee = )
        assertEquals(new BigDecimal(30.48).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getProcessingFee().setScale(2, RoundingMode.HALF_UP));
        //total (subtotal + tax + processing fee)
        assertEquals(new BigDecimal(1278.48).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getTotal().setScale(2, RoundingMode.HALF_UP));

    }

    // FIND OUT IF THESE CAN BE TESTED NOW THAT WE ARE USING MOCKS *******************

/*
    @Test
    public void amendQuantityInDB() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model x");
        consoleVM.setManufacturer("manufacturer x");
        consoleVM.setMemoryAmount("x memory");
        consoleVM.setProcessor("x processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP);
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        invoiceService.amendQuantityInDB("Consoles", consoleVM.getConsoleId(), 10, "add");

        int newQuantity = consoleDao.getConsole(consoleVM.getConsoleId()).getQuantity();

        assertEquals(20, newQuantity);

        invoiceService.amendQuantityInDB("Consoles", consoleVM.getConsoleId(), 5, "subtract");

        newQuantity = consoleDao.getConsole(consoleVM.getConsoleId()).getQuantity();

        assertEquals(15, newQuantity);
        
    }

*/
    /*
    @Test
    public void updateInvoice() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP);
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

        invoiceVM.setProcessingFee(new BigDecimal(15.00).setScale(2, RoundingMode.HALF_UP);
        invoiceVM.setTotal(new BigDecimal(120.00).setScale(2, RoundingMode.HALF_UP);

        invoiceVM.setStreet("John's new street");
        invoiceVM.setZipCode("34567");

        invoiceService.updateInvoice(invoiceVM);

        InvoiceViewModel fromService  = invoiceService.findInvoice(invoiceVM.getInvoiceId());
        assertEquals("John's new street", fromService.getStreet());
        assertEquals("34567", fromService.getZipCode());

    }
*/
    /*

    //public void removeInvoice(int id) {
    @Test
    public void removeInvoice() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("model 1");
        consoleVM.setManufacturer("manufacturer 1");
        consoleVM.setMemoryAmount("lots of memory");
        consoleVM.setProcessor("best processor");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP);
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

     */

    // Helper methods

    public void setUpInvoiceDaoMock() {

        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        Invoice invoice = new Invoice();
        invoice.setName("Terry DoByne");
        invoice.setStreet("2380 W US Hwy 89");
        invoice.setCity("Sedona");
        invoice.setState("AZ");
        invoice.setZipCode("86336");
        invoice.setItemType("Consoles");
        invoice.setItemId(1);
        invoice.setQuantity(2);
        invoice.setUnitPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        invoice.setSubtotal(new BigDecimal(200.00).setScale(2, RoundingMode.HALF_UP));
        invoice.setTax(new BigDecimal(8.00).setScale(2, RoundingMode.HALF_UP));
        invoice.setProcessingFee(new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP));
        invoice.setTotal(new BigDecimal(222.99).setScale(2, RoundingMode.HALF_UP));

        Invoice invoice2 = new Invoice();
        invoice2.setInvoiceId(1);
        invoice2.setName("Terry DoByne");
        invoice2.setStreet("2380 W US Hwy 89");
        invoice2.setCity("Sedona");
        invoice2.setState("AZ");
        invoice2.setZipCode("86336");
        invoice2.setItemType("Consoles");
        invoice2.setItemId(1);
        invoice2.setQuantity(2);
        invoice2.setUnitPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        invoice2.setSubtotal(new BigDecimal(200.00).setScale(2, RoundingMode.HALF_UP));
        invoice2.setTax(new BigDecimal(8.00).setScale(2, RoundingMode.HALF_UP));
        invoice2.setProcessingFee(new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP));
        invoice2.setTotal(new BigDecimal(222.99).setScale(2, RoundingMode.HALF_UP));


        Invoice invoice3 = new Invoice();
        invoice3.setName("Mari Garcia");
        invoice3.setStreet("1000 Sturdivant Street");
        invoice3.setCity("Cary");
        invoice3.setState("NC");
        invoice3.setZipCode("27511");
        invoice3.setItemType("Games");
        invoice3.setItemId(1);
        invoice3.setQuantity(1);
        invoice3.setUnitPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        invoice3.setSubtotal(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        invoice3.setTax(new BigDecimal(3.00).setScale(2, RoundingMode.HALF_UP));
        invoice3.setProcessingFee(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));
        invoice3.setTotal(new BigDecimal(64.48).setScale(2, RoundingMode.HALF_UP));

        Invoice invoice4 = new Invoice();
        invoice4.setInvoiceId(2);
        invoice4.setName("Mari Garcia");
        invoice4.setStreet("1000 Sturdivant Street");
        invoice4.setCity("Cary");
        invoice4.setState("NC");
        invoice4.setZipCode("27511");
        invoice4.setItemType("Games");
        invoice4.setItemId(1);
        invoice4.setQuantity(1);
        invoice4.setUnitPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        invoice4.setSubtotal(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        invoice4.setTax(new BigDecimal(3.00).setScale(2, RoundingMode.HALF_UP));
        invoice4.setProcessingFee(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP));
        invoice4.setTotal(new BigDecimal(64.48).setScale(2, RoundingMode.HALF_UP));

        List<Invoice> invoicesList = new ArrayList<>();
        invoicesList.add(invoice2);
        invoicesList.add(invoice4);

        doReturn(invoice2).when(invoiceDao).addInvoice(invoice);
        doReturn(invoice4).when(invoiceDao).addInvoice(invoice3);

       //when(invoiceDao.addInvoice(refEq(invoice, "unitPrice", "subtotal", "tax", "processingFee","total"))).thenReturn(invoice2);


        //when(invoiceDao.addInvoice(refEq(invoice3, "unitPrice", "subtotal", "tax", "processingFee","total"))).thenReturn(invoice4);
        doReturn(invoice2).when(invoiceDao).getInvoice(1);
        doReturn(invoicesList).when(invoiceDao).getAllInvoices();

    }

    public void setUpConsoleDaoMock() {

        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4GB");
        console.setProcessor("NVIDIA");
        console.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        console.setQuantity(10);

        Console console2 = new Console();
        console2.setConsoleId(1);
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setMemoryAmount("4GB");
        console2.setProcessor("NVIDIA");
        console2.setPrice(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
        console2.setQuantity(10);

        doReturn(console2).when(consoleDao).addConsole(console);
        doReturn(console2).when(consoleDao).getConsole(1);

    }

    // Helper method

    public void setUpGameDaoMock() {

        gameDao = mock(GameDaoJdbcTemplateImpl.class);

        Game game = new Game();
        game.setTitle("The Legend of Zelda: Link's Awakening");
        game.setEsrbRating("E");
        game.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        game.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        game.setStudio("Nintendo");
        game.setQuantity(10);

        Game game2 = new Game();
        game2.setGameId(1);
        game2.setTitle("The Legend of Zelda: Link's Awakening");
        game2.setEsrbRating("E");
        game2.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        game2.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        game2.setStudio("Nintendo");
        game2.setQuantity(10);

        doReturn(game2).when(gameDao).addGame(game);
        doReturn(game2).when(gameDao).getGame(1);

    }

    public void setUpProcessingFeeDaoMock() {

        processingFeeDao = mock(ProcessingFeeDaoJdbcTemplateImpl.class);

        doReturn(new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP)).when(processingFeeDao).getProcessingFee("Consoles");
        doReturn(new BigDecimal(1.49).setScale(2, RoundingMode.HALF_UP)).when(processingFeeDao).getProcessingFee("Games");
    }

    public void setUpSalesTaxRateDaoMock() {

        salesTaxRateDao = mock(SalesTaxRateDaoJdbcTemplateImpl.class);

        doReturn(new BigDecimal(0.04).setScale(2, RoundingMode.HALF_UP)).when(salesTaxRateDao).getSalesTaxRate("AZ");
        doReturn(new BigDecimal(0.05).setScale(2, RoundingMode.HALF_UP)).when(salesTaxRateDao).getSalesTaxRate("NC");
    }

}

