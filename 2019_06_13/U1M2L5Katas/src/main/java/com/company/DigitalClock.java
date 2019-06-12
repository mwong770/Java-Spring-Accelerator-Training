package com.company;

public class DigitalClock implements Clock, Alarm {

    public void displayTime() {
        System.out.println("12:00");
    }

    public void timer(int hour, int minute) {
        System.out.println("Timer: " + hour + ":" + minute);
    }

    public void sound() {
        System.out.println("Bang, Bang, Bang");
    }
}
