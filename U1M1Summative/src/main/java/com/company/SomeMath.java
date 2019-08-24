/**
 * This app solves simple math problems.
 * Returns the sum, average and the largest number given 5 numbers.
 * @params args
 *
 */

package com.company;

public class SomeMath {

    public static void main(String[] args) {

        int sum = total5(1, 2, 3, 4, 5);
        System.out.println("Sum of 5 numbers: " + sum);

        double average = average5(1, 3, 5, 7, 9);
        System.out.println("Average of 5 numbers: " + average);

        double largestNum = largest5(42.0, 35.1, 2.3, 40.2, 15.6);
        System.out.println("Largest of 5 numbers: " + largestNum);

    }

    public static int total5(int num1, int num2, int num3, int num4, int num5) {
        return num1 + num2 + num3 + num4 + num5;
    }

    public static double average5(int num1, int num2, int num3, int num4, int num5) {
        return (double) (num1 + num2 + num3 + num4 + num5) / 5;
    }

    public static double largest5(double num1, double num2, double num3, double num4, double num5) {

        double largestNum = num1;

        // not in 'else if' because need to check all numbers
        if (num2 > largestNum) {
            largestNum = num2;
        }
        if (num3 > largestNum) {
            largestNum = num3;
        }
        if (num4 > largestNum) {
            largestNum = num4;
        }
        if (num5 > largestNum) {
            largestNum = num5;
        }

        return largestNum;

    }

}
