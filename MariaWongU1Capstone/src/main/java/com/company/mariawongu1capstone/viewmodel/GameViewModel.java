package com.company.mariawongu1capstone.viewmodel;

import com.company.mariawongu1capstone.model.Game;

import java.util.Objects;

/**
 * This view is used for crud and other queries directly about games.
 */
public class GameViewModel extends Game {

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameViewModel game = (GameViewModel) o;
        return getGameId() == game.getGameId() &&
                getQuantity() == game.getQuantity() &&
                getTitle().equals(game.getTitle()) &&
                getEsrbRating().equals(game.getEsrbRating()) &&
                getDescription().equals(game.getDescription()) &&
                getPrice().equals(game.getPrice()) &&
                getStudio().equals(game.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getTitle(), getEsrbRating(), getDescription(), getPrice(), getStudio(), getQuantity());
    }

}
