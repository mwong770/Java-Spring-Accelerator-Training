/**
 * This app stores values from one array into another array but in reverse order, then
 * displays the elements of both arrays.
 * @params args
 *
 */

package com.company;

public class ArrayFunReverseIt {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5};
        int[] numsReversed = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            numsReversed[i] = nums[nums.length - 1 - i];
        }

        System.out.println("Here are the values in the original array.");

        for (int elem : nums) {
            System.out.println(elem);
        }

        System.out.println("Here are the values in the original array in reverse order.");

        for (int elem : numsReversed) {
            System.out.println(elem);
        }

    }

}
