/**
 * This is a number guessing game in which users try to guess a random number until the user gets it right up to 7 times max.
 * Displays whether the user's guess is too high, too low, or correct.
 * @params args
 *
 */

import java.util.Random;
import java.util.Scanner;

public class HiLoWithLimitedTries {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(100) + 1;

        System.out.println("I'm thinking of a number from 1 to 100. What number am I thinking of?");
        int userGuess = Integer.parseInt(scan.nextLine());

        System.out.println("First guess: " + userGuess);

        displayIfHighOrLow(userGuess, randomInt);

        int counter = 1;

        while (userGuess != randomInt && counter < 7) {

            System.out.println("Try again. I'm thinking of a number from 1 to 100.");
            userGuess = Integer.parseInt(scan.nextLine());

            counter++;

            displayIfHighOrLow(userGuess, randomInt);

            System.out.println("Guess # " + counter + " : " + userGuess);

        }

        if (userGuess == randomInt) {
            System.out.println("You guessed it! What are the odds?!?");
        } else {
            System.out.println("Sorry, you didn't guess it in " + counter + " tries. You lose.");
        }

    }

    public static void displayIfHighOrLow(int userGuess, int randomInt) {
        if (userGuess < randomInt) {
            System.out.println("Sorry, you are too low.");
        } else if (userGuess > randomInt)  {
            System.out.println("Sorry, that guess is too high.");
        }
    }

}
