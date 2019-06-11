/**
 * This is a number guessing game in which users try to guess a random number.
 * Displays the random number and whether the user got it right or wrong.
 * @params args
 *
 */

import java.util.Random;
import java.util.Scanner;

public class ANumberGuessingGame {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10) + 1;

        System.out.println("I'm thinking of a number from 1 to 10. What number am I thinking of?");
        int userGuess = Integer.parseInt(scan.nextLine());

        System.out.println("Your guess: " + userGuess);

        if (userGuess == randomInt) {
            System.out.println("That's right! My secret number was " + randomInt + "!");
        } else {
            System.out.println("Sorry, but I was really thinking of " + randomInt + ".");
        }

    }

}
