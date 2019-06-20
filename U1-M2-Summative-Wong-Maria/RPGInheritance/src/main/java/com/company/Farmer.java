/**
 * This class represents a Farmer.
 * Subclass of Character
 */

package com.company;

public class Farmer extends Character {

    // constructor

    public Farmer(String name) {
        super(name, 75, 100, 75, 10, 1);
    }

    // methods

    // farming duty
    public void plow() {
        System.out.println("Plow! Plow! Plow! The soil won't dig itself up.\n");
    }

    // farming duty
    public void harvest() {
        System.out.println("Come on guys and gals. Let's gather these crops.\n");
    }

}
