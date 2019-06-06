
/**
 * This program repeatedly asks the user to input a programming language until the user types "Java".
 * Returns an affirmative print statement indicating "Java" is the correct response.
 * @params args
 *
 */

package com.company;

import java.util.Scanner;

public class FavoriteProgrammingLanguage {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Try to guess my favorite programming language.");
        String favLanguage = consoleScanner.nextLine();

        while (!favLanguage.equals("Java")) {
            System.out.println("Try to guess my favorite programming language.");
            favLanguage = consoleScanner.nextLine();
        }
        System.out.println("That's what I was looking for! Java is definitely the answer!");

    }
}
