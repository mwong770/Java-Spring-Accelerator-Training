package com.company.home;

public class Bathroom {

    private float squareFootage;
    private String bathType;

    public Bathroom() { }

    public void remodel() {
        System.out.println("Much nicer.");
    }

    public void clean() {
        System.out.println("All better.");
    }

    public float getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(float squareFootage) {
        this.squareFootage = squareFootage;
    }

    public String getBathType() {
        return bathType;
    }

    public void setBathType(String bathType) {
        this.bathType = bathType;
    }

}
