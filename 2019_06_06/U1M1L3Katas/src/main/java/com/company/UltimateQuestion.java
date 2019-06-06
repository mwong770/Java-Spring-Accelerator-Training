
/**
 * This program asks the user to input a number within a range.
 * The program continues to prompt the user until the user provides the number 42.
 * Returns an affirmative print statement indicating they selected the correct number once they select 42.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class UltimateQuestion {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Try to guess the number I selected between 1 and 100.");
        int num = Integer.parseInt(consoleScanner.nextLine());

        while (num != 42) {
            System.out.println("Try to guess the number I selected between 1 and 100.");
            num = Integer.parseInt(consoleScanner.nextLine());
        }

        System.out.println("That's the number I was looking for! 42 is definitely the answer!");

    }

}

