package com.company;

import java.util.Scanner;
import java.text.NumberFormat;
import java.text.DecimalFormat;

public class RectPavingCompany {

    public static void main(String[] args) {

        Scanner consoleScanner = new Scanner(System.in);
        NumberFormat formatter = new DecimalFormat("#0.00");

        System.out.println("Please enter the width of the driveway.");
        int width = Integer.parseInt(consoleScanner.nextLine());

        System.out.println("Please enter the length of the driveway.");
        int length = Integer.parseInt(consoleScanner.nextLine());

        int area = width * length;

        int perimeter = (width * 2) + (length * 2);

        System.out.println("Area: " + area);

        System.out.println("Perimeter: " + perimeter);

        float cementCost = area * 12.50f;

        float framingCost = perimeter * 8.25f;

        System.out.println("Cement Cost: " + formatter.format(cementCost));

        System.out.println("Framing Cost: " + formatter.format(framingCost));

        // Version 2

        System.out.println("Please enter the cost of the cement");
        float cementCostPerSqFt = Float.parseFloat(consoleScanner.nextLine());

        System.out.println("Please enter the cost of the framing");
        float framingCostPerLnFt = Float.parseFloat(consoleScanner.nextLine());

        float cementCostPerUserPrice = area * cementCostPerSqFt;

        float framingCostPerUserPrice = perimeter * framingCostPerLnFt;

        System.out.println("Cement Cost: " + formatter.format(cementCostPerUserPrice));

        System.out.println("Framing Cost: " + formatter.format(framingCostPerUserPrice));

    }
}
