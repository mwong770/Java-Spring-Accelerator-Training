package com.company.mariawongu1capstone.viewmodel;

import java.util.Objects;

/**
 * This view model is used to display info about the item
 * purchased on an invoice when a user purchases a game
 */
public class GameInvoiceViewModel {

    // items to include in invoice

    private String title;

    private String esrbRating;

    private String description;

    private String studio;

    // getters and setters

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

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameInvoiceViewModel that = (GameInvoiceViewModel) o;
        return Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getEsrbRating(), that.getEsrbRating()) &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getStudio(), that.getStudio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getEsrbRating(), getDescription(), getStudio());
    }

}
