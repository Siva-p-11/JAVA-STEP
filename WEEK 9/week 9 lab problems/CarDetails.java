// File: CarDetails.java
class Car {
    private String brand;
    private String model;
    private double price;

    public Car(String brand, String model, double price) {
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car [Brand=" + brand + ", Model=" + model + ", Price=" + price + "]";
    }
}

public class CarDetails {
    public static void main(String[] args) {
        Car car = new Car("Tesla", "Model S", 95000.0);

        System.out.println("Car Details: " + car);  // invokes toString()
        System.out.println("Class Name: " + car.getClass().getName());
    }
}
