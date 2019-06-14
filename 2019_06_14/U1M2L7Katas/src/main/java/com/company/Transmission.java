package com.company;

public class Transmission {

    private boolean isAutomatic;
    private String carFits;
    private String location;
    private String warranty;

    public boolean isAutomatic() {
        return isAutomatic;
    }

    public void setAutomatic(boolean automatic) {
        isAutomatic = automatic;
    }

    public String getCarFits() {
        return carFits;
    }

    public void setCarFits(String carFits) {
        this.carFits = carFits;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

}
