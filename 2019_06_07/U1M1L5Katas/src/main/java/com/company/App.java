package com.company;

public class App {

    public static int subtract(int a, int b){
        return a - b;
    }

    public static int subtractOrZero(int a, int b) {
        if (a - b > 0) {
            return a - b;
        } else {
            return 0;
        }
    }

    public static int max(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        } else if (b > a && b > c) {
            return b;
        } else {
            return c;
        }
    }

    public static int min(int a, int b, int c) {
        if (a < b && a < c) {
            return a;
        } else if (b < a && b < c) {
            return b;
        } else {
            return c;
        }
    }

    public static boolean isLarger(int first, int second) {
        return first > second;
    }

}
