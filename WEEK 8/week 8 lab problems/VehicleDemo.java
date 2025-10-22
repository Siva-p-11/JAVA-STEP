// VehicleDemo.java
// LAB PROBLEM 3: Abstract Vehicle and Maintainable Interface

abstract class Vehicle {
    protected int speed;
    protected String fuelType;

    public Vehicle(int speed, String fuelType) {
        this.speed = speed;
        this.fuelType = fuelType;
    }

    public abstract void startEngine();
}

interface Maintainable {
    void serviceInfo();
}

class Car extends Vehicle implements Maintainable {
    private String model;

    public Car(int speed, String fuelType, String model) {
        super(speed, fuelType);
        this.model = model;
    }

    @Override
    public void startEngine() {
        System.out.println(model + " engine starts with key ignition.");
    }

    @Override
    public void serviceInfo() {
        System.out.println(model + " requires service every 10,000 km.");
    }

    public void showDetails() {
        System.out.println("Car Model: " + model + ", Speed: " + speed + " km/h, Fuel: " + fuelType);
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car(180, "Petrol", "Honda City");
        car.startEngine();
        car.serviceInfo();
        car.showDetails();
    }
}
