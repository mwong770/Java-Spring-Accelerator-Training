
package com.company;

import java.util.Scanner;

public class SubtractTwo {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter a number.");
        int num1 = consoleScanner.nextInt();

        System.out.println("Please enter another number.");
        int num2 = consoleScanner.nextInt();

        int total = num1 -  num2 ;
        System.out.println(total);

    }
}
