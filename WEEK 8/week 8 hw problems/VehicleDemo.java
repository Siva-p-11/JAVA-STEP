// VehicleDemo.java

abstract class Vehicle {
    public abstract void start();

    public void stop() {
        System.out.println("Vehicle stopped.");
    }
}

interface Fuel {
    void refuel();
}

class Car extends Vehicle implements Fuel {
    @Override
    public void start() {
        System.out.println("Car engine started.");
    }

    @Override
    public void refuel() {
        System.out.println("Car is refueled with petrol.");
    }
}

public class VehicleDemo {
    public static void main(String[] args) {
        Car car = new Car();
        car.start();
        car.refuel();
        car.stop();
    }
}
