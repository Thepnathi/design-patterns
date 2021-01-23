import java.util.HashMap;

// Demonstration of this pattern by drawing 20 curcles of different locations
// but we will create only 5 objects. Only 5 colours are available so color property is 
// used to check already existing Circle objects.

interface Shape {
    void draw();
}

class Circle implements Shape {
    private String color;
    private int x;
    private int y;
    private int radius;

    public Circle(String color) {
        this.color = color;
    }

    public void setX(int x) { this.x = x; }

    public void setY(int y) { this.y = y; }

    public void setRadius(int radius) { this.radius = radius; }

    @Override 
    public void draw() {
        System.out.println("Circle: Draw() [Color : " + color + ", x : " + x + ", y :" + y + ", radius :" + radius);
    }
}

// Creates a factory to generate object of concrete class based on the given information
class ShapeFactory {
    private static final HashMap circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle)circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}

// Use the factory to get object of concrete class by passing information such as color
public class Flyweight {
    private static final String colors[] = {"Red", "Green", "Blue", "White", "Black"};

    public static void main(String[] args) {

        for(int i=0; i < 20; ++i) {
           Circle circle = (Circle)ShapeFactory.getCircle(getRandomColor());
           circle.setX(getRandomX());
           circle.setY(getRandomY());
           circle.setRadius(100);
           circle.draw();
        }
     }
     private static String getRandomColor() {
        return colors[(int)(Math.random()*colors.length)];
     }
     private static int getRandomX() {
        return (int)(Math.random()*100 );
     }
     private static int getRandomY() {
        return (int)(Math.random()*100);
     }
}
