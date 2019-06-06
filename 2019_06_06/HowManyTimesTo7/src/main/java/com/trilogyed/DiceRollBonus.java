
/**
 * This is a virtual dice game.
 * Returns a print statement indicating how many rolls it took to roll each value of 2 to 12
 * and the total number of values 2 to 12 rolled out of a number of rolls selected by the user
 * @params args
 *
 */

package com.trilogyed;

import java.util.Random;
import java.util.Scanner;

public class DiceRollBonus {

    public static void main(String[] args) {

        Random randomGenerator = new Random();
        Scanner consoleScanner = new Scanner(System.in);

        int rollsToFirst2 = 0;
        int rollsToFirst3 = 0;
        int rollsToFirst4 = 0;
        int rollsToFirst5 = 0;
        int rollsToFirst6 = 0;
        int rollsToFirst7 = 0;
        int rollsToFirst8 = 0;
        int rollsToFirst9 = 0;
        int rollsToFirst10 = 0;
        int rollsToFirst11 = 0;
        int rollsToFirst12 = 0;

        int totalTwos = 0;
        int totalThrees = 0;
        int totalFours = 0;
        int totalFives = 0;
        int totalSixes = 0;
        int totalSevens = 0;
        int totalEights = 0;
        int totalNines = 0;
        int totalTens = 0;
        int totalElevens = 0;
        int totalTwelves = 0;

        System.out.println("How many times would you like to roll the dice?");
        int numRolls = consoleScanner.nextInt();

        for (int i = 0; i < numRolls; i++) {
            int dice1 = randomGenerator.nextInt(6) + 1;
            int dice2 = randomGenerator.nextInt(6) + 1;
            int total = dice1 + dice2;
            System.out.println("Total: " + total);

            switch (total) {
                case 2:
                    if (rollsToFirst2 == 0) {
                        rollsToFirst2 = i + 1;

                    }
                    totalTwos++;
                    break;
                case 3:
                    if (rollsToFirst3 == 0) {
                        rollsToFirst3 = i + 1;

                    }
                    totalThrees++;
                    break;
                case 4:
                    if (rollsToFirst4 == 0) {
                        rollsToFirst4 = i + 1;

                    }
                    totalFours++;
                    break;
                case 5:
                    if (rollsToFirst5 == 0) {
                        rollsToFirst5 = i + 1;

                    }
                    totalFives++;
                    break;
                case 6:
                    if (rollsToFirst6 == 0) {
                        rollsToFirst6 = i + 1;

                    }
                    totalSixes++;
                    break;
                case 7:
                    if (rollsToFirst7 == 0) {
                        rollsToFirst7 = i + 1;

                    }
                    totalSevens++;
                    break;
                case 8:
                    if (rollsToFirst8 == 0) {
                        rollsToFirst8 = i + 1;

                    }
                    totalEights++;
                    break;
                case 9:
                    if (rollsToFirst9 == 0) {
                        rollsToFirst9 = i + 1;

                    }
                    totalNines++;
                    break;
                case 10:
                    if (rollsToFirst10 == 0) {
                        rollsToFirst10 = i + 1;

                    }
                    totalTens++;
                    break;
                case 11:
                    if (rollsToFirst11 == 0) {
                        rollsToFirst11 = i + 1;

                    }
                    totalElevens++;
                    break;
                case 12:
                    if (rollsToFirst12 == 0) {
                        rollsToFirst12 = i + 1;

                    }
                    totalTwelves++;
                    break;
                default:
                    System.out.println("An error occurred while rolling the dice.");
            }

        }

        System.out.println("Rolls to First 2: " + rollsToFirst2);
        System.out.println("Rolls to First 3: " + rollsToFirst3);
        System.out.println("Rolls to First 4: " + rollsToFirst4);
        System.out.println("Rolls to First 5: " + rollsToFirst5);
        System.out.println("Rolls to First 6: " + rollsToFirst6);
        System.out.println("Rolls to First 7: " + rollsToFirst7);
        System.out.println("Rolls to First 8: " + rollsToFirst8);
        System.out.println("Rolls to First 9: " + rollsToFirst9);
        System.out.println("Rolls to First 10: " + rollsToFirst10);
        System.out.println("Rolls to First 11: " + rollsToFirst11);
        System.out.println("Rolls to First 12: " + rollsToFirst12);


        System.out.println("Total Number of 2s Rolled: " + totalTwos);
        System.out.println("Total Number of 3s Rolled: " + totalThrees);
        System.out.println("Total Number of 4s Rolled: " + totalFours);
        System.out.println("Total Number of 5s Rolled: " + totalFives);
        System.out.println("Total Number of 6s Rolled: " + totalSixes);
        System.out.println("Total Number of 7s Rolled: " + totalSevens);
        System.out.println("Total Number of 8s Rolled: " + totalEights);
        System.out.println("Total Number of 9s Rolled: " + totalNines);
        System.out.println("Total Number of 10s Rolled: " + totalTens);
        System.out.println("Total Number of 11s Rolled: " + totalElevens);
        System.out.println("Total Number of 12s Rolled: " + totalTwelves);

    }

}
