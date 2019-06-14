package com.company.laptop;

public class Laptop {

    private String operatingSystem;
    private float speed;
    private boolean isOn;

    private Processor processor;
    private Memory memory;
    private OpticalDrive opticalDrive;

    public Laptop() { }

    public void updateApps() {
        System.out.println("All updated.");
    }

    public void toggleOn() {
        if (isOn) {
            this.isOn = false;
        } else {
            this.isOn = true;
        }
    }

    public void repair() {
        System.out.println("All fixed.");
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public OpticalDrive getOpticalDrive() {
        return opticalDrive;
    }

    public void setOpticalDrive(OpticalDrive opticalDrive) {
        this.opticalDrive = opticalDrive;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public Processor getProcessor() {
        return processor;
    }

    public void setProcessor(Processor processor) {
        this.processor = processor;
    }

    public Memory getMemory() {
        return memory;
    }

    public void setMemory(Memory memory) {
        this.memory = memory;
    }

}
