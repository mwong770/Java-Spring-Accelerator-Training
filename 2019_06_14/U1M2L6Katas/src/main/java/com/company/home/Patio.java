package com.company.home;

public class Patio {

    private float squareFootage;
    private String roofType;
    private boolean isEnclosed;

    public Patio() { }

    public void decorate() {
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

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public boolean isEnclosed() {
        return isEnclosed;
    }

    public void setEnclosed(boolean enclosed) {
        isEnclosed = enclosed;
    }

}
