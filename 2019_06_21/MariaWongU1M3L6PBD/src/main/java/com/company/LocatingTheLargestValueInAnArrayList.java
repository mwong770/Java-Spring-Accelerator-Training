/**
 * Creates and displays an ArrayList with 10 random integers.
 * Finds and displays the largest value in the ArrayList and its index number(s).
 *
 * @params args
 */

package com.company;

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

        int currentLargest = nums.get(0);

        List<Integer> indexList = new ArrayList<>();

        // if a number larger than currentLargest is found, 
        // assigns it to currentLargest, empties indexList and adds its index to indexList
        // if a number equal to currentLargest is found, adds its index to indexList
        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i) > currentLargest) {
                currentLargest = nums.get(i);
                indexList.clear();
                indexList.add(i);
            } else if (nums.get(i) == currentLargest) {
                indexList.add(i);
            }
        }

        // displays the largest number and its index/indices
        if (indexList.size() == 1) {
            System.out.println("\nThe largest value is " + currentLargest + ", which is in slot " + indexList.get(0) + ".");
        } else {
            System.out.print("\nThe largest value is " + currentLargest + ", which is in slots ");
            for (int i = 0; i < indexList.size(); i++) {
                if (i == (indexList.size() - 1)) {
                    System.out.print(indexList.get(i));
                } else {
                    System.out.print(indexList.get(i) + " and ");
                }
            }
            System.out.print(".\n");
        }

    }

}