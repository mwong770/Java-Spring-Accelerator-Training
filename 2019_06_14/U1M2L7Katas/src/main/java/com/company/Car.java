package com.company;

import com.company.interfaces.Vehicle;

public class Car implements Vehicle {

    private String make;
    private String model;
    private int milesTraveled;

    public void drive(int miles) {
        System.out.println("Miles to drive: " + miles);
    }

    public void displayMilesTraveled() {
        System.out.println("Miles traveled: " + milesTraveled);
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getMilesTraveled() {
        return milesTraveled;
    }

    public void setMilesTraveled(int milesTraveled) {
        this.milesTraveled = milesTraveled;
    }

}
