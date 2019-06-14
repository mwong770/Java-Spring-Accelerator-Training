package com.company.home;

public class Kitchen {

    private int cabinetsNum;
    private String refrigeratorType;
    private String stoveType;
    private float squareFootage;

    public Kitchen() { }

    public void remodel() {
        System.out.println("Much nicer.");
    }

    public void clean() {
        System.out.println("All better.");
    }

    public int getCabinetsNum() {
        return cabinetsNum;
    }

    public void setCabinetsNum(int cabinetsNum) {
        this.cabinetsNum = cabinetsNum;
    }

    public String getRefrigeratorType() {
        return refrigeratorType;
    }

    public void setRefrigeratorType(String refrigeratorType) {
        this.refrigeratorType = refrigeratorType;
    }

    public String getStoveType() {
        return stoveType;
    }

    public void setStoveType(String stoveType) {
        this.stoveType = stoveType;
    }

    public float getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(float squareFootage) {
        this.squareFootage = squareFootage;
    }

}
