/**
 *
 * Prints the contents of a list to an XML file using the Jackson Data Format XML extension.
 * Reads and prints out the contents of a XML file using the Jackson Data Format XML extension.
 * @params args
 *
 */

package com.company;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class XMLWriteAndRead {

    public static void main(String[] args) {

        // creates car list
        List<Car> carList = new ArrayList<>();

        carList.add(new Car(2000, "Toyota", "Camry", "Blue"));
        carList.add(new Car(2001, "Honda", "Civic", "Silver"));
        carList.add(new Car(2009, "Jeep", "Wrangler", "Rust"));
        carList.add(new Car(2018, "Tesla", "Roadster", "Black"));
        carList.add(new Car(1964, "Ford", "Mustang", "Red"));

        PrintWriter writer = null;

        try{

            // writes to file
            ObjectMapper mapper = new XmlMapper();

            String jsonCarList = mapper.writeValueAsString(carList);
            System.out.println(jsonCarList);

            writer = new PrintWriter(new FileWriter("cars.xml"));
            writer.println(jsonCarList);

            writer.flush();
            writer.close();

            // reads from file
            List<Car> newCarList;

            newCarList = mapper.readValue(new File("cars.xml"), new TypeReference<List<Car>>() {
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
