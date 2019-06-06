
/**
 * This program asks the user for their age.
 * Returns a follow-up question based on the user's age.
 *
 */

package com.company;

import java.util.Scanner;

public class AgeAgain {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter your age.");
        int age = Integer.parseInt(consoleScanner.nextLine());

        if (age > 18) {
            System.out.println("What is your job?");
            String job = consoleScanner.nextLine();
            System.out.println(job + " sounds like a great job!");
        } else if (age >= 14 && age <=18) {
            System.out.println("Are you planning on going to college?");
            String collegeBound = consoleScanner.nextLine();
            System.out.println("test:   " + collegeBound.toLowerCase());
            if (collegeBound.toLowerCase().equals("yes")) {
                System.out.println("Which college?");
                String college = consoleScanner.nextLine();
                System.out.println(college + " is a great school!");
            } else if (collegeBound.toLowerCase().equals("no")) {
                System.out.println("What do you want to do after high school?");
                String plan = consoleScanner.nextLine();
                System.out.println("Wow, " + plan + " sounds like a plan!");
            }
        } else if (age < 14) {
                System.out.println("What grade are you in?");
                String grade = consoleScanner.nextLine();
                System.out.println("Wow! " + grade + " grade - that sounds exciting!");
        }

    }

}
