package com.company.store;

import com.company.store.*;

public class VendingMachine {

    private String id;
    private int capacity;
    private float availableChange;

    private Chips chips;
    private SodaPop sodaPop;
    private Chocolate chocolate;
    private Mints mints;
    private Gum gum;
    private Popcorn popcorn;

    public VendingMachine() { }

    public void restock() {
        System.out.println("adding items");
    }

    public void removeCash() {
        System.out.println("removing cash");
    }

    public void repair() {
        System.out.println("All fixed.");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public float getAvailableChange() {
        return availableChange;
    }

    public void setAvailableChange(float availableChange) {
        this.availableChange = availableChange;
    }

    public Chips getChips() {
        return chips;
    }

    public void setChips(Chips chips) {
        this.chips = chips;
    }

    public SodaPop getSodaPop() {
        return sodaPop;
    }

    public void setSodaPop(SodaPop sodaPop) {
        this.sodaPop = sodaPop;
    }

    public Chocolate getChocolate() {
        return chocolate;
    }

    public void setChocolate(Chocolate chocolate) {
        this.chocolate = chocolate;
    }

    public Mints getMints() {
        return mints;
    }

    public void setMints(Mints mints) {
        this.mints = mints;
    }

    public Gum getGum() {
        return gum;
    }

    public void setGum(Gum gum) {
        this.gum = gum;
    }

    public Popcorn getPopcorn() {
        return popcorn;
    }

    public void setPopcorn(Popcorn popcorn) {
        this.popcorn = popcorn;
    }

}
