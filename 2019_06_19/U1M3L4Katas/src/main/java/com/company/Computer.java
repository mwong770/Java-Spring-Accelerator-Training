package com.company;

public class Computer {

    private String Brand;
    private String Model;
    private String CPU;
    private int RAM;
    private int StorageSize;

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getCPU() {
        return CPU;
    }

    public void setCPU(String CPU) {
        this.CPU = CPU;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int RAM) {
        this.RAM = RAM;
    }

    public int getStorageSize() {
        return StorageSize;
    }

    public void setStorageSize(int storageSize) {
        StorageSize = storageSize;
    }
}
