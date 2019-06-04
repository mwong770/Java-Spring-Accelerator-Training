package com.company;

import java.util.Scanner;

public class DoubleAndAddFive {

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter a number.");
        int num = consoleScanner.nextInt();

        int total = (num * 2) + 5;

        System.out.println(total);
    }
}
