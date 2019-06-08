package com.company;

public class App {

    public static int total(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
        }
        return sum;
    }

    public static int totalOdd(int[] arr) {
        int sum = 0;
        for (int i = 1; i < arr.length; i = i + 2) {
            sum = sum + arr[i];
        }
        return sum;
    }

    public static int totalEven(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i = i + 2) {
            sum = sum + arr[i];
        }
        return sum;
    }

    public static int secondLargestNumber(int[] arr) {

        int max;
        int secondMax;

        if (arr[0] > arr[1]) {
            max = arr[0];
            secondMax = arr[1];
        } else {
            max = arr[1];
            secondMax = arr[0];
        }

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            } else if (arr[i] > secondMax) {
                secondMax = arr[i];
            }
        }

        return secondMax;
    }

    public static String[] swapFirstAndLast(String[] arr) {
        String temp = arr[0];
        arr[0] = arr[arr.length - 1];
        arr[arr.length - 1] = temp;
        return arr;
    }

    public static int[] reverse(int[] arr) {
        int[] arrReversed = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrReversed[i] = arr[arr.length - 1 - i];
        }
        return arrReversed;
    }

    public static String concatenateString(String[] arr) {
        String combinedStrings = arr[0];
        for (int i = 1; i < arr.length; i++) {
            combinedStrings = combinedStrings + arr[i];
        }
        return combinedStrings;
    }

    public static int[] everyThird(int[] arr) {
        if (arr.length < 3) {
            return null;
        } else {
            int[] filteredArr = new int[arr.length / 3];
            int decrementVal = 0;
            for (int i = 2; i < arr.length; i = i + 3) {
                decrementVal += 2;
                filteredArr[i - decrementVal] = arr[i];
            }
            return filteredArr;
        }
    }

    public static int[] lessThanFive(int[] arr) {

        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 5) {
                counter++;
            }
        }

        int[] arrOf4OrLess = new int[counter];

        int indexArr2 = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 5) {
                arrOf4OrLess[indexArr2] = arr[i];
                indexArr2++;
            }
        }

        if (arrOf4OrLess.length == 0) {
            return null;
        } else {
            return arrOf4OrLess;
        }

    }

    // Returns a two-dimensional array, with values less than 5
    // at index 0 and values equal to or greater than 5 at index 1
    // ex. { {0, 1, 2, 3, 4}, {5, 6, 7} }
    public static int[][] splitAtFive(int[] arr) {

        int counter = 0;

        // counts number of values less than 5 in arr
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 5) {
                counter++;
            }
        }

        int[] arrOf4OrLess = new int[counter];
        int[] arrOf5OrMore = new int[arr.length - counter];

        int indexArrOf4OrLess = 0;
        int indexArrOf5OrMore = 0;

        // adds values to array arrOf4OrLess and arrOf5OrMore
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 5) {
                arrOf4OrLess[indexArrOf4OrLess] = arr[i];
                indexArrOf4OrLess++;
            } else {
                arrOf5OrMore[indexArrOf5OrMore] = arr[i];
                indexArrOf5OrMore++;
            }
        }

        int[][] test = new int[10][];
        test[0] = new int[100];
        test[1] = new int[500];

        int[][] combinedArr = new int[2][];
        combinedArr[0] = new int[arrOf4OrLess.length];
        combinedArr[1] = new int[arrOf5OrMore.length];

        // adds values to 2D array from 1D arrays arrOf4OrLess and arrOf5OrMore
        for (int i = 0; i < arrOf4OrLess.length; i++) {
            combinedArr[0][i] = arrOf4OrLess[i];
        }

        for (int i = 0; i < arrOf5OrMore.length; i++) {
            combinedArr[1][i] = arrOf5OrMore[i];
        }

        return combinedArr;

    }

    // Returns a two-dimensional array, with the values of
    // even-indices at index 0 and values of odd -indices at index 1
    // ex. if {"dog", "cat", "monkey"} returns {{"dog", "monkey"}, {"cat}}
    public static String[][] evensAndOdds(String[] arr) {

        int counter = 1;

        // counts number of even indices in arr
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) {
                counter++;
            }
        }

        String[] evenArr = new String[counter];
        String[] oddArr = new String[arr.length - counter];

        evenArr[0] = arr[0];
        int evenIdx = 1;
        int oddIdx = 0;

        // adds values to evenArr and oddArr from arr
        for (int i = 1; i < arr.length; i++) {
            if (i % 2 == 0) {
                evenArr[evenIdx] = arr[i];
                evenIdx++;
            } else {
                oddArr[oddIdx] = arr[i];
                oddIdx++;
            }
        }

        String[][] combinedArr = new String[2][];

        combinedArr[0] = new String[evenArr.length];
        combinedArr[1] = new String[oddArr.length];

        // adds values to 2D array from 1D arrays evenArr and oddArr
        for (int i = 0; i < evenArr.length; i++) {
            combinedArr[0][i] = evenArr[i];
        }

        for (int i = 0; i < oddArr.length; i++) {
            combinedArr[1][i] = oddArr[i];
        }

        return combinedArr;

    }

}
