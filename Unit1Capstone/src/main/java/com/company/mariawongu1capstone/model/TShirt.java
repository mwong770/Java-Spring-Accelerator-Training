package com.company.mariawongu1capstone.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class TShirt {

    private int tShirtId;

    @Size(min = 1, max = 20, message = "The size of size must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for size.")
    private String size;

    @Size(min = 1, max = 20, message = "The size of color must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for color.")
    private String color;

    @Size(min = 1, max = 255, message = "The size of description must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for description.")
    private String description;

    @DecimalMin(value = "0.0", inclusive = true, message = "The min value you can enter for price is {value}.")
    @DecimalMax(value = "999.99", inclusive = true, message = "The max value you can enter for price is {value}")
    private BigDecimal price;

    @Min(value = 0, message = "You must select a quantity of at least {value}.")
    private int quantity;

    // getters and setters

    public int gettShirtId() {
        return tShirtId;
    }

    public void settShirtId(int tShirtId) {
        this.tShirtId = tShirtId;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        // avoid NullPointer Exceptions when doing calculations
        if (price == null) {
            return new BigDecimal(0);
        }
        return price;
    }

    public void setPrice(BigDecimal price) {

        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    //override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirt tShirt = (TShirt) o;
        return gettShirtId() == tShirt.gettShirtId() &&
                getQuantity() == tShirt.getQuantity() &&
                getSize().equals(tShirt.getSize()) &&
                getColor().equals(tShirt.getColor()) &&
                getDescription().equals(tShirt.getDescription()) &&
                getPrice().equals(tShirt.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(gettShirtId(), getSize(), getColor(), getDescription(), getPrice(), getQuantity());
    }

}
