
/**
 * This program asks the user to input a number.
 * Displays numbers from 0 to the user input.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class CountTo {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please type a positive number.");
        int userInput = Integer.parseInt(consoleScanner.nextLine());

        for (int i = 0; i <= userInput; i++) {
            System.out.println(i);
        }

    }

}
