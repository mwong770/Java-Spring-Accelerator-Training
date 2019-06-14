package com.company.home;

public class Home {

    private int bedroomNum;
    private int bathroomNum;
    private String type;

    private Basement basement;
    private Bathroom bathroom;
    private Kitchen kitchen;
    private Patio patio;

    public Home() { }

    public void remodel() {
        System.out.println("Much better.");
    }

    public void sell() {
        System.out.println("Goodbye.");
    }

    public void purchase() {
        System.out.println("Hello");
    }

    public int getBedroomNum() {
        return bedroomNum;
    }

    public void setBedroomNum(int bedroomNum) {
        this.bedroomNum = bedroomNum;
    }

    public int getBathroomNum() {
        return bathroomNum;
    }

    public void setBathroomNum(int bathroomNum) {
        this.bathroomNum = bathroomNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Basement getBasement() {
        return basement;
    }

    public void setBasement(Basement basement) {
        this.basement = basement;
    }

    public Bathroom getBathroom() {
        return bathroom;
    }

    public void setBathroom(Bathroom bathroom) {
        this.bathroom = bathroom;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public Patio getPatio() {
        return patio;
    }

    public void setPatio(Patio patio) {
        this.patio = patio;
    }

}
