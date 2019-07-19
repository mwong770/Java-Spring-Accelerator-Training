package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.Game;

import java.util.List;

public interface GameDao {

    // standard CRUD

    Game addGame(Game game);

    Game getGame(int id);

    List<Game> getAllGames();

    void updateGame(Game game);

    void deleteGame(int id);

    // additional methods

    List<Game> findGamesByStudio(String studio);

    List<Game> findGamesByEsrbRating(String esrbRating);

    List<Game> findGamesByTitle(String title);

}
