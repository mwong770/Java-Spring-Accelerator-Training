package com.company.concreteapproach;

public class Circle extends Shape {

    private double radius = 0;

    public double area() {
        return (double) (Math.PI * radius * radius);
    };

    public double perimeter() {
        return (double) (2 * Math.PI * radius);
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
