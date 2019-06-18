package com.company;

import java.util.LinkedList;
import java.util.List;

public class LinkedListApp {

    public static int total (LinkedList<Integer> arr) {

        int sum = 0;
        int num = 0;

        for (int i = 0; i < arr.size(); i++) {
            num = arr.get(i);
            sum += num;
        }

        return sum;

    }

    public static int totalEven (LinkedList<Integer> arr) {

        int sum = 0;

        for(int i = 0; i < arr.size(); i += 2) {
            sum += arr.get(i);
        }

        return sum;

    }

    public static List swapFirstAndLast(LinkedList<String> arr) {

        String temp = arr.get(0);

        arr.set(0, arr.get(arr.size()-1) );

        for (int i = 0; i<arr.size(); i++) {
            System.out.println(arr.get(i));
        }

        arr.set( (arr.size()-1), temp);

        return arr;

    }

    public static LinkedList reverse(LinkedList<Integer> arr) {

        LinkedList<Integer> reversed = new LinkedList<>();

        for(int i = 0; i < arr.size(); i++) {
            reversed.add(arr.get(arr.size()-1 -i));
        }

        return reversed;

    }

    public static LinkedList lessThanFive(LinkedList<Integer> arr) {

        LinkedList<Integer> lessThan = new LinkedList<>();

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
    public static LinkedList splitAtFive(LinkedList<Integer> arr) {

        LinkedList<LinkedList<Integer>> numSplit  = new LinkedList<>();

        LinkedList<Integer> lessThan = new LinkedList<>();
        LinkedList<Integer> moreThan = new LinkedList<>();

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
    public static LinkedList evensAndOdds(LinkedList<String> arr) {

        LinkedList<LinkedList<String>> evensAndOdds  = new LinkedList<>();

        LinkedList<String> odds = new LinkedList<>();
        LinkedList<String> evens = new LinkedList<>();

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
