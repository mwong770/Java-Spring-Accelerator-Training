/**
 *
 * Reads data from a CSV file using the OpenCSV library and stores its contents in a list.
 * Prints the contents of the list.
 * @params args
 *
 */

package com.company;

import com.opencsv.bean.CsvToBeanBuilder;
import java.io.*;
import java.util.List;

public class ComputerReader {

    public static void main(String[] args) {

        try {

            List<Computer> computers =
                    new CsvToBeanBuilder<Computer>(new FileReader("computers.csv")).withType(Computer.class).build().parse();

            for (Computer computer : computers) {
                System.out.println("=================================");
                System.out.println(computer.getBrand());
                System.out.println(computer.getModel());
                System.out.println(computer.getCPU());
                System.out.println(computer.getRAM());
                System.out.println(computer.getStorageSize());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Could not find CSV file: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("ERROR: Something went wrong writing your CSV file: " + e.getMessage());
        }

    }

}
