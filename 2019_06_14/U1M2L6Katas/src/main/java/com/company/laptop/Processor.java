package com.company.laptop;

public class Processor {

    private String model;
    private float clockSpeed;
    private int coresNum;

    public Processor() { }

    public void plugIntoMotherboard() {
        System.out.println("In motherboard.");
    }

    public void remove() {
        System.out.println("Removing.");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getClockSpeed() {
        return clockSpeed;
    }

    public void setClockSpeed(float clockSpeed) {
        this.clockSpeed = clockSpeed;
    }

    public int getNumCores() {
        return coresNum;
    }

    public void setNumCores(int coresNum) {
        this.coresNum = coresNum;
    }

}
