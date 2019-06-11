
/**
 * This is a mortgage payment calculator.
 * Displays a mortgage payment amount given the following:
 * - The amount of the mortgage
 * - The term of the mortgage
 * - The annual interest rate of the mortgage
 * The formula used can be found here: https://www.mtgprofessor.com/formulas.htm
 * @params args
 *
 */

package com.company;

import java.util.Scanner;


public class LoanCalculator {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter your mortgage amount.");
        double mortgageAmt = Double.parseDouble(consoleScanner.nextLine());

        System.out.println("Please enter your mortgage term in months.");
        int termInMonths = Integer.parseInt(consoleScanner.nextLine());

        System.out.println("Please enter your interest rate.");
        double intRate = Double.parseDouble(consoleScanner.nextLine());

        double monthlyInterest = (intRate/100)/12;

        double mortgagePayment = mortgageAmt * (monthlyInterest * Math.pow((1+monthlyInterest),termInMonths))
                / (Math.pow((1 + monthlyInterest),termInMonths)-1) ;

        //truncates mortgagePayment to 2 decimal places
        System.out.println(Math.floor(mortgagePayment * 100) / 100);

    }
}
