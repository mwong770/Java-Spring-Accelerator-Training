package com.company.mariawongu1capstone.model;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Console {

    private int consoleId;

    @Size(min = 1, max = 50, message = "The size of model must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for model.")
    private String model;

    @Size(min = 1, max = 50, message = "The size of manufacturer must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a value for manufacturer.")
    private String manufacturer;

    @Size(min = 1, max = 20, message = "The size of memory amount must be between {min} and {max} Characters.")
    private String memoryAmount;

    @Size(min = 1, max = 20, message = "The size of processor must be between {min} and {max} Characters.")
    private String processor;

    @DecimalMin(value = "0.0", inclusive = true, message = "The min value you can enter for price is {value}.")
    @DecimalMax(value = "999.99", inclusive = true, message = "The max value you can enter for price is {value}")
    private BigDecimal price;

    @Min(value = 0, message = "You must select a quantity of at least {value}.")
    private int quantity;

    // getters and setters

    public int getConsoleId() {
        return consoleId;
    }

    public void setConsoleId(int consoleId) {
        this.consoleId = consoleId;
    }

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
        Console console = (Console) o;
        return getConsoleId() == console.getConsoleId() &&
                getQuantity() == console.getQuantity() &&
                getModel().equals(console.getModel()) &&
                getManufacturer().equals(console.getManufacturer()) &&
                Objects.equals(getMemoryAmount(), console.getMemoryAmount()) &&
                Objects.equals(getProcessor(), console.getProcessor()) &&
                getPrice().equals(console.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConsoleId(), getModel(), getManufacturer(), getMemoryAmount(), getProcessor(), getPrice(), getQuantity());
    }

}
