package com.company.mariawongu1capstone.controller;

import com.company.mariawongu1capstone.exception.NotFoundException;
import com.company.mariawongu1capstone.service.GameService;
import com.company.mariawongu1capstone.viewmodel.GameViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class GameInventoryController {

    @Autowired
    GameService gameService;

    // handles requests to add a game
    @RequestMapping(value = "/games", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public GameViewModel createGame(@RequestBody @Valid GameViewModel game) {
        return gameService.saveGame(game);
    }

    // handles requests to retrieve a game by id
    @RequestMapping(value = "/games/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public GameViewModel getGame(@PathVariable("id") int itemId) {
        GameViewModel item = gameService.findGameById(itemId);
        if (item == null)
            throw new NotFoundException("Item could not be retrieved for id " + itemId);
        return item;
    }

    // handles requests to delete a game by id
    @RequestMapping(value = "/games/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGame(@PathVariable("id") int itemId) {
        gameService.removeGame(itemId);
    }

    // handles requests to update a game with a matching id
    @RequestMapping(value = "/games/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGame(@PathVariable("id") int itemId, @RequestBody @Valid GameViewModel itemViewModel) {
        if (itemViewModel.getGameId() == 0)
            itemViewModel.setGameId(itemId);
        if (itemId != itemViewModel.getGameId()) {
            throw new IllegalArgumentException("Item ID on path must match the ID in the Item object");
        }
        gameService.updateGame(itemViewModel);
    }

    // handles requests to retrieve a list of all games
    @RequestMapping(value = "/games", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> getAllGames() {
        return gameService.findAllGames();
    }

    // handles requests to retrieve a list of games with a matching studio
    @RequestMapping(value = "/games/studios/{studio}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> findGamesByStudio(@PathVariable String studio) {
        return gameService.findGamesByStudio(studio);
    }

    // handles requests to retrieve a list of games with a matching rating
    @RequestMapping(value = "/games/ratings/{esrbRating}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> findGamesByEsrbRating(@PathVariable String esrbRating) {
        return gameService.findGamesByEsrbRating(esrbRating);
    }

    // handles requests to retrieve a list of games with a matching title
    @RequestMapping(value = "/games/titles/{title}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<GameViewModel> findGamesByTitle(@PathVariable String title) {
        return gameService.findGamesByTitle(title);
    }

}

