package com.company.mariawongu1capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProcessingFee {

    private String productType;
    private BigDecimal fee;

    // getters and setters

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getFee() {
        // helps avoid NullPointer Exceptions when doing calculations
        if (fee == null) {
            return new BigDecimal(0);
        }
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    // override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return getProductType().equals(that.getProductType()) &&
                Objects.equals(getFee(), that.getFee());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getFee());
    }

}
