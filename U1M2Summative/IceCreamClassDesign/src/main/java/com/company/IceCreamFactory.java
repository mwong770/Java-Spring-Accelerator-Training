/**
 * This class represents an Ice Cream Factory.
 * It has the ability to get and set characteristics of an Ice Cream Factory.
 */

package com.company;

public class IceCreamFactory {

    // general factory
    private String factoryName;
    private String factoryLocation;

    // inventory
    private float poundsButter;
    private float gallonsMilk;
    private float poundsSugar;
    private float gallonsCream;
    private float poundsNuts;
    private float poundsCherries;
    private float poundsStrawberries;
    private float poundsChocolate;
    private float gallonsVanillaFlavoring;

    // equipment
    private int numFreezers;
    private int numHomogenizers;
    private int numCoolingTowers;
    private int numChillerVats;

    // constructor
    public IceCreamFactory() {
    }

    // make ice cream

    public void pasteurizeMilk() {
        System.out.println("Pasteurizing Milk.");
    }

    public void homogenize() {
        System.out.println("Homogenizing");
    }

    public void ageMix() {
        System.out.println("Aging mix.");
    }

    public void blendIngredients() {
        System.out.println("Blending ingredients.");
    }

    public void addLiquidFlavors() {
        System.out.println("Adding Liquid Flavor.");
    }

    public void freeze() {
        System.out.println("Freezing mix.");
    }

    public void addFruits() {
        System.out.println("Adding Fruits.");
    }

    public void addNuts() {
        System.out.println("Adding Nuts.");
    }

    public void harden() {
        System.out.println("Hardening.");
    }

    // package ice cream

    public void packageIceCream() {
        System.out.println("Packaging ice Cream.");
    }

    // deliver ice cream

    public void deliverToStore() {
        System.out.println("Delivering order to store.");
    }

    public void ship() {
        System.out.println("Shipping orders.");
    }

    // getters and setters

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getFactoryLocation() {
        return factoryLocation;
    }

    public void setFactoryLocation(String factoryLocation) {
        this.factoryLocation = factoryLocation;
    }

    public float getPoundsButter() {
        return poundsButter;
    }

    public void setPoundsButter(float poundsButter) {
        this.poundsButter = poundsButter;
    }

    public float getGallonsMilk() {
        return gallonsMilk;
    }

    public void setGallonsMilk(float gallonsMilk) {
        this.gallonsMilk = gallonsMilk;
    }

    public float getPoundsSugar() {
        return poundsSugar;
    }

    public void setPoundsSugar(float poundsSugar) {
        this.poundsSugar = poundsSugar;
    }

    public float getGallonsCream() {
        return gallonsCream;
    }

    public void setGallonsCream(float gallonsCream) {
        this.gallonsCream = gallonsCream;
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

    public float getPoundsStrawberries() {
        return poundsStrawberries;
    }

    public void setPoundsStrawberries(float poundsStrawberries) {
        this.poundsStrawberries = poundsStrawberries;
    }

    public float getPoundsChocolate() {
        return poundsChocolate;
    }

    public void setPoundsChocolate(float poundsChocolate) {
        this.poundsChocolate = poundsChocolate;
    }

    public float getGallonsVanillaFlavoring() {
        return gallonsVanillaFlavoring;
    }

    public void setGallonsVanillaFlavoring(float gallonsVanillaFlavoring) {
        this.gallonsVanillaFlavoring = gallonsVanillaFlavoring;
    }

    public int getNumFreezers() {
        return numFreezers;
    }

    public void setNumFreezers(int numFreezers) {
        this.numFreezers = numFreezers;
    }

    public int getNumHomogenisers() {
        return numHomogenizers;
    }

    public void setNumHomogenisers(int numHomogenizers) {
        this.numHomogenizers = numHomogenizers;
    }

    public int getNumCoolingTowers() {
        return numCoolingTowers;
    }

    public void setNumCoolingTowers(int numCoolingTowers) {
        this.numCoolingTowers = numCoolingTowers;
    }

    public int getNumChillerVats() {
        return numChillerVats;
    }

    public void setNumChillerVats(int numChillerVats) {
        this.numChillerVats = numChillerVats;
    }

}
