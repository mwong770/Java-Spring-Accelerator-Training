
/**
 * This program asks the user to input a number.
 * Displays whether the number is even or odd.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class EvenOrOdd {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please type a positive number.");
        int userInput = Integer.parseInt(consoleScanner.nextLine());

        if (userInput % 2 == 0) {
            System.out.println("Even");
        } else {
            System.out.println("Odd");
        }

    }

}
