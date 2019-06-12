package com.company;

public class AnalogClock implements Clock {

    public void displayTime() {
        System.out.println("Twelve O' Clock");
    }


    public void timer(int hour, int minute) {
        System.out.println("Hour: " + hour);
        System.out.println("Minute: " + minute);
    }

}
