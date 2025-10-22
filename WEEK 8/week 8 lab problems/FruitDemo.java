// FruitDemo.java
// LAB PROBLEM 1: Abstract Fruit + Edible Interface

// Abstract Class
abstract class Fruit {
    protected String color;
    protected String taste;

    // Constructor
    public Fruit(String color, String taste) {
        this.color = color;
        this.taste = taste;
    }

    // Abstract Method
    public abstract void showDetails();
}

// Interface
interface Edible {
    void nutrientsInfo();
}

// Subclass Apple (extends Fruit, implements Edible)
class Apple extends Fruit implements Edible {
    private String variety;

    public Apple(String color, String taste, String variety) {
        super(color, taste);
        this.variety = variety;
    }

    @Override
    public void showDetails() {
        System.out.println("Apple Variety: " + variety);
        System.out.println("Color: " + color);
        System.out.println("Taste: " + taste);
    }

    @Override
    public void nutrientsInfo() {
        System.out.println("Nutrients: Rich in fiber, Vitamin C, and antioxidants.");
    }
}

// Test Class
public class FruitDemo {
    public static void main(String[] args) {
        Apple apple = new Apple("Red", "Sweet", "Fuji");
        apple.showDetails();
        apple.nutrientsInfo();
    }
}
