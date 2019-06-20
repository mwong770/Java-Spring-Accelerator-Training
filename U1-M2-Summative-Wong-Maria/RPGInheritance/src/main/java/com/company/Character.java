/**
 * This class represents a Character in an RPG game.
 * Superclass of Constable, Farmer, and Warrior
 */

package com.company;

public class Character {

    private String name;
    private int strength;
    private int health;
    private int stamina;
    private int speed;
    private int attackPower;

    // constructor

    public Character(String name, int strength, int health, int stamina, int speed, int attackPower) {
        this.name = name;
        this.strength = strength;
        this.health = health;
        this.stamina = stamina;
        this.speed = speed;
        this.attackPower = attackPower;
    }

    // methods

    public void run() {
        System.out.println("Running.... \n");
    }

    // decreases health of opponent
    public void attack(Character opponent) {
        System.out.println("Punch! Pow! Bang!");
        opponent.decreaseHealth(this.attackPower, this.name);
    }

    // returns character health to 100
    public void heal() {
        System.out.println("All better. I'm healed!");
        this.health = 100;
        System.out.println("New health: " + this.health + "\n");
    }

    // decreases health when attacked by opponent
    public void decreaseHealth(int opponentAttackPower, String name) {
        System.out.println("Oh no! Doctor!");
        System.out.println(name + " attacked " + this.name + " with " + opponentAttackPower + " power.");
        if (this.health >= opponentAttackPower) {
            this.health -= opponentAttackPower;
        } else {
            this.health = 0;
        }
        System.out.println(this.name + " health: " + this.health + "\n");
    }

    // increases stamina by 10
    public void increaseStamina() {
        System.out.println("Stamina is mine!");
        this.stamina += 10;
        System.out.println("New stamina: " + this.stamina + "\n");
    }

    // decreases stamina by 10
    public void decreaseStamina() {
        System.out.println("Nooo! Need more stamina.");
        if (this.stamina >= 10) {
            this.stamina -= 10;
        } else {
            this.stamina = 0;
        }
        System.out.println("New stamina: " + this.stamina + "\n");
    }

    public String getName() {
        return name;
    }

    public int getStrength() {
        return strength;
    }

    public int getHealth() {
        return health;
    }

    public int getStamina() {
        return stamina;
    }

    public int getSpeed() {
        return speed;
    }

    public int getAttackPower() {
        return attackPower;
    }

}
