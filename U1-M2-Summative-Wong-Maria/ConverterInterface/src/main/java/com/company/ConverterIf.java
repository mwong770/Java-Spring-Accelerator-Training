/**
 * Returns a day of the week or a day of the month corresponding to an inputted integer.
 */

package com.company;

public class ConverterIf implements Converter {

    public ConverterIf() {
    }

    // returns month
    public String convertMonth(int monthNumber) {
        if (monthNumber == 1) {
            return "January";
        } else if (monthNumber == 2) {
            return "February";
        } else if (monthNumber == 3) {
            return "March";
        } else if (monthNumber == 4) {
            return "April";
        } else if (monthNumber == 5) {
            return "May";
        } else if (monthNumber == 6) {
            return "June";
        } else if (monthNumber == 7) {
            return "July";
        } else if (monthNumber == 8) {
            return "August";
        } else if (monthNumber == 9) {
            return "September";
        } else if (monthNumber == 10) {
            return "October";
        } else if (monthNumber == 11) {
            return "November";
        } else if (monthNumber == 12) {
            return "December";
        } else {
            return "Error: You did not pick a number between 1 and 12.";
        }
    }

    // returns day of the week
    public String convertDay(int dayNumber) {
        if (dayNumber == 1) {
            return "Sunday";
        } else if (dayNumber == 2) {
            return "Monday";
        } else if (dayNumber == 3) {
            return "Tuesday";
        } else if (dayNumber == 4) {
            return "Wednesday";
        } else if (dayNumber == 5) {
            return "Thursday";
        } else if (dayNumber == 6) {
            return "Friday";
        } else if (dayNumber == 7) {
            return "Saturday";
        } else {
            return "Error: You did not pick a number between 1 and 7.";
        }
    }

}
