package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;

import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.viewmodel.GameViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class GameServiceTest {

    GameService gameService;

    GameDao gameDao;

    @Before
    public void setUp() throws Exception {

        // Configures mock objects
        setUpGameDaoMock();

        // Passes mock objects
        gameService = new GameService(gameDao);

    }

    // tests saveGame() and findGameById()
    @Test
    public void saveFindGame() {

        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("The Legend of Zelda: Link's Awakening");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        gameVM.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(10);

        gameVM = gameService.saveGame(gameVM);

        GameViewModel fromService = gameService.findGameById(gameVM.getGameId());

        assertEquals(gameVM, fromService);

    }

    // tests findAllGames()
    @Test
    public void findAllGames() {

        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("The Legend of Zelda: Link's Awakening");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        gameVM.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(10);

        gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("Grand Theft Auto 5");
        gameVM.setEsrbRating("M");
        gameVM.setDescription("Experience Rockstar Games' critically acclaimed open world game, Grand Theft Auto V.");
        gameVM.setPrice(new BigDecimal(29.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("XBox One");
        gameVM.setQuantity(100);

        gameService.saveGame(gameVM);

        List<GameViewModel> fromService = gameService.findAllGames();

        assertEquals(2, fromService.size());

    }

    // tests findGamesByStudio()
    @Test
    public void findGamesByStudio() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("The Legend of Zelda: Link's Awakening");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        gameVM.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(10);

        gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("Grand Theft Auto 5");
        gameVM.setEsrbRating("M");
        gameVM.setDescription("Experience Rockstar Games' critically acclaimed open world game, Grand Theft Auto V.");
        gameVM.setPrice(new BigDecimal(29.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("XBox One");
        gameVM.setQuantity(100);

        gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("Mario Kart 8 Deluxe");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("Play anytime, anywhere! Race your friends or battle them.");
        gameVM.setPrice(new BigDecimal(49.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(100);

        gameService.saveGame(gameVM);
        List<GameViewModel> gList = gameService.findGamesByStudio("Nintendo");
        assertEquals(2, gList.size());
        assertEquals("Nintendo", gList.get(0).getStudio());

        gList = gameService.findGamesByStudio("XBox One");
        assertEquals(1, gList.size());
        assertEquals("XBox One", gList.get(0).getStudio());

        gList = gameService.findGamesByStudio("Sega");
        assertEquals(0, gList.size());

    }

    // tests findGamesByEsrbRating()
    @Test
    public void findGamesByEsrbRating() {
        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("The Legend of Zelda: Link's Awakening");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("As Link, explore a reimagined Koholint Island and collect instruments to awaken the Wind Fish to find a way home.");
        gameVM.setPrice(new BigDecimal(59.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(10);

        gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("Grand Theft Auto 5");
        gameVM.setEsrbRating("M");
        gameVM.setDescription("Experience Rockstar Games' critically acclaimed open world game, Grand Theft Auto V.");
        gameVM.setPrice(new BigDecimal(29.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("XBox One");
        gameVM.setQuantity(100);

        gameService.saveGame(gameVM);

        gameVM = new GameViewModel();
        gameVM.setTitle("Mario Kart 8 Deluxe");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("Play anytime, anywhere! Race your friends or battle them.");
        gameVM.setPrice(new BigDecimal(49.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(100);

        gameService.saveGame(gameVM);

        List<GameViewModel> gList = gameService.findGamesByEsrbRating("E");
        assertEquals(2, gList.size());
        assertEquals("E", gList.get(0).getEsrbRating());

        gList = gameService.findGamesByEsrbRating("M");
        assertEquals(1, gList.size());
        assertEquals("M", gList.get(0).getEsrbRating());

        gList = gameService.findGamesByEsrbRating("T");
        assertEquals(0, gList.size());

    }

    // tests findGamesByTitle()
    @Test
    public void findGamesByTitle() {

        GameViewModel gameVM = new GameViewModel();
        gameVM.setTitle("Mario Kart 8 Deluxe");
        gameVM.setEsrbRating("E");
        gameVM.setDescription("Play anytime, anywhere! Race your friends or battle them.");
        gameVM.setPrice(new BigDecimal(49.99).setScale(2, RoundingMode.HALF_UP));
        gameVM.setStudio("Nintendo");
        gameVM.setQuantity(100);

        gameService.saveGame(gameVM);

        List<GameViewModel> gList = gameService.findGamesByTitle("Mario Kart 8 Deluxe");
        assertEquals(1, gList.size());
        assertEquals("Mario Kart 8 Deluxe", gList.get(0).getTitle());

        gList = gameService.findGamesByTitle("Marvel’s Spider-Man");
        assertEquals(0, gList.size());

    }

    // Create mocks

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

        Game game3 = new Game();
        game3.setTitle("Grand Theft Auto 5");
        game3.setEsrbRating("M");
        game3.setDescription("Experience Rockstar Games' critically acclaimed open world game, Grand Theft Auto V.");
        game3.setPrice(new BigDecimal(29.99).setScale(2, RoundingMode.HALF_UP));
        game3.setStudio("XBox One");
        game3.setQuantity(100);

        Game game4 = new Game();
        game4.setGameId(2);
        game4.setTitle("Grand Theft Auto 5");
        game4.setEsrbRating("M");
        game4.setDescription("Experience Rockstar Games' critically acclaimed open world game, Grand Theft Auto V.");
        game4.setPrice(new BigDecimal(29.99).setScale(2, RoundingMode.HALF_UP));
        game4.setStudio("XBox One");
        game4.setQuantity(100);

        Game game5 = new Game();
        game5.setTitle("Mario Kart 8 Deluxe");
        game5.setEsrbRating("E");
        game5.setDescription("Play anytime, anywhere! Race your friends or battle them.");
        game5.setPrice(new BigDecimal(49.99).setScale(2, RoundingMode.HALF_UP));
        game5.setStudio("Nintendo");
        game5.setQuantity(100);

        Game game6 = new Game();
        game6.setGameId(3);
        game6.setTitle("Mario Kart 8 Deluxe");
        game6.setEsrbRating("E");
        game6.setDescription("Play anytime, anywhere! Race your friends or battle them.");
        game6.setPrice(new BigDecimal(49.99).setScale(2, RoundingMode.HALF_UP));
        game6.setStudio("Nintendo");
        game6.setQuantity(100);

        List<Game> gamesList = new ArrayList<>();
        gamesList.add(game2);
        gamesList.add(game4);

        List<Game> nintendoList = new ArrayList<>();
        nintendoList.add(game2);
        nintendoList.add(game6);

        List<Game> xBoxList = new ArrayList<>();
        xBoxList.add(game4);

        List<Game> eRatingList = new ArrayList<>();
        eRatingList.add(game2);
        eRatingList.add(game6);

        List<Game> mRatingList = new ArrayList<>();
        mRatingList.add(game4);

        List<Game> marioList = new ArrayList<>();
        marioList.add(game6);

        List<Game> emptyList = new ArrayList<>();

        doReturn(game2).when(gameDao).addGame(game);
        doReturn(game4).when(gameDao).addGame(game3);
        doReturn(game6).when(gameDao).addGame(game5);
        doReturn(game2).when(gameDao).getGame(1);
        doReturn(gamesList).when(gameDao).getAllGames();


        doReturn(nintendoList).when(gameDao).findGamesByStudio("Nintendo");
        doReturn(xBoxList).when(gameDao).findGamesByStudio("XBox One");
        doReturn(emptyList).when(gameDao).findGamesByStudio("Sega");

        doReturn(eRatingList).when(gameDao).findGamesByEsrbRating("E");
        doReturn(mRatingList).when(gameDao).findGamesByEsrbRating("M");
        doReturn(emptyList).when(gameDao).findGamesByEsrbRating("T");

        doReturn(marioList).when(gameDao).findGamesByTitle("Mario Kart 8 Deluxe");
        doReturn(emptyList).when(gameDao).findGamesByTitle("Sony");

    }

}
