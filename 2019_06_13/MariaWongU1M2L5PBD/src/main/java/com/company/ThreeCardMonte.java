/**
 * This is a number guessing game.
 * It prompts the user to guess which of 3 "cards" is an Ace (a randomly selected number).
 * Displays a message indicating whether the user guessed correctly or not.
 * @params args
 *
 */

package com.company;

import java.util.Random;
import java.util.Scanner;

public class ThreeCardMonte {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        // randomly selects a card to be the Ace
        Random randomGenerator = new Random();
        int randomCard = randomGenerator.nextInt(3) + 1;

        System.out.println("\nYou slide up to Fast Eddie's card table and plop down your cash.\n" +
                           "He glances at you out of the corner of his eye and starts shuffling.\n" +
                           "He lays down three cards.\n\n" +

                            "Which one is the ace?\n" +

                            "     ##   ##   ##\n" +
                            "     ##   ##   ##\n" +
                            "     1    2    3\n");

        int userInput = scan.nextInt();

        if (userInput != 1 && userInput != 2 & userInput != 3) {
            System.out.println("Oops. You did not pick a valid card number.");
        }
        else if (userInput == randomCard) {
            System.out.println("You nailed it! Fast Eddie reluctantly " +
                    "hands over your winnings, scowling.");
        } else {
            System.out.println("Ha! Fast Eddie wins again! " +
                    "The ace card was card number " + randomCard);
        }

    }

}
