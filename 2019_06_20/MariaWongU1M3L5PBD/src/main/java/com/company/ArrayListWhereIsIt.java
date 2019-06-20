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

public class ArrayListWhereIsIt {

    public static void main(String[] args) {

        Random randomGenerator = new Random();

        Scanner scan = new Scanner(System.in);

        List<Integer> nums = new ArrayList<>();

        List<Integer> indexList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            nums.add(randomGenerator.nextInt(50) + 1);
        }

        System.out.println("ArrayList: " + nums);

        System.out.print("Value to Find: ");
        int userInput = Integer.parseInt(scan.nextLine());

        // if the user selected value is in the ArrayList,
        // store the indices where it is found in indexList
        for (int i = 0; i < nums.size(); i++) {
            if (userInput == nums.get(i)) {
                indexList.add(i);
            }
        }

        System.out.println();

        // if there are no values in indexList, display a message saying the value was not found.
        // otherwise, display messages indicating the indices where the value was found.
        if (indexList.size() == 0) {
            System.out.println(userInput + " is not in the ArrayList.");
        } else {
            indexList.forEach(i -> System.out.println(userInput + " is in slot " + i + "."));
        }

    }

}
