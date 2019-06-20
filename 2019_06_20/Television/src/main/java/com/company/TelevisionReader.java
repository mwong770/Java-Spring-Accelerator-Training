/**
 * Reads data from a JSON file, then
 * uses aggregate functions to filter and analyze the data.
 * Prints the results of the aggregate functions.
 *
 * @params args
 */

package com.company;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TelevisionReader {

    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        List<Television> tvList = null;

        // reads the television.json file and stores it's contents in a list
        try {
            tvList = mapper.readValue(new File("televisions.json"), new TypeReference<List<Television>>() {
            });
        } catch (IOException e) {
            System.out.println("ERROR: Problem encountered reading JSON file - " + e.getMessage());
        }

        System.out.println("\nTelevisions With a Screen Size Greater Than 60 Inches:\n");

        // finds and displays televisions with a screen size greater than 60 inches
        tvList
                .stream()
                .filter(t -> t.getScreenSize() > 60)
                .forEach(t -> {
                    System.out.println("Brand: " + t.getBrand());
                    System.out.println("Model: " + t.getModel());
                    System.out.println("Price: $" + t.getPrice());
                    System.out.println("Screen Size: " + t.getScreenSize());
                    System.out.println();
                });

        System.out.println("Television Brands:\n");


        // groups all televisions into a map by brand and prints out the brands
        Map<String, List<Television>> brandMap =
                tvList
                        .stream()
                        .collect(Collectors.groupingBy(t -> t.getBrand()));

        Set<String> tvKeys = brandMap.keySet();

        tvKeys.forEach(System.out::println);

        System.out.println();

        // finds and displays the average screen size
        double avgSize = tvList
                .stream()
                .mapToInt(t -> t.getScreenSize())
                .average()
                .getAsDouble();

        System.out.println("\nAverage Screen Size: " + avgSize);

        // finds and display the largest screen size
        int largestSize = tvList
                .stream()
                .mapToInt(t -> t.getScreenSize())
                .max()
                .getAsInt();

        System.out.println("\nLargest Screen Size: " + largestSize);

        System.out.println("\nTelevsions Sorted by Screen Size:\n");

        // sorts the data by screen size, then prints out the sorted list
        tvList
                .stream()
                .sorted((a, b) -> a.getScreenSize() - b.getScreenSize())
                .forEach(t -> {
                    System.out.println("Brand: " + t.getBrand());
                    System.out.println("Model: " + t.getModel());
                    System.out.println("Price: $" + t.getPrice());
                    System.out.println("Screen Size: " + t.getScreenSize());
                    System.out.println();
                });

    }

}
