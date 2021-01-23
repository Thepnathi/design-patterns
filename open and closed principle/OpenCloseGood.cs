// A solution to solve the bad version and making it open and close principle

// One way is to create a base class for both rectangle and circle as well as any other shapes

public abstract class Shape {
    public abstract double Area();
}

public class Retangle : Shape {
    public double Width { get; set; }
    public double Height { get; set; }
    public override double Area() {
        return Width * Height;
    }
}

public class Circle : Shape {
    public double Radius { get; set; }
    public override double Area() {
        return Radius*Radius*Math.PI;
    }
}

public class Area {
    double area = 0;
    foreach (var shape in shapes) {
        area += shape.Area();
    }
    return area;
}


