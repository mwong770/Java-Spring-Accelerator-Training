/**
 * This class provides methods to read user input, convert it to various data types and
 * return it to the user. Handles InputMismatchException exceptions.
 * Returns user input converted to various data types.
 * (int, long, double, float, String)
 * @params args
 *
 */

package com.company;
import com.company.interfaces.UserIO;

import java.util.Scanner;

public class BetterInput implements UserIO {

    public int readInt(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        int userInput = 0;
        try {
            userInput = scan.nextInt();
        }
        catch (Exception InputMismatchException) {
            System.out.println("Oops. You did not enter the correct value type.");
            readInt(prompt);
        } finally {
            return userInput;
        }
    }

    public long readLong(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        long userInput = 0;
        try {
            userInput = scan.nextLong();
        }
        catch (Exception InputMismatchException) {
            System.out.println("Oops. You did not enter the correct value type.");
            readLong(prompt);
        } finally {
            return userInput;
        }
    }

    public double readDouble(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        double userInput = 0;
        try {
            userInput = scan.nextDouble();
        }
        catch (Exception InputMismatchException) {
            System.out.println("Oops. You did not enter the correct value type.");
            readDouble(prompt);
        } finally {
            return userInput;
        }
    }

    public float readFloat(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        float userInput = 0;
        try {
            userInput = scan.nextFloat();
        }
        catch (Exception InputMismatchException) {
            System.out.println("Oops. You did not enter the correct value type.");
            readFloat(prompt);
        } finally {
            return userInput;
        }
    }

    public String readString(String prompt) {
        Scanner scan = new Scanner(System.in);
        System.out.println(prompt);
        String userInput = scan.nextLine();
        return userInput;
    }

}
