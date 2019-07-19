package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.*;
import com.company.mariawongu1capstone.model.Game;
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
public class GameDaoJdbcTemplateImplTest {

    @Autowired
    ConsoleDao consoleDao;
    @Autowired
    GameDao gameDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    TShirtDao tShirtDao;

    // clear game, game, invoice, and tshirt tables in database
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

    // USE ANOTHER @BEFORE TO PLACE REPEATED GAME CREATES FROM THE LAST THREE METHODS

    // USER @AFTER TO CLEAR VARIABLES OUT

    @Test
    public void addGetDeleteGame() {

        Game game = new Game();
        game.setTitle("titoe 1");
        game.setEsrbRating("rating 1");
        game.setDescription("my first game");
        game.setPrice(new BigDecimal(100.00).setScale(2));
        game.setStudio("studio 1");
        game.setQuantity(10);

        game = gameDao.addGame(game);

        Game game1 = gameDao.getGame(game.getGameId());

        assertEquals(game, game1);

        gameDao.deleteGame(game.getGameId());
        game1 = gameDao.getGame(game.getGameId());
        assertNull(game1);
    }

    @Test
    public void updateGame() {
        Game game = new Game();
        game.setTitle("titoe 1");
        game.setEsrbRating("rating 1");
        game.setDescription("my first game");
        game.setPrice(new BigDecimal(100.00).setScale(2));
        game.setStudio("studio 1");
        game.setQuantity(10);

        game = gameDao.addGame(game);

        game.setPrice(new BigDecimal(50.00).setScale(2));
        game.setStudio("studio 3");
        game.setQuantity(100);
        gameDao.updateGame(game);

        Game game1  = gameDao.getGame(game.getGameId());
        assertEquals(game1,game);
    }

    @Test
    public void getAllGames() {
        Game game = new Game();
        game.setTitle("title 1");
        game.setEsrbRating("rating 1");
        game.setDescription("my first game");
        game.setPrice(new BigDecimal(100.00).setScale(2));
        game.setStudio("studio 1");
        game.setQuantity(10);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("title 2");
        game.setEsrbRating("rating 2");
        game.setDescription("my second game");
        game.setPrice(new BigDecimal(30.00).setScale(2));
        game.setStudio("studio 2");
        game.setQuantity(40);

        game = gameDao.addGame(game);

        List<Game> gList = gameDao.getAllGames();
        assertEquals(2, gList.size());
    }

    @Test
    public void findGamesByStudio()
    {
        Game game = new Game();
        game.setTitle("title 1");
        game.setEsrbRating("rating 1");
        game.setDescription("my first game");
        game.setPrice(new BigDecimal(100.00).setScale(2));
        game.setStudio("studio 1");
        game.setQuantity(10);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("title 2");
        game.setEsrbRating("rating 2");
        game.setDescription("my second game");
        game.setPrice(new BigDecimal(30.00).setScale(2));
        game.setStudio("studio 2");
        game.setQuantity(40);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("title 3");
        game.setEsrbRating("rating 3");
        game.setDescription("my third game");
        game.setPrice(new BigDecimal(50.00).setScale(2));
        game.setStudio("studio 2");
        game.setQuantity(50);

        game = gameDao.addGame(game);

        List<Game> gList = gameDao.findGamesByStudio("Studio 1");
        assertEquals(1, gList.size());

        gList = gameDao.findGamesByStudio("Studio 2");
        assertEquals(2, gList.size());

        gList = gameDao.findGamesByStudio("Unknown Studio");
        assertEquals(0, gList.size());

    }

    @Test
    public void findGamesByEsrbRating()
    {
        Game game = new Game();
        game.setTitle("title 1");
        game.setEsrbRating("rating 1");
        game.setDescription("my first game");
        game.setPrice(new BigDecimal(100.00).setScale(2));
        game.setStudio("studio 1");
        game.setQuantity(10);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("title 2");
        game.setEsrbRating("rating 1");
        game.setDescription("my second game");
        game.setPrice(new BigDecimal(30.00).setScale(2));
        game.setStudio("studio 2");
        game.setQuantity(40);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("title 3");
        game.setEsrbRating("rating 2");
        game.setDescription("my third game");
        game.setPrice(new BigDecimal(50.00).setScale(2));
        game.setStudio("studio 2");
        game.setQuantity(50);

        game = gameDao.addGame(game);

        List<Game> gList = gameDao.findGamesByEsrbRating("rating 1");
        assertEquals(2, gList.size());

        gList = gameDao.findGamesByEsrbRating("rating 2");
        assertEquals(1, gList.size());

        gList = gameDao.findGamesByEsrbRating("Unknown rating");
        assertEquals(0, gList.size());

    }

    @Test
    public void findGamesByTitle()
    {
        Game game = new Game();
        game.setTitle("title 1");
        game.setEsrbRating("rating 1");
        game.setDescription("my first game");
        game.setPrice(new BigDecimal(100.00).setScale(2));
        game.setStudio("studio 1");
        game.setQuantity(10);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("title 2");
        game.setEsrbRating("rating 1");
        game.setDescription("my second game");
        game.setPrice(new BigDecimal(30.00).setScale(2));
        game.setStudio("studio 2");
        game.setQuantity(40);

        game = gameDao.addGame(game);

        game = new Game();
        game.setTitle("title 3");
        game.setEsrbRating("rating 2");
        game.setDescription("my third game");
        game.setPrice(new BigDecimal(50.00).setScale(2));
        game.setStudio("studio 2");
        game.setQuantity(50);

        game = gameDao.addGame(game);

        List<Game> gList = gameDao.findGamesByTitle("title 1");
        assertEquals(1, gList.size());

        gList = gameDao.findGamesByTitle("title 4");
        assertEquals(0, gList.size());

    }

}
