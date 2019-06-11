
/**
 * Displays numbers 1 to 100 with the following exceptions:
 * -	If the number is a multiple of 3, prints FIZZ instead of the number.
 * -	If the number is a multiple of 5, prints BUZZ instead of the number.
 * -	If the number is a multiple of 3 and 5, prints FIZZBUZZ instead of the number.
 * @params args
 *
 */

package com.company;

public class FizzBuzz {

    public static void main(String[] args) {

        for (int i = 1; i <= 100; i++) {
            if ((i%3 == 0) && (i%5 == 0)) {
                System.out.println("FIZZBUZZ");
            } else if (i%5 == 0){
                System.out.println("BUZZ");
            } else if (i%3 == 0){
                System.out.println("FIZZ");
            } else {
                System.out.println(i);
            }
        }

    }
}
