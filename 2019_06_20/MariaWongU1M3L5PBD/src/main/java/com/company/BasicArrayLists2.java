/**
 * Creates an ArrayList with ten random integers,
 * then displays the contents of the ArrayList.
 *
 * @params args
 */

package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BasicArrayLists2 {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();

        Random randomGenerator = new Random();

        for (int i = 0; i < 10; i++) {
            nums.add(randomGenerator.nextInt(100) + 1);
        }

        System.out.println("ArrayList: " + nums);

    }

}
