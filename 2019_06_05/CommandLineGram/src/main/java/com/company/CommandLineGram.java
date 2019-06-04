package com.company;

import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CommandLineGram {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);

        System.out.println("Please enter your first name.");
        String firstName = consoleScanner.nextLine();

        System.out.println("Please enter your last name.");
        String lastName = consoleScanner.nextLine();

        System.out.println("Please enter your email.");
        String email = consoleScanner.nextLine();

        System.out.println("Please enter your twitter handle.");
        String twitter = consoleScanner.nextLine();

        System.out.println("Please enter your age.");
        int age = parseInt(consoleScanner.nextLine());

        System.out.println("Please enter your country.");
        String country = consoleScanner.nextLine();

        System.out.println("Please enter your profession.");
        String profession = consoleScanner.nextLine();

        System.out.println("Please enter your favorite operating system.");
        String favOS = consoleScanner.nextLine();

        System.out.println("Please enter your favorite programming language.");
        String favLang = consoleScanner.nextLine();

        System.out.println("Please enter your favorite computer scientist.");
        String favComputerSci = consoleScanner.nextLine();

        System.out.println("Please enter your favorite keyboard shortcut.");
        String favShortcut = consoleScanner.nextLine();

        System.out.println("Have you ever built your own computer?");
        String builtComputer = consoleScanner.nextLine();

        System.out.println("If you could be a superhero, who would it be?");
        String hero = consoleScanner.nextLine();

        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Twitter Handle: " + twitter);
        System.out.println("Age: " + age);
        System.out.println("Country: " + country);
        System.out.println("Profession: " + profession);
        System.out.println("Favorite Operating System: " + favOS);
        System.out.println("Favorite Language: " + favLang);
        System.out.println("Favorite Computer Scientist: " + favComputerSci);
        System.out.println("Favorite Keyboard Shortcut: " + favShortcut);
        System.out.println("Built Computer: " + builtComputer);
        System.out.println("Superhero You'd Be: " + hero);
    }
}
