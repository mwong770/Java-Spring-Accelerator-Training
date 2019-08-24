/**
 * This class represents a Warrior.
 * Subclass of Character
 */

package com.company;

public class Warrior extends Character {

    private int shieldStrength;

    // constructor

    public Warrior(String name) {
        super(name, 75, 100, 100, 50, 10);
        this.shieldStrength = 100;
    }

    // methods

    // decreases shield strength by 15 points
    public void decreaseShieldStrength() {
        System.out.println("You shall pay for what you did to my shield!");
        this.shieldStrength -= 15;
        System.out.println("New shield strength: " + this.shieldStrength + "\n");
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

}
