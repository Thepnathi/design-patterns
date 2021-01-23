// • Lets you produce families of related objects without specifying their concrete class
// • The super-factory creates other factories


// Imagine you are creating a furniture shop. You code contains three types of furnitures: 
// chair, sofa and coffee table. Also there are three variants in Modern, Victorian and ArtDeco.
// You need a way to create the individual objects so that they match other objects of the same
// family.

// In this example, we can create a Factory of shapes depending on the type.

// ======================================================================

interface Shape {
    void draw();
}

// The concrete classes of different shapes
class RoundedRectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside RoundedRectangle");
    }
}

class RoundedSquare implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside RoundedSquare");
    }
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle");
    }
}

class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square");
    }
}

// The abstract class to get factories for Normal and Rounded shape objects
abstract class AbstractFactoryPart {
    abstract Shape getShape(String shapeType);
}

// Factory classes that extends the abstract and create concrete class based on given info
class ShapeFactory extends AbstractFactoryPart {
    @Override
    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        } else {
            return null;
        }
    }
}

// The Factory class for Rounded shapes
class RoundedShapeFactory extends AbstractFactoryPart {
    @Override 
    public Shape getShape(String shapeType) {
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new RoundedRectangle();
        } else if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new RoundedSquare();
        } else {
            return null;
        }
    }
}

// The Factory producer class to get factories by passing information such as Shape
class FactoryProducer {
    public static AbstractFactoryPart getFactory(boolean rounded) {
        if (rounded) {
            return new RoundedShapeFactory();
        } else {
            return new ShapeFactory();
        }
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        // Get the shape Factory
        AbstractFactoryPart shapeFactory = FactoryProducer.getFactory(false);

        Shape shape1 = shapeFactory.getShape("Rectangle");
        Shape shape2 = shapeFactory.getShape("SQUARE");

        shape1.draw();
        shape2.draw();
    }
} 