package com.company;

import java.util.Scanner;
import static java.lang.Float.parseFloat;
import java.text.NumberFormat;

public class LuxuryTaxCalculator {

    public static void main(String[] args) {
        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter the salary for Player 1 (without the dollar sign or commas).");
        float salaryPlayer1 = parseFloat(consoleScanner.nextLine());

        System.out.println("Please enter the salary for Player 2 (without the dollar sign or commas).");
        float salaryPlayer2 = parseFloat(consoleScanner.nextLine());

        System.out.println("Please enter the salary for Player 3 (without the dollar sign or commas).");
        float salaryPlayer3 = parseFloat(consoleScanner.nextLine());

        float total = salaryPlayer1 + salaryPlayer2 + salaryPlayer3;

        NumberFormat defaultLocaleFormat = NumberFormat.getCurrencyInstance();

        System.out.println("Total salary: " + defaultLocaleFormat.format(total) + ".");

        int spendingLimit = 40000000;
        float taxRate = 0.18f;

        if (total > spendingLimit) {
            float amtOverLimit = total - spendingLimit;
            float luxTax = amtOverLimit * taxRate;

            System.out.println("Luxury tax: " + defaultLocaleFormat.format(luxTax) + ".");
        } else {
            System.out.println("You don't have to pay any luxury tax.");
        }


    }
}
