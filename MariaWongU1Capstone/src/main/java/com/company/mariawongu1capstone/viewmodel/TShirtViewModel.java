package com.company.mariawongu1capstone.viewmodel;

import com.company.mariawongu1capstone.model.TShirt;

import java.util.Objects;

/**
 * This view is used for crud and other queries directly about t-shirts.
 */
public class TShirtViewModel extends TShirt {

    //override methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TShirtViewModel tShirt = (TShirtViewModel) o;
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
