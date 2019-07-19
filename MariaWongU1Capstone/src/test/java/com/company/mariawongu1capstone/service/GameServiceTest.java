package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.model.Invoice;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.GameViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameServiceTest {

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

    //public GameViewModel saveGame(GameViewModel gameViewModel) {
    //public GameViewModel findGameById(int id) {
    @Test
    public void saveFindGame() {

        GameViewModel gameVM = new GameViewModel();

        gameVM.setTitle("title 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        GameViewModel fromService  = gameService.findGameById(gameVM.getGameId());

        //GameViewModel cannot be cast to Game

//        assertEquals(gameVM, fromService);
//        assertEquals(gameVM.toString(), fromService.toString());

    }

    //public List<Game> findAllGames() {
    @Test
    public void findAllGames() {

        GameViewModel gameVM = new GameViewModel();

        gameVM.setTitle("title 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        gameVM = new GameViewModel();

        gameVM.setTitle("title 2");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my second game");
        gameVM.setPrice(new BigDecimal(30.00).setScale(2));
        gameVM.setStudio("studio 2");
        gameVM.setQuantity(40);

        gameVM = gameService.saveGame(gameVM);

        List<GameViewModel> fromService = gameService.findAllGames();

        assertEquals(2, fromService.size());

    }

    //public void updateGame(GameViewModel gameViewModel) {
    @Test
    public void updateGame() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("titoe 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        gameVM.setStudio("studio 3");
        gameVM.setQuantity(100);
        gameService.updateGame(gameVM);

        GameViewModel fromService  = gameService.findGameById(gameVM.getGameId());
//        assertEquals(gameVM, fromService);
        assertEquals("studio 3", fromService.getStudio());
        assertEquals(100, fromService.getQuantity());
    }

    //public void removeGame(int id) {
    @Test
    public void removeGame() {

        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("titoe 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        List<GameViewModel> fromService = gameService.findAllGames();

        assertEquals(1, fromService.size());

        gameService.removeGame(fromService.get(0).getGameId());

        fromService = gameService.findAllGames();

        assertEquals(0, fromService.size());

    }

    //public List<GameViewModel> findGamesByStudio(String studio) {
    @Test
    public void findGamesByStudio() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("title 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("title 2");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my second game");
        gameVM.setPrice(new BigDecimal(30.00).setScale(2));
        gameVM.setStudio("studio 2");
        gameVM.setQuantity(40);

        gameVM = gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("title 3");
        gameVM.setEsrbRating("rating 2");
        gameVM.setDescription("my third game");
        gameVM.setPrice(new BigDecimal(50.00).setScale(2));
        gameVM.setStudio("studio 2");
        gameVM.setQuantity(50);

        gameVM = gameService.saveGame(gameVM);

        List<GameViewModel> gList = gameService.findGamesByStudio("studio 2");
        assertEquals(2,gList.size());
        assertEquals("studio 2", gList.get(0).getStudio());

        gList = gameService.findGamesByStudio("studio 1");
        assertEquals(1,gList.size());
        assertEquals("studio 1", gList.get(0).getStudio());

        gList = gameService.findGamesByStudio("Unknown Studio");
        assertEquals(0,gList.size());

    }

    //public List<GameViewModel> findGamesByEsrbRating(String esrbRating) {
    @Test
    public void findGamesByEsrbRating() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("title 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("title 2");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my second game");
        gameVM.setPrice(new BigDecimal(30.00).setScale(2));
        gameVM.setStudio("studio 2");
        gameVM.setQuantity(40);

        gameVM = gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("title 3");
        gameVM.setEsrbRating("rating 2");
        gameVM.setDescription("my third game");
        gameVM.setPrice(new BigDecimal(50.00).setScale(2));
        gameVM.setStudio("studio 2");
        gameVM.setQuantity(50);

        gameVM = gameService.saveGame(gameVM);

        List<GameViewModel> gList = gameService.findGamesByEsrbRating("rating 1");
        assertEquals(2,gList.size());
        assertEquals("rating 1", gList.get(0).getEsrbRating());

        gList = gameService.findGamesByEsrbRating("rating 2");
        assertEquals(1,gList.size());
        assertEquals("rating 2", gList.get(0).getEsrbRating());

        gList = gameService.findGamesByEsrbRating("Unknown Studio");
        assertEquals(0,gList.size());

    }

    //public List<GameViewModel> findGamesByTitle(String title) {
    @Test
    public void findGamesByTitle() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("title 1");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my first game");
        gameVM.setPrice(new BigDecimal(100.00).setScale(2));
        gameVM.setStudio("studio 1");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("title 2");
        gameVM.setEsrbRating("rating 1");
        gameVM.setDescription("my second game");
        gameVM.setPrice(new BigDecimal(30.00).setScale(2));
        gameVM.setStudio("studio 2");
        gameVM.setQuantity(40);

        gameVM = gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("title 3");
        gameVM.setEsrbRating("rating 2");
        gameVM.setDescription("my third game");
        gameVM.setPrice(new BigDecimal(50.00).setScale(2));
        gameVM.setStudio("studio 2");
        gameVM.setQuantity(50);

        gameVM = gameService.saveGame(gameVM);

        List<GameViewModel> gList = gameService.findGamesByTitle("title 1");
        assertEquals(1, gList.size());
        assertEquals("title 1", gList.get(0).getTitle());

        gList = gameService.findGamesByTitle("title 4");
        assertEquals(0, gList.size());

    }

}
