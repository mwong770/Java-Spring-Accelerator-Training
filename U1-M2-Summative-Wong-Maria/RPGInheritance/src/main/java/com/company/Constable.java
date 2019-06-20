/**
 *
 * This class represents a Constable.
 * Subclass of Character
 *
 */

package com.company;

public class Constable extends Character {

    private String jurisdiction;

    public Constable(String name, String jurisdiction) {
        super(name, 60, 100, 60, 20, 5);
        this.jurisdiction = jurisdiction;
    }

    public void arrest() {
        System.out.println("You are under arrest!\n");
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

}
