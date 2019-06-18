/**
 *
 * Prints the contents of a list to a JSON file using the Jackson Data Binding API.
 * Reads and prints out the contents of a JSON file using the Jackson Data Binding API.
 * @params args
 *
 */

package com.company;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;


public class JSONWriteAndRead {

    public static void main(String[] args) {

        // create car list
        List<Car> carList = new ArrayList<>();

        carList.add(new Car(2012, "Toyota", "Camry", "Blue"));
        carList.add(new Car(2001, "Honda", "Civic", "Silver"));
        carList.add(new Car(2009, "Jeep", "Wrangler", "Rust"));
        carList.add(new Car(2018, "Tesla", "Roadster", "Black"));
        carList.add(new Car(1964, "Ford", "Mustang", "Red"));

        PrintWriter writer = null;

        try{

            // write to file
            ObjectMapper mapper = new ObjectMapper();

            String jsonCarList = mapper.writeValueAsString(carList);
            System.out.println(jsonCarList);

            writer = new PrintWriter(new FileWriter("cars.json"));
            writer.println(jsonCarList);

            writer.flush();
            writer.close();

            // read from file
            List<Car> newCarList;

            newCarList = mapper.readValue(new File("cars.json"), new TypeReference<List<Car>>() {
            });

            for (Car car: newCarList) {
                System.out.println("=================================");
                System.out.println(car.getYear());
                System.out.println(car.getMake());
                System.out.println(car.getModel());
                System.out.println(car.getColor());
            }

        } catch( JsonProcessingException e) {
            System.out.println("ERROR: Trouble converting object to JSON string: " + e.getMessage());
            e.printStackTrace();
        } catch(IOException e) {
            System.out.println("ERROR: Could not write to file: " + e.getMessage());
        }finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }

    }

}
