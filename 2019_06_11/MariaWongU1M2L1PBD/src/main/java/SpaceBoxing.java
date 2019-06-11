/**
 * This app determines what a user's weight would be on a different planet.
 * Displays weight on a different planet based on the user's weight on Earth and the relative gravity of the target planet.
 * @params args
 *
 */

import java.util.Scanner;

public class SpaceBoxing {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter your current Earth weight, in pounds.");
        int earthWeight = Integer.parseInt(scan.nextLine());

        System.out.println("\nI have information on the following planets:\n" +
                            "1) Venus\n" +
                            "2) Mars\n" +
                            "3) Jupiter\n" +
                            "4) Saturn\n" +
                            "5) Uranus\n" +
                            "6) Neptune\n\n" +
                            "Which plant are you visiting (enter number)?");
        int planetNum = Integer.parseInt(scan.nextLine());

        float targetPlanetWeight = 0;

        boolean isPlanet = true;

        switch (planetNum) {
            case 1:
                targetPlanetWeight = earthWeight * 0.78f;
                break;
            case 2:
                targetPlanetWeight = earthWeight * 0.39f;
                break;
            case 3:
                targetPlanetWeight = earthWeight * 2.65f;
                break;
            case 4:
                targetPlanetWeight = earthWeight * 1.17f;
                break;
            case 5:
                targetPlanetWeight = earthWeight * 1.05f;
                break;
            case 6:
                targetPlanetWeight = earthWeight * 1.23f;
                break;
            default:
                System.out.println("You did not enter an existing planet number.");
                isPlanet = false;
        }

        if (isPlanet) {
            System.out.println("Your weight would be " + targetPlanetWeight + " pounds on that planet.");
        }
    }

}
