package com.company.home;

public class Basement {

    private String wallType;
    private float squareFootage;

    public Basement() { }

    public void dehumidify() {
        System.out.println("Dehumidifying.");
    }

    public void changeFlooring() {
        System.out.println("Changing floor.");
    }

    public String getWallType() {
        return wallType;
    }

    public void setWallType(String wallType) {
        this.wallType = wallType;
    }

    public float getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(float squareFootage) {
        this.squareFootage = squareFootage;
    }

}
