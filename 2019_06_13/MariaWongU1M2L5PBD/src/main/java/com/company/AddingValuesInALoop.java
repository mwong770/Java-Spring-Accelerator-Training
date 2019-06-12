/**
 * This app adds several integers inputted from the user.
 * Inputting a 0 ends the game.
 * Displays the user inputs and the sum of all inputs.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class AddingValuesInALoop {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int total = 0;

        System.out.println("I will add up the integers you give me.");
        System.out.print("Number: ");

        int userInput = Integer.parseInt(scan.next());

        while (userInput != 0) {

            total += userInput;
            System.out.println("The total so far is " + total);

            System.out.print("Number: ");
            userInput = Integer.parseInt(scan.next());

        }

        System.out.println("Number: " + userInput);
        System.out.println("\nThe total is " + total);

    }

}
