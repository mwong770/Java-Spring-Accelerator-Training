/**
 * Returns a day of the week or a day of the month corresponding to an inputted integer.
 */

package com.company;

public class ConverterSwitch implements Converter {

    public ConverterSwitch() {
    }

    // returns month
    public String convertMonth(int monthNumber) {
        switch (monthNumber) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "Error: You did not pick a number between 1 and 12.";
        }
    }

    // returns day of the week
    public String convertDay(int dayNumber) {
        switch (dayNumber) {
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
            default:
                return "Error: You did not pick a number between 1 and 7.";
        }
    }

}
