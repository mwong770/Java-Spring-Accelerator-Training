package com.company.mariawongu1capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesTaxRate {

    private String state;
    private BigDecimal rate;

    // getters and setters


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (rate == null) {
            return new BigDecimal(0);
        }
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTaxRate that = (SalesTaxRate) o;
        return getState().equals(that.getState()) &&
                getRate().equals(that.getRate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getState(), getRate());
    }


}
