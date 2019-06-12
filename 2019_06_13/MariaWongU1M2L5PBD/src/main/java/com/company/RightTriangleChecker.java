/**
 * This app determines whether three user-inputted integers
 * could represent the sides of a right triangle.
 * Displays the user inputs once inputted all in ascending order and a
 * message indicating if they can represent a right triangle.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class RightTriangleChecker {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int[] sidesArr = new int[3];

        System.out.println("Enter three integers:");
        System.out.print("Side 1: ");
        int userInput = scan.nextInt();

        sidesArr[0] = userInput;
        int arrIdx = 1; // represents first element in array as 1 instead of 0 for user display

        do {

            System.out.print("Side " + (arrIdx + 1) + ": ");
            userInput = scan.nextInt();

            // compares user input to last input added to sidesArr
            // arrIdx-1 corrects for arrIdx starting at 1, not 0
            if (userInput < sidesArr[arrIdx-1]) {
                System.out.println(userInput + " is smaller than " + sidesArr[arrIdx-1] + ". Try again.");
            } else {
                sidesArr[arrIdx] = userInput;
                arrIdx++;
            }

        } while(arrIdx < 3);

        System.out.println("\nYour three sides are " + sidesArr[0] + " " + sidesArr[1] + " " + sidesArr[2]);

        // applies Pythagoras' theorem to determine if the numbers will make a right triangle, a^2 + b^2 = c^2
        if ( (sidesArr[0] * sidesArr[0]) + (sidesArr[1] * sidesArr[1]) == (sidesArr[2] * sidesArr[2]) ) {
            System.out.println("These sides *do* make a right triangle. Yippy-skippy!");
        } else {
            System.out.println("NO! These sides do not make a right triangle!");
        }

    }

}
