
/**
 * This program asks the user to input a number within a range.
 * The program continues to prompt the user until the user provides a number that is in range.
 * Returns the user's number to the console once they select a number in range.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class RangeChecker {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please choose a number between 15 and 32, not inclusive");
        int userNumber = Integer.parseInt(consoleScanner.nextLine());

        while (userNumber < 15 || userNumber >=32) {
            System.out.println("Please choose a number between 15 and 32, not inclusive");
            userNumber = Integer.parseInt(consoleScanner.nextLine());
        }
        System.out.println("Great! You chose " + userNumber);

    }

}
