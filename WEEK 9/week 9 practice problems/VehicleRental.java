// File: VehicleRental.java

class Vehicle {
    private String registrationNo;
    private String type;
    private double ratePerDay;

    // ✅ Constructor initializing all fields
    public Vehicle(String registrationNo, String type, double ratePerDay) {
        this.registrationNo = registrationNo;
        this.type = type;
        this.ratePerDay = ratePerDay;
    }

    // ✅ Override toString() method
    @Override
    public String toString() {
        return "Vehicle: " + registrationNo + 
               ", Type: " + type + 
               ", Rate: $" + ratePerDay + "/day";
    }

    // ✅ Getters for all fields
    public String getRegistrationNo() {
        return registrationNo;
    }

    public String getType() {
        return type;
    }

    public double getRatePerDay() {
        return ratePerDay;
    }
}

public class VehicleRental {
    public static void main(String[] args) {
        // 1. Create Vehicle object
        Vehicle v1 = new Vehicle("MH12AB1234", "Sedan", 1500);

        // 2. Print the Vehicle object (calls toString())
        System.out.println(v1);

        // 3. Create another vehicle
        Vehicle v2 = new Vehicle("MH14XY5678", "SUV", 2000);

        // Print second vehicle
        System.out.println(v2);

        // Compare (just simple example)
        if (v1.getRatePerDay() > v2.getRatePerDay()) {
            System.out.println(v1.getType() + " is costlier.");
        } else if (v1.getRatePerDay() < v2.getRatePerDay()) {
            System.out.println(v2.getType() + " is costlier.");
        } else {
            System.out.println("Both vehicles have the same rate.");
        }
    }
}
