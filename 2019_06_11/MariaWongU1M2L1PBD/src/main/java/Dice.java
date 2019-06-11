/**
 * This is a dice game in which the values of two dice are randomly chosen.
 * Displays the sum of both dice.
 * @params args
 *
 */

import java.util.Random;

public class Dice {

    public static void main(String[] args) {

        Random randomGenerator = new Random();
        int randomDice1 = randomGenerator.nextInt(6) + 1;
        int randomDice2 = randomGenerator.nextInt(6) + 1;

        System.out.println("HERE COMES THE DICE!");
        System.out.println();
        System.out.println("Roll #1: " + randomDice1);
        System.out.println("Roll #2: " + randomDice2);

        int total = randomDice1 + randomDice2;
        System.out.println("The total is " + total + "!");

    }

}
