package com.company.mariawongu1capstone.viewmodel;

import java.util.Objects;

/**
 * This view model is used to display info about the item
 * purchased on an invoice when a user purchases a tshirt
 */
public class TShirtInvoiceViewModel {

    // items to include in invoice

    private String size;

    private String color;

    private String description;

    // getters and setters

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

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtInvoiceViewModel that = (TShirtInvoiceViewModel) o;
        return Objects.equals(getSize(), that.getSize()) &&
                Objects.equals(getColor(), that.getColor()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSize(), getColor(), getDescription());
    }

}
