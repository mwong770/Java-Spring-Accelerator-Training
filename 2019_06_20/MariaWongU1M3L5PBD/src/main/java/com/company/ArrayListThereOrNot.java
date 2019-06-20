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

public class ArrayListThereOrNot {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();

        Random randomGenerator = new Random();

        Scanner scan = new Scanner(System.in);

        boolean isInList = false;

        for (int i = 0; i < 10; i++) {
            nums.add(randomGenerator.nextInt(50) + 1);
        }

        System.out.println("ArrayList: " + nums);

        System.out.print("Value to Find: ");
        int userInput = Integer.parseInt(scan.nextLine());

        System.out.println();

        for (int i = 0; i < nums.size(); i++) {
            if (userInput == nums.get(i)) {
                System.out.println(userInput + " is in the ArrayList.");
                isInList = true;
            }
        }

        if (!isInList) {
            System.out.println(userInput + " is not in the ArrayList.");
        }

    }

}
