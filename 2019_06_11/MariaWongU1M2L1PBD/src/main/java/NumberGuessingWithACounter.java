/**
 * This is a number guessing game in which users try to guess a random number until the user gets it right.
 * Displays the random number and how many tries it took for the user to guess it.
 * @params args
 *
 */

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingWithACounter {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10) + 1;

        System.out.println("I'm thinking of a number from 1 to 10. What number am I thinking of?");
        int userGuess = Integer.parseInt(scan.nextLine());

        System.out.println("Your guess: " + userGuess);

        int counter = 1;

        while (userGuess != randomInt) {
            System.out.println("That's not correct. Try again. I'm thinking of a number from 1 to 10.");
            userGuess = Integer.parseInt(scan.nextLine());
            counter++;
        }

        System.out.println("That's right! My secret number was " + randomInt + "!");
        System.out.println("It only took you " + counter + " tries.");

    }

}
