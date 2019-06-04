package com.company;

import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class AverageThree {

    public static void main(String[] args) {

        NumberFormat formatter = new DecimalFormat("#0.00");

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter a number.");
        float num1 = Float.parseFloat(consoleScanner.nextLine());

        System.out.println("Please enter another number.");
        float num2 = Float.parseFloat(consoleScanner.nextLine());

        System.out.println("Please enter another number");
        float num3 = Float.parseFloat(consoleScanner.nextLine());

        float answer = ((num1 + num2 + num3)/3);

        System.out.println(formatter.format(answer));

    }
}