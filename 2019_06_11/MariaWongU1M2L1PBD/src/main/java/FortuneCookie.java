/**
 * This is a random fortune and lottery number generator.
 * Displays the randomly selected fortune and lottery numbers.
 * @params args
 *
 */

import java.util.Random;

public class FortuneCookie {

    public static void main(String[] args) {

        String[] fortunes = {"The fortune you seek is in another cookie.",
                             "A closed mouth gathers no feet.",
                             "A cynic is only a frustrated optimist.",
                             "You will die alone and poorly dressed.",
                             "Flattery will go far tonight.",
                             "He who throws dirt is losing ground.",
                             "He who laughs last is laughing at you.",
                             "That wasn't chicken.",
                             "I am worth a fortune.",
                             "You will receive a fortune cookie."};

        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(10);

        System.out.println("Fortune cookie says: \"" + fortunes[randomInt] + "\"");

        // creates lotto of 6 numbers, each between 1 and 54
        String lottoCombined = Integer.toString(randomGenerator.nextInt(54) + 1);
        int randomLottoNum;

        for (int i = 0; i < 5; i++) {
            randomLottoNum = randomGenerator.nextInt(54) + 1;
            lottoCombined += " - " + randomLottoNum;
        }

        System.out.println("     " + lottoCombined);

    }
}
