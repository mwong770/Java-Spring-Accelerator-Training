package com.company.laptop;

public class Memory {

    private int size;
    private int speed;

    public Memory() { }

    public void insertInComputer() {
        System.out.println("In computer.");
    }

    public void activate() {
        System.out.println("Now activated.");
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
