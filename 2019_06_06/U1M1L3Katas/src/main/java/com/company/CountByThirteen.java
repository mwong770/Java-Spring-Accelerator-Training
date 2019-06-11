
/**
 * This program asks the user to input a number.
 * Displays numbers from 0 to the user input counting by 13.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class CountByThirteen {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please type a positive number.");
        int userInput = Integer.parseInt(consoleScanner.nextLine());

        for (int i = 0; i <= userInput; i=i+13) {
            System.out.println(i);
        }

    }

}

