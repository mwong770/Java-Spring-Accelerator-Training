/**
 * Creates an ArrayList with 10 random integers.
 * Prompts the user for an integer, then displays a message
 * indicating if the integer is present in the list or not.
 *
 * @params args
 */

//ADD COMMENTS TO SUMMATIVE ************************

package com.company;

/*
Write a program that creates an ArrayList of Integers.
 Fill the ArrayList with random numbers from 1 to 100.
 Display the values in the ArrayList on the screen. Then use a linear
  search to find the largest value in the ArrayList, and display that
   value and its slot number.
 */

//ADD COMMENTS TO SUMMATIVE ************************


        import java.util.ArrayList;
        import java.util.List;
        import java.util.Random;

public class LocatingTheLargestValueInAnArrayList {

    public static void main(String[] args) {


        Random randomGenerator = new Random();

        List<Integer> nums = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            nums.add(randomGenerator.nextInt(100) + 1);
        }

        System.out.println("ArrayList: " + nums);

        int largest = nums.get(0);

        //find all
        int largestIdx = 0;

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > largest) {
                largest = nums.get(i);
                largestIdx = i;
            }
        }

        System.out.println(largest);
        System.out.println(largestIdx);
    }
}