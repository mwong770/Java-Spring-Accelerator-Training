/**
 * Creates an ArrayList with ten integers,
 * then displays the contents of the ArrayList.
 * Uses loops.
 *
 * @params args
 */

package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BasicArrayLists1 {

    public static void main(String[] args) {

        // Creates a List with an initial size of 10
        // The size was set to meet the requirements of using size() in the condition of the
        // loop used to fill up the ArrayList with numbers
        List<Integer> nums = new ArrayList<>(Arrays.asList(new Integer[10]));

        for (int i = 0; i < nums.size(); i++) {
            nums.set(i, -113);
        }

        for (int i = 0; i < nums.size(); i++) {
            System.out.println("Slot " + i + " contains a " + nums.get(i) + ".");
        }

    }

}
