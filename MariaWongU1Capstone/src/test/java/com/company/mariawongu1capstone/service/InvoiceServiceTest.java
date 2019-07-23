package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.viewmodel.*;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

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

    // tests saveInvoice and findInvoice()
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
        invoiceVM = invoiceService.saveInvoice(invoiceVM);

        InvoiceViewModel fromService = invoiceService.findInvoice(invoiceVM.getInvoiceId());

        assertEquals(invoiceVM, fromService);

    }

    // tests findAllInvoices()
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

        gameService.saveGame(gameVM);

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

    // tests getItemDetails()
    @Test
    public void getItemDetails() {

        // creates console to add id to invoice
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

        // this should be returned from getItemDetails
        ConsoleInvoiceViewModel consoleInvoiceView = new ConsoleInvoiceViewModel();
        consoleInvoiceView.setManufacturer(console.getManufacturer());
        consoleInvoiceView.setModel(console.getModel());
        consoleInvoiceView.setMemoryAmount(console.getMemoryAmount());
        consoleInvoiceView.setProcessor(console.getProcessor());

        assertEquals(consoleInvoiceView.toString(), invoiceVM.getItem().toString());
        assertEquals(new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP), invoiceVM.getUnitPrice());

        // Checks if works for a different item type

        // creates game to add id to invoice
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

        // this should be returned from getItemDetails
        GameInvoiceViewModel gameInvoiceView = new GameInvoiceViewModel();
        gameInvoiceView.setTitle(game.getTitle());
        gameInvoiceView.setEsrbRating(game.getEsrbRating());
        gameInvoiceView.setDescription(game.getDescription());
        gameInvoiceView.setStudio(game.getStudio());

        assertEquals(gameInvoiceView.toString(), invoiceVMWithGame.getItem().toString());
        assertEquals(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP), invoiceVMWithGame.getUnitPrice());
    }

    // tests calculateTotal()
    @Test
    public void calculateTotal() {

        // tests quantity below 10

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

        //subtotal (unitPrice * quantity) (100 * 2)
        assertEquals(new BigDecimal(200.00).setScale(2, RoundingMode.HALF_UP), invoiceVM.getSubtotal());
        //tax (subtotal * taxRate AZ) (200 * 0.04 = 8)
        assertEquals(new BigDecimal(8.00).setScale(2, RoundingMode.HALF_UP), invoiceVM.getTax());
        //processing fee for Consoles (0 additional fee)
        assertEquals(new BigDecimal(14.99).setScale(2, RoundingMode.HALF_UP), invoiceVM.getProcessingFee());
        //total (subtotal + tax + processing fee)
        assertEquals(new BigDecimal(222.99).setScale(2, RoundingMode.HALF_UP), invoiceVM.getTotal());

        InvoiceViewModel invoiceVMWithAddedFee = new InvoiceViewModel();

        // tests quantity above 10

        Game game = new Game();
        game.setTitle("The Legend of Zelda: Link's Awakening");
        game.setEsrbRating("E");
        game.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        game.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        game.setStudio("Nintendo");
        game.setQuantity(10);

        System.out.println("id1: " + game.toString());

        game = gameDao.addGame(game);

        System.out.println("id2: " + game.toString());

        invoiceVMWithAddedFee.setName("Terry DoByne");
        invoiceVMWithAddedFee.setStreet("2380 W US Hwy 89");
        invoiceVMWithAddedFee.setCity("Sedona");
        invoiceVMWithAddedFee.setState("AZ");
        invoiceVMWithAddedFee.setZipCode("86336");
        invoiceVMWithAddedFee.setItemType("Games");
        invoiceVMWithAddedFee.setItemId(game.getGameId());
        invoiceVMWithAddedFee.setQuantity(11);

        invoiceVMWithAddedFee = invoiceService.calculateTotal(invoiceVMWithAddedFee);

        //subtotal (unitPrice * quantity) (59.99 * 11)
        assertEquals(new BigDecimal(659.89).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getSubtotal());
        //tax (subtotal * taxRate AZ) (659.89 * 0.04 = 8)
        assertEquals(new BigDecimal(26.40).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getTax());
        //processing fee for Games (1.49 + 15.49 additional fee = )
        assertEquals(new BigDecimal(16.98).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getProcessingFee().setScale(2, RoundingMode.HALF_UP));
        //total (subtotal + tax + processing fee)
        assertEquals(new BigDecimal(702.87).setScale(2, RoundingMode.HALF_UP), invoiceVMWithAddedFee.getTotal().setScale(2, RoundingMode.HALF_UP));

    }

    // Create mocks

    public void setUpInvoiceDaoMock() {

        invoiceDao = mock(InvoiceDaoJdbcTemplateImpl.class);

        // adds calculations to these mock objects because InvoiceService.saveInvoice()
        // does calculations before calling invoiceDao.addInvoice()
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

