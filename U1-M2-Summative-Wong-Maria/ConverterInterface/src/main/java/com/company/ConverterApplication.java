/**
 * Displays sentences about my Java training using classes ConverterIf and ConverterSwitch.
 */

package com.company;

public class ConverterApplication {

    public static void main(String[] args) {

        // creates instances of converter classes
        ConverterIf converterIf = new ConverterIf();
        ConverterSwitch converterSwitch = new ConverterSwitch();

        // uses converterIf and converterSwitch objects  to print months/days
        System.out.println("\nI have class " + converterIf.convertDay(2) +
                " to " + converterIf.convertDay(6) + ".");

        System.out.println("My days off are " + converterSwitch.convertDay(7) +
                " and " + converterSwitch.convertDay(1) + ".");


        System.out.println("\nMy Java training is from " + converterIf.convertMonth(6) +
                " to " + converterIf.convertMonth(8) + ".");

        System.out.println("I hope to begin working full-time in " + converterSwitch.convertMonth(9) + ".");

    }

}
