package com.company.mariawongu1capstone.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Game {

    private int gameId;

    @Size(min = 1, max = 50, message = "The size of title must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for title.")
    private String title;

    @Size(min = 1, max = 50, message = "The size of ESRB rating must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for ESRB rating.")
    private String esrbRating;

    @Size(min = 1, max = 255, message = "The size of description must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for description.")
    private String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "The min value you can enter for price is {value}.")
    @DecimalMax(value = "999.99", inclusive = true, message = "The max value you can enter for price is {value}")
    private BigDecimal price;

    @Size(min = 1, max = 50, message = "The size of studio must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for studio.")
    private String studio;

    @Min(value = 0, message = "You must select a quantity of at least {value}.")
    private int quantity;

    // setters and getters

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (price == null) {
            return new BigDecimal(0);
        }
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
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
