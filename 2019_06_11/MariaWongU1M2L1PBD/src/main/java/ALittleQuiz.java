/**
 * This is an interactive quiz in which users answer questions.
 * Displays how many answers the user got right.
 * @params args
 *
 */

import java.util.Scanner;

public class ALittleQuiz {

    public static void main(String[] args) {

        int numRight = 0;
        int totalQuest = 3;

        System.out.println("What is the capital of Alaska?\n" +
                                    "1) Melbourne\n" +
                                    "2) Anchorage\n" +
                                    "3) Juneau");

        numRight += checkUserAnswer(3);

        System.out.println("Can you store the value 'cat' in a variable of type int?\n" +
                            "1)yes\n" +
                            "2)no");

        numRight += checkUserAnswer(2);

        System.out.println("What is the result of 9+6/3?\n" +
                            "1) 5\n" +
                            "2) 11\n" +
                            "3) 15/3");

        numRight += checkUserAnswer(2);

        System.out.println("Overall, you got " + numRight + " out of " + totalQuest + " correct.");

    }

    public static int checkUserAnswer(int answer) {
        Scanner scan = new Scanner(System.in);
        int userResponse = Integer.parseInt(scan.nextLine());
        if (userResponse == answer) {
            System.out.println("That's correct.");
            return 1;
        } else {
            System.out.println("Sorry. That is not correct.");
            return 0;
        }
    }

}
