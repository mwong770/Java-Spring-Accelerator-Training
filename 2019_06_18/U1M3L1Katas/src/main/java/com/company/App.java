package com.company;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static int total (ArrayList<Integer> arr) {

        int sum = 0;
        int num = 0;

        for (int i = 0; i < arr.size(); i++) {
            num = arr.get(i);
            sum += num;
        }

        return sum;

    }

    public static int totalEven (ArrayList<Integer> arr) {

        int sum = 0;

        for(int i = 0; i < arr.size(); i += 2) {
            sum += arr.get(i);
        }

        return sum;

    }

    public static List swapFirstAndLast(ArrayList<String> arr) {

        String temp = arr.get(0);

        arr.set(0, arr.get(arr.size()-1) );

        for (int i = 0; i<arr.size(); i++) {
            System.out.println(arr.get(i));
        }

        arr.set( (arr.size()-1), temp);

        return arr;

    }

    public static ArrayList reverse(ArrayList<Integer> arr) {

        ArrayList<Integer> reversed = new ArrayList<>();

        for(int i = 0; i < arr.size(); i++) {
            reversed.add(arr.get(arr.size()-1 -i));
        }

        return reversed;

    }

    public static ArrayList lessThanFive(ArrayList<Integer> arr) {

        ArrayList<Integer> lessThan = new ArrayList<>();

        for(int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < 5) {
                lessThan.add(arr.get(i));
            }
        }

        if (lessThan.size() > 0) {
            return lessThan;
        } else {
            return null;
        }

    }

    //challenge
    public static ArrayList splitAtFive(ArrayList<Integer> arr) {

        ArrayList<ArrayList<Integer>> numSplit  = new ArrayList<>();

        ArrayList<Integer> lessThan = new ArrayList<>();
        ArrayList<Integer> moreThan = new ArrayList<>();

        for(int i = 0; i < arr.size(); i++) {
            if (arr.get(i) < 5) {
                lessThan.add(arr.get(i));
            } else {
                moreThan.add(arr.get(i));
            }
        }

        numSplit.add(lessThan);
        numSplit.add(moreThan);

        return numSplit;

    }

    // Challenge
    public static ArrayList evensAndOdds(ArrayList<String> arr) {

        ArrayList<ArrayList<String>> evensAndOdds  = new ArrayList<>();

        ArrayList<String> odds = new ArrayList<>();
        ArrayList<String> evens = new ArrayList<>();

        for(int i = 0; i < arr.size(); i++) {
            if (i % 2 == 0) {
                evens.add(arr.get(i));
            } else {
                odds.add(arr.get(i));
            }
        }

        evensAndOdds.add(evens);
        evensAndOdds.add(odds);

        return evensAndOdds;

    }

}
