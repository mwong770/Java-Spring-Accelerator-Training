/**
 * This app converts a number entered by the user into a month.
 * Displays the month corresponding to the number entered by the user.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class MonthConverterSwitch {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter a number between 1 and 12.");
        int userNum = Integer.parseInt(consoleScanner.nextLine());

        switch (userNum) {
            case 1:
                System.out.println("January");
                break;
            case 2:
                System.out.println("February");
                break;
            case 3:
                System.out.println("March");
                break;
            case 4:
                System.out.println("April");
                break;
            case 5:
                System.out.println("May");
                break;
            case 6:
                System.out.println("June");
                break;
            case 7:
                System.out.println("July");
                break;
            case 8:
                System.out.println("August");
                break;
            case 9:
                System.out.println("September");
                break;
            case 10:
                System.out.println("October");
                break;
            case 11:
                System.out.println("November");
                break;
            case 12:
                System.out.println("December");
                break;
            default:
                System.out.println("You have entered an invalid number. You must\n" +
                        " enter a number between 1 and 12. Goodbye.");
        }

    }

}
