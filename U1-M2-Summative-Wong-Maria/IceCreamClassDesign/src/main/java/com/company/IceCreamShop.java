/**
 * This class represents an Ice Cream Shop.
 * It has the ability to get and set characteristics of an Ice Cream Shop.
 */

package com.company;

public class IceCreamShop {

    // general store
    private String storeName;
    private String storeLocation;

    // inventory
    private int numWaffleCones;
    private int numCakeCones;
    private int numSpoons;
    private float poundsNapkins;
    private float gallonsVanillaIceCream;
    private float gallonsChocolateIceCream;
    private float gallonsStrawberryIceCream;
    private float poundsSprinkles;
    private float poundsNuts;
    private float poundsCherries;
    private float poundsWhippedCream;

    // money
    private float singleScoopSalesPrice;
    private float doubleScoopSalesPrice;
    private float perToppingSalesPrice;

    // constructor
    public IceCreamShop() {
    }

    // make ice cream

    public void putIceCreamOnCone() {
        System.out.println("Putting ice cream on cone.");
    }

    public void addNuts() {
        System.out.println("Adding nuts.");
    }

    public void addSprinkles() {
        System.out.println("Adding sprinkles.");
    }

    public void addCherries() {
        System.out.println("Adding cherries.");
    }

    public void addWhippedCream() {
        System.out.println("Adding whipped cream.");
    }

    // customer related

    public void takeCustomerOrder() {
        System.out.println("Taking customer's order.");
    }

    public void checkCustomerOut() {
        System.out.println("Checking out customer.");
    }

    public void takePayment() {
        System.out.println("Taking payment.");
    }

    public void printReceipt() {
        System.out.println("Printing receipt.");
    }

    // money related

    public void balanceCashRegister() {
        System.out.println("Balancing Cash Register.");
    }

    public void balanaceDaysSales() {
        System.out.println("Balancing Day's Sales.");
    }

    // setters and getters

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public int getNumWaffleCones() {
        return numWaffleCones;
    }

    public void setNumWaffleCones(int numWaffleCones) {
        this.numWaffleCones = numWaffleCones;
    }

    public int getNumCakeCones() {
        return numCakeCones;
    }

    public void setNumCakeCones(int numCakeCones) {
        this.numCakeCones = numCakeCones;
    }

    public int getNumSpoons() {
        return numSpoons;
    }

    public void setNumSpoons(int numSpoons) {
        this.numSpoons = numSpoons;
    }

    public float getPoundsNapkins() {
        return poundsNapkins;
    }

    public void setPoundsNapkins(float poundsNapkins) {
        this.poundsNapkins = poundsNapkins;
    }

    public float getGallonsVanillaIceCream() {
        return gallonsVanillaIceCream;
    }

    public void setGallonsVanillaIceCream(float gallonsVanillaIceCream) {
        this.gallonsVanillaIceCream = gallonsVanillaIceCream;
    }

    public float getGallonsChocolateIceCream() {
        return gallonsChocolateIceCream;
    }

    public void setGallonsChocolateIceCream(float gallonsChocolateIceCream) {
        this.gallonsChocolateIceCream = gallonsChocolateIceCream;
    }

    public float getGallonsStrawberryIceCream() {
        return gallonsStrawberryIceCream;
    }

    public void setGallonsStrawberryIceCream(float gallonsStrawberryIceCream) {
        this.gallonsStrawberryIceCream = gallonsStrawberryIceCream;
    }

    public float getPoundsSprinkles() {
        return poundsSprinkles;
    }

    public void setPoundsSprinkles(float poundsSprinkles) {
        this.poundsSprinkles = poundsSprinkles;
    }

    public float getPoundsNuts() {
        return poundsNuts;
    }

    public void setPoundsNuts(float poundsNuts) {
        this.poundsNuts = poundsNuts;
    }

    public float getPoundsCherries() {
        return poundsCherries;
    }

    public void setPoundsCherries(float poundsCherries) {
        this.poundsCherries = poundsCherries;
    }

    public float getPoundsWhippedCream() {
        return poundsWhippedCream;
    }

    public void setPoundsWhippedCream(float poundsWhippedCream) {
        this.poundsWhippedCream = poundsWhippedCream;
    }

    public float getSingleScoopSalesPrice() {
        return singleScoopSalesPrice;
    }

    public void setSingleScoopSalesPrice(float singleScoopSalesPrice) {
        this.singleScoopSalesPrice = singleScoopSalesPrice;
    }

    public float getDoubleScoopSalesPrice() {
        return doubleScoopSalesPrice;
    }

    public void setDoubleScoopSalesPrice(float doubleScoopSalesPrice) {
        this.doubleScoopSalesPrice = doubleScoopSalesPrice;
    }

    public float getPerToppingSalesPrice() {
        return perToppingSalesPrice;
    }

    public void setPerToppingSalesPrice(float perToppingSalesPrice) {
        this.perToppingSalesPrice = perToppingSalesPrice;
    }

}
