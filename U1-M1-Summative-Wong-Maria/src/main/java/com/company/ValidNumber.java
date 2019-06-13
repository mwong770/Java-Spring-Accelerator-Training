/**
 * This app prompts the user for a number between 1 and 10 until the entered number is in range.
 * Displays the number entered when in range or a message asking the user to try again.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class ValidNumber {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter a number between 1 and 10.");
        int userNum = Integer.parseInt(consoleScanner.nextLine());

        while (userNum < 1 || userNum > 10) {
            System.out.println("You must enter a number between 1 and 10, please try again.");
            userNum = Integer.parseInt(consoleScanner.nextLine());
        }

        System.out.println("Great. You chose number " + userNum + ".");

    }

}