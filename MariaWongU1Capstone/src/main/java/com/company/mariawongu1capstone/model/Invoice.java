package com.company.mariawongu1capstone.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Objects;

public class Invoice {

    private int invoiceId;

    @Size(min = 1, max = 80, message = "The size of name must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a name.")
    private String name;

    @Size(min = 1, max = 30, message = "The size of street must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a street.")
    private String street;

    @Size(min = 1, max = 30, message = "The size of city must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a city.")
    private String city;

    @Size(min = 1, max = 30, message = "The size of state must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply a state.")
    private String state;

    @Size(min = 5, max = 5, message = "The size of zip code must {max} Characters.")
    @NotBlank(message = "Please supply a zip code.")
    private String zipCode;

    @Size(min = 1, max = 20, message = "The size of item type must be between {min} and {max} Characters.")
    @NotBlank(message = "Please supply an item type.")
    private String itemType;

    @Min(value = 1, message = "You must supply an item id value of at least {value}.")
    private int itemId;


    private BigDecimal unitPrice;

    @Min(value = 1, message = "You must select a quantity of at least {value}.")
    private int quantity;
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal processingFee;
    private BigDecimal total;

    // getters and setters

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (unitPrice == null) {
            return new BigDecimal(0);
        }
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (subtotal == null) {
            return new BigDecimal(0);
        }
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (tax == null) {
            return new BigDecimal(0);
        }
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (processingFee == null) {
            return new BigDecimal(0);
        }
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (total == null) {
            return new BigDecimal(0);
        }
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getInvoiceId() == invoice.getInvoiceId() &&
                getItemId() == invoice.getItemId() &&
                getQuantity() == invoice.getQuantity() &&
                getName().equals(invoice.getName()) &&
                getStreet().equals(invoice.getStreet()) &&
                getCity().equals(invoice.getCity()) &&
                getState().equals(invoice.getState()) &&
                getZipCode().equals(invoice.getZipCode()) &&
                getItemType().equals(invoice.getItemType()) &&
                getUnitPrice().equals(invoice.getUnitPrice()) &&
                getSubtotal().equals(invoice.getSubtotal()) &&
                getTax().equals(invoice.getTax()) &&
                getProcessingFee().equals(invoice.getProcessingFee()) &&
                getTotal().equals(invoice.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInvoiceId(), getName(), getStreet(), getCity(), getState(), getZipCode(), getItemType(), getItemId(), getUnitPrice(), getQuantity(), getSubtotal(), getTax(), getProcessingFee(), getTotal());
    }


}
