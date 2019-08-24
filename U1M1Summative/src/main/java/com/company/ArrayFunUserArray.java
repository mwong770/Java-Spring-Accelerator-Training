/**
 * This app stores integers inputted by the user in an array, then
 * displays the elements of the array.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class ArrayFunUserArray {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        int[] userNums = new int[5];

        for (int i = 0; i < 5; i++) {
            System.out.println("Please enter a number.");
            userNums[i] = Integer.parseInt(consoleScanner.nextLine());
        }

        System.out.println("\nHere are the numbers you entered:\n");

        for (int elem : userNums) {
            System.out.println(elem);
        }

    }

}
