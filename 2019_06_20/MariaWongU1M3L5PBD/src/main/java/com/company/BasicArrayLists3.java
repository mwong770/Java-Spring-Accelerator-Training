/**
 * Creates an ArrayList with 1000 random integers,
 * then displays the contents of the ArrayList.
 *
 * @params args
 */

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicArrayLists3 {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();

        Random randomGenerator = new Random();

        for (int i = 0; i < 1000; i++) {
            nums.add(randomGenerator.nextInt(90) + 10);
        }

        System.out.println("ArrayList: " + nums);

    }

}
