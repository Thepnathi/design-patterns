// A principle for object oriented design which states that software entities
// (classes, modules, functions, etc.) should be open for extension, but closed for modification.

class Rectangle {
    public double Width {get; set;}
    public double Height {get; set}
}

class Circle {
    public double Radius {get; set}
}

class AreaCalculator {
    public double Area(Rectangle[] shapes) {
        double area = 0;
        foreach (var shape in shapes) {
            area += shape.Width * shape.Height;
        }
        return area
    }

    // Suppose the client wants us to be able to calculate Circle as well?
    public double Area2(object[] shapes) {
        double area = 0;
        foreach (var shape in shapes) {
            if (shape is Reactangle) {
                Rectangle rectangle = (Rectangle) shape;
                area += rectangle.Width * rectangle.Height;
            } else {
                Circle circle = (Circle) shape;
                area += circle.Radius * circle.Radius * Math.PI;
            }
        }
        return area;
    }
    // As you can see the AreaCalculator class requires us to modify the code.
    // It isn't closed for modification as we have to change in order to extend it.
    // Therefore, it is not open for extension

    // In a real word. The code can be in hundred or thousand. Thus, it can be big problem if
    // it has to be redeployed assembly/package to five different servers. 
}


