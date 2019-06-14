package com.company.laptop;

public class OpticalDrive {

    private int disksize;
    private int speed;

    public OpticalDrive() { }

    public void play() {
        System.out.println("Playing.");
    }

    public void eject() {
        System.out.println("Ejecting.");
    }

    public int getDisksize() {
        return disksize;
    }

    public void setDisksize(int disksize) {
        this.disksize = disksize;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
