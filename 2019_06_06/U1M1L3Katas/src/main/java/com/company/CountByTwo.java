
/**
 * This program asks the user to input a number.
 * Returns numbers from 0 to the user input counting by 2.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class CountByTwo {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please type a positive number.");
        int userInput = Integer.parseInt(consoleScanner.nextLine());

        for (int i = 0; i <= userInput; i = i+2) {
            System.out.println(i);
        }

    }

}
