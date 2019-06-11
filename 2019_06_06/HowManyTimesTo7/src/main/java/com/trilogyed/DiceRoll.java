
/**
 * This is a virtual dice game.
 * Displays how many rolls it took to roll the first 7
 * and the total number of 7s rolled out of 100 rolls
 * @params args
 *
 */

package com.trilogyed;

import java.util.Random;

public class DiceRoll {

    public static void main(String[] args) {

        Random randomGenerator = new Random();
        int rollsToFirst7 = 0;
        int totalSevens = 0;

        for (int i = 0; i < 100; i++) {
            int dice1 = randomGenerator.nextInt(6) + 1;
            int dice2 = randomGenerator.nextInt(6) + 1;
            int total = dice1 + dice2;
            System.out.println("Total: " + total);
            if (total == 7) {
                if (rollsToFirst7 == 0) {
                    rollsToFirst7 = i + 1;
                }
                totalSevens++;
            }
        }

        System.out.println("Rolls to First 7: " + rollsToFirst7);
        System.out.println("Total Number of 7s Rolled: " + totalSevens);

    }

}
