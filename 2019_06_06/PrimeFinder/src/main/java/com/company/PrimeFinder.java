
/**
 *
 * Returns all the prime numbers from 1 to a number inputted by the user.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class PrimeFinder {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter a number greater than 0.");
        int num = Integer.parseInt(consoleScanner.nextLine());

        for (int i = 2; i <= num; i++) {

            boolean primeNum = true;

            //checks if i is divisible by any numbers smaller than itself
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    primeNum = false;
                    break;
                }
            }
            if (primeNum) {
                System.out.println(i);
            }
        }

    }

}

