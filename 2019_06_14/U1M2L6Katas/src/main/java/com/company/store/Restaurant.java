package com.company.store;

public class Restaurant {

    private float squareFootage;
    private String location;
    private String timeOpen;
    private String timeClose;
    private boolean isOpen;

    private SodaPop sodaPop;

    public Restaurant() { }

    public void clean() {
        System.out.println("All clean.");
    }

    public void toggleOpen() {
        if (isOpen) {
            this.isOpen = false;
        } else {
            this.isOpen = true;
        }
    }

    public float getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(float squareFootage) {
        this.squareFootage = squareFootage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTimeOpen() {
        return timeOpen;
    }

    public void setTimeOpen(String timeOpen) {
        this.timeOpen = timeOpen;
    }

    public String getTimeClose() {
        return timeClose;
    }

    public void setTimeClose(String timeClose) {
        this.timeClose = timeClose;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public SodaPop getSodaPop() {
        return sodaPop;
    }

    public void setSodaPop(SodaPop sodaPop) {
        this.sodaPop = sodaPop;
    }

}
