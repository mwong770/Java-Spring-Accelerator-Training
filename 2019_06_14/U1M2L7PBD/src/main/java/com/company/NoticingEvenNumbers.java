/**
 *
 * Displays numbers 1 to 20, marking those which are divisible by 2.
 * @params args
 *
 */

package com.company;

public class NoticingEvenNumbers {

    public static void main(String[] args) {

        for (int i = 1; i < 21; i++) {
            if (i % 2 == 0) {
                System.out.println(i + " <");
            } else {
                System.out.println(i);
            }
        }

    }

}
