/**
 * This app converts a number entered by the user into a month.
 * Displays the month corresponding to the number entered by the user.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class MonthConverterIf {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter a number between 1 and 12.");
        int userNum = Integer.parseInt(consoleScanner.nextLine());

        if (userNum == 1) {
            System.out.println("January");
        } else if (userNum == 2) {
            System.out.println("February");
        } else if (userNum == 3) {
            System.out.println("March");
        } else if (userNum == 4) {
            System.out.println("April");
        } else if (userNum == 5) {
            System.out.println("May");
        } else if (userNum == 6) {
            System.out.println("June");
        } else if (userNum == 7) {
            System.out.println("July");
        } else if (userNum == 8) {
            System.out.println("August");
        } else if (userNum == 9) {
            System.out.println("September");
        } else if (userNum == 10) {
            System.out.println("October");
        } else if (userNum == 11) {
            System.out.println("November");
        } else if (userNum == 12) {
            System.out.println("December");
        } else {
            System.out.println("You have entered an invalid number. You must\n" +
                    " enter a number between 1 and 12. Goodbye.");
        }

    }

}
