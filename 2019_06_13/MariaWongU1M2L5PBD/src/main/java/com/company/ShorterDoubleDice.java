/**
 * This is a dice game in which the values of two dice are randomly chosen
 * until they return doubles (the same number on both dice).
 * Displays the value of each dice and the sum of both dice.
 * @params args
 *
 */

package com.company;

import java.util.Random;

public class ShorterDoubleDice {

    public static void main(String[] args) {

        boolean rolledDoubles = false;
        Random randomGenerator = new Random();

        do {

            int randomDice1 = randomGenerator.nextInt(6) + 1;
            int randomDice2 = randomGenerator.nextInt(6) + 1;

            if (randomDice1 == randomDice2) {
                rolledDoubles = true;
            }

            System.out.println("\nHERE COMES THE DICE!");
            System.out.println();
            System.out.println("Roll #1: " + randomDice1);
            System.out.println("Roll #2: " + randomDice2);

            int total = randomDice1 + randomDice2;
            System.out.println("The total is " + total + "!");

        } while (!rolledDoubles);

    }

}
