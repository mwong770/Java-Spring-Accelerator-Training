/**
 * Creates an ArrayList with ten integers,
 * then displays the contents of the ArrayList.
 * Does not use loops.
 *
 * @params args
 */

package com.company;

import java.util.ArrayList;
import java.util.List;

public class BasicArrayLists0 {

    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();

        nums.add(-113);
        nums.add(-113);
        nums.add(-113);
        nums.add(-113);
        nums.add(-113);
        nums.add(-113);
        nums.add(-113);
        nums.add(-113);
        nums.add(-113);
        nums.add(-113);

        System.out.println("Slot 0 contains a " + nums.get(0) + ".");
        System.out.println("Slot 1 contains a " + nums.get(1) + ".");
        System.out.println("Slot 2 contains a " + nums.get(2) + ".");
        System.out.println("Slot 3 contains a " + nums.get(3) + ".");
        System.out.println("Slot 4 contains a " + nums.get(4) + ".");
        System.out.println("Slot 5 contains a " + nums.get(5) + ".");
        System.out.println("Slot 6 contains a " + nums.get(6) + ".");
        System.out.println("Slot 7 contains a " + nums.get(7) + ".");
        System.out.println("Slot 8 contains a " + nums.get(8) + ".");
        System.out.println("Slot 9 contains a " + nums.get(9) + ".");

    }

}

