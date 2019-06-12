/**
 * This class provides methods to read user input, convert it to
 * various data types and return it to the user.
 * Returns user input converted to various data types
 * (int, long, double, float, String).
 * @params args
 *
 */

package com.company;
import com.company.interfaces.UserIO;

import java.util.Scanner;

public class Input implements UserIO {

    public int readInt(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        int userInput = scan.nextInt();
        return userInput;
    }

    public long readLong(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        long userInput = scan.nextLong();
        return userInput;
    }

    public double readDouble(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        double userInput = scan.nextDouble();
        return userInput;
    }

    public float readFloat(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        float userInput = scan.nextFloat();
        return userInput;
    }

    public String readString(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = scan.nextLine();
        return userInput;
    }

}
