/**
 * Creates and displays an ArrayList with 10 random integers.
 * Finds and displays the largest value in the ArrayList.
 *
 * @params args
 */

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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