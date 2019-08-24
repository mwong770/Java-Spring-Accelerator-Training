/**
 * This app stores words inputted by the user in an array, then
 * displays the elements of the array.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class ArrayFun5Words {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        String[] words = new String[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Please enter a word.");
            words[i] = consoleScanner.nextLine();
        }

        System.out.println("\nHere are the words you entered:\n");

        for (String elem : words) {
            System.out.println(elem);
        }

    }

}
