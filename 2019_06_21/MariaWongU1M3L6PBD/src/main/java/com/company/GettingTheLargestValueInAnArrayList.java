/**
 * Creates an ArrayList with 10 random integers.
 * Prompts the user for an integer, then displays a message
 * indicating if the integer is present in the list or not.
 *
 * @params args
 */

package com.company;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;
        import java.util.Scanner;

/*
Write a program that creates an ArrayList which can hold Integers.
Fill the ArrayList with random numbers from 1 to 100. Display the values
in the ArrayList on the screen. Then

use a linear search to find the largest
 value in the ArrayList, and display that value.
 */
public class GettingTheLargestValueInAnArrayList {

    public static void main(String[] args) {


        Random randomGenerator = new Random();

        List<Integer> nums = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            nums.add(randomGenerator.nextInt(100) + 1);
        }

        System.out.println("ArrayList: " + nums);

        int largest = nums.get(0);

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > largest) {
                largest = nums.get(i);
            }
        }

        System.out.println(largest);
    }
}