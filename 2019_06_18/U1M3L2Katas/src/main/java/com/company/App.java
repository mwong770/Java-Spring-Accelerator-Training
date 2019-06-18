package com.company;

import java.util.*;


public class App {

    public void printKeys(Map<String, String> stringMap) {

        Set<String> myKeys = stringMap.keySet();

        for(String key: myKeys) {
            System.out.println(key);
        }

    }

    public void printValues(Map<String, String> stringMap) {

        Collection<String> myValues = stringMap.values();

        for(String value: myValues) {
            System.out.println(value);
        }

    }

    public void printKeysAndValues(Map<String, String> stringMap) {

        for (Map.Entry<String, String> entry: stringMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

    }

    public Map mapFun(Map<String, Integer> intMap) {

        intMap.put("Ford Explorer", 2012);
        intMap.put("Smart Fortwo", 2013);
        intMap.remove("Jeep Wrangler");

        return intMap;

    }

    public Map listCars(ArrayList<Car> arr) {

        ArrayList<Car> toyotaList = new ArrayList<>();
        ArrayList<Car> fordList = new ArrayList<>();
        ArrayList<Car> hondaList = new ArrayList<>();

        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getMake() == "Toyota") {
                toyotaList.add(arr.get(i));
            } else if (arr.get(i).getMake() == "Ford") {
                fordList.add(arr.get(i));
            } else if (arr.get(i).getMake() == "Honda") {
                hondaList.add(arr.get(i));
            }
        }

        Map<String, ArrayList<Car>> cars = new HashMap<>();

        cars.put("Toyota", toyotaList);
        cars.put("Ford", fordList);
        cars.put("Honda", hondaList);

        return cars;

    }

}
