package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Game;
import com.company.mariawongu1capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GameService {

    ConsoleDao consoleDao;
    GameDao gameDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    TShirtDao tShirtDao;

    @Autowired
    public GameService(ConsoleDao consoleDao, GameDao gameDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao, SalesTaxRateDao salesTaxRateDao, TShirtDao tShirtDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.tShirtDao = tShirtDao;
    }


    //
    // Game API
    //

    public GameViewModel saveGame(GameViewModel gameViewModel) {
        Game game = new Game();

        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());
        game =  gameDao.addGame(game);

        gameViewModel.setGameId(game.getGameId());
        return gameViewModel;
    }

    public GameViewModel findGameById(int id) {
        Game game = gameDao.getGame(id);
        if(game == null)
            return null;
        else
            return buildGameViewModel(game);
    }

    public List<GameViewModel> findAllGames() {

        List<Game> games = gameDao.getAllGames();

        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game g: games) {
            gameViewModels.add(buildGameViewModel(g));
        }
        return gameViewModels;

    }

    public void updateGame(GameViewModel gameViewModel) {
        Game game = new Game();
        game.setGameId(gameViewModel.getGameId());
        game.setTitle(gameViewModel.getTitle());
        game.setEsrbRating(gameViewModel.getEsrbRating());
        game.setDescription(gameViewModel.getDescription());
        game.setPrice(gameViewModel.getPrice());
        game.setStudio(gameViewModel.getStudio());
        game.setQuantity(gameViewModel.getQuantity());

        gameDao.updateGame(game);
    }

    public void removeGame(int id) {
        gameDao.deleteGame(id);
    }

    public List<GameViewModel> findGamesByStudio(String studio) {

        List<Game> games = gameDao.findGamesByStudio(studio);

        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game g: games) {
            gameViewModels.add(buildGameViewModel(g));
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGamesByEsrbRating(String esrbRating) {

        List<Game> games = gameDao.findGamesByEsrbRating(esrbRating);

        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game g: games) {
            gameViewModels.add(buildGameViewModel(g));
        }
        return gameViewModels;
    }

    public List<GameViewModel> findGamesByTitle(String title) {

        List<Game> games = gameDao.findGamesByTitle(title);

        List<GameViewModel> gameViewModels = new ArrayList<>();

        for (Game g: games) {
            gameViewModels.add(buildGameViewModel(g));
        }
        return gameViewModels;
    }



    // Build View Model

    private GameViewModel buildGameViewModel(Game game) {
        GameViewModel gameViewModel = new GameViewModel();
        gameViewModel.setGameId(game.getGameId());
        gameViewModel.setTitle(game.getTitle());
        gameViewModel.setEsrbRating(game.getEsrbRating());
        gameViewModel.setDescription(game.getDescription());
        gameViewModel.setPrice(game.getPrice());
        gameViewModel.setStudio(game.getStudio());
        gameViewModel.setQuantity(game.getQuantity());

        return gameViewModel;
    }
}
