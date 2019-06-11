
/**
 * This is a number guessing game.
 * The user must guess a random number.
 * Displays if the user's guess is too high, too low, or correct,
 * and prints how many tries it took the user to guess the correct number.
 * @params args
 *
 */

package com.company;

import java.util.Random;
import java.util.Scanner;

public class HiLo {

    public static void main(String[] args) {

        Random randomGenerator = new Random();
        Scanner consoleScanner = new Scanner(System.in);
        int randomInt = randomGenerator.nextInt(100) + 1;

        System.out.println("Welcome to Hi-Low!");

        System.out.println("What is your name?");
        String name = consoleScanner.nextLine();

        System.out.println("OK, " + name + ", here are the rules:");
        System.out.println("Please guess the number I selected between 1 and 100, inclusive.");
        int userGuess = Integer.parseInt(consoleScanner.nextLine());

        int numGuesses = 1;

        while (randomInt != userGuess) {
            numGuesses++;
            if (userGuess > randomInt) {
                System.out.println("Too high!");
            } else {
                System.out.println("Too low");
            }
            System.out.println("Please guess the number I selected between 1 and 100, inclusive.");
            userGuess = Integer.parseInt(consoleScanner.nextLine());
        }

        System.out.println("Congratulations, " + name + "! You win!”\n" +
                "“It took you " + numGuesses +  " guesses to find my number!");
        
    }
}
