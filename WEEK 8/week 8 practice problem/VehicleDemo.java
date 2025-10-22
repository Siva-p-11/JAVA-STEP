// VehicleDemo.java
// Problem 1: Vehicle with Abstract Class in a single file

abstract class Vehicle {
    // Abstract method
    public abstract void start();

    // Non-abstract method
    public void fuelType() {
        System.out.println("Uses fuel");
    }
}

class Car extends Vehicle {
    @Override
    public void start() {
        System.out.println("Car starts with key");
    }
}

class Bike extends Vehicle {
    @Override
    public void start() {
        System.out.println("Bike starts with kick");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        // Vehicle reference pointing to Car
        Vehicle v1 = new Car();
        v1.start();
        v1.fuelType();

        // Vehicle reference pointing to Bike
        Vehicle v2 = new Bike();
        v2.start();
        v2.fuelType();
    }
}
