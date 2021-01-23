// Example of open-close principle

interface IShape {
    double getArea();
}

abstract class Shapey implements IShape {
    protected abstract double calculateArea();

    public final double getArea() {
        return calculateArea();
    }
}

final class Rectangle extends Shapey {
    private double length;
    private double width;

    public Rectangle(double l, double w) {
        this.length = l;
        this.width = w;
    }
    // Make this new method final sice it will no longer be extended/overriden
    public final double getWidth() {
        return width;
    }

    // again, cannot be overriden since this is in a final class
    protected final double calculateArea() {
        return length * width;
    }
}

class Circle extends Shapey {
    private double radius;
    protected final double calculateArea() {
        return radius * radius * Math.PI;
    }

}

public class SebExample {
    public static void main(String[] args) {

    }
}
