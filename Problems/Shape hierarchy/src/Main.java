abstract class Shape {
    abstract double getPerimeter();
    abstract double getArea();
}

class Triangle extends Shape {
    protected double side1;
    protected double side2;
    protected double side3;

    public Triangle(double side1, double side2, double side3) {
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    @Override
    double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    double getArea() {
        double sp = getPerimeter() / 2.0;
        return Math.sqrt(sp * (sp - side1) * (sp - side2) * (sp - side3));
    }
}

class Rectangle extends Shape {
    protected double length;
    protected double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    @Override
    double getPerimeter() {
        return (length + width) * 2.0;
    }

    @Override
    double getArea() {
        return length * width;
    }
}

class Circle extends Shape {
    protected double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double getPerimeter() {
        return 2.0 * Math.PI * radius;
    }

    @Override
    double getArea() {
        return Math.PI * radius * radius;
    }
}