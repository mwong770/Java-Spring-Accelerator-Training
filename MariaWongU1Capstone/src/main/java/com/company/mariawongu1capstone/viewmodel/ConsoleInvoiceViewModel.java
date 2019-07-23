package com.company.mariawongu1capstone.viewmodel;

import java.util.Objects;

/**
 * This view model is used to display info about the item
 * purchased on an invoice when a user purchases a console
 */
public class ConsoleInvoiceViewModel {

    // items to include in invoice

    private String model;

    private String manufacturer;

    private String memoryAmount;

    private String processor;

    // getters and setters

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMemoryAmount() {
        return memoryAmount;
    }

    public void setMemoryAmount(String memoryAmount) {
        this.memoryAmount = memoryAmount;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConsoleInvoiceViewModel that = (ConsoleInvoiceViewModel) o;
        return Objects.equals(getModel(), that.getModel()) &&
                Objects.equals(getManufacturer(), that.getManufacturer()) &&
                Objects.equals(getMemoryAmount(), that.getMemoryAmount()) &&
                Objects.equals(getProcessor(), that.getProcessor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModel(), getManufacturer(), getMemoryAmount(), getProcessor());
    }

}
