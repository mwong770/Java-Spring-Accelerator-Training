package com.company.store;

public class Gum {

    private String brand;
    private int ounces;
    private float price;
    private int quantity;

    public Gum() { }

    public boolean isAvailable() {
        if (quantity > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getOunces() {
        return ounces;
    }

    public void setOunces(int ounces) {
        this.ounces = ounces;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
