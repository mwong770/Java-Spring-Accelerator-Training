/**
 *
 * Displays the square root of a number typed in by the user.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class SafeSquareRoot {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("\nSQUARE ROOT\n");

        System.out.print("Enter a number: ");

        int userInput = scan.nextInt();

        while (userInput < 0) {
            System.out.println("You can't take the square root of a negative number, silly.");
            System.out.print("Try again: ");
            userInput = scan.nextInt();
        }

        System.out.println("The square root of " + userInput + " is " + Math.sqrt(userInput));

    }

}
