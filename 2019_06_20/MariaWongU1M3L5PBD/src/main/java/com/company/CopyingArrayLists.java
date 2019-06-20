/**
 * Creates an ArrayList with 10 random integers, copies the values to
 * another ArrayList, modifies a value in one list,
 * then displays the contents of both ArrayLists.
 * Uses loops.
 *
 * @params args
 */

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CopyingArrayLists {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        List<Integer> numsCopy = new ArrayList<>();

        Random randomGenerator = new Random();

        for (int i = 0; i < 10; i++) {
            nums.add(randomGenerator.nextInt(100) + 1);
        }

        nums.stream()
                .forEach(x -> numsCopy.add(x));

        nums.set(nums.size() - 1, -7);

        System.out.println("ArrayList 1: \n" + nums);

        System.out.println("\nArrayList Copy: \n" + numsCopy);

    }

}
