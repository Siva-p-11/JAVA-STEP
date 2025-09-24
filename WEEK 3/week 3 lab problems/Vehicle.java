class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double rentPerDay;
    private boolean isAvailable;
    private int totalRentalDays;

    private static int totalVehicles = 0;
    private static double totalRevenue = 0;
    private static int totalRentalDaysAll = 0;
    private static String companyName = "Default Rentals";
    private static int vehicleCounter = 0;

    public Vehicle(String brand, String model, double rentPerDay) {
        this.brand = brand;
        this.model = model;
        this.rentPerDay = rentPerDay;
        this.isAvailable = true;
        this.totalRentalDays = 0;
        this.vehicleId = generateVehicleId();
        totalVehicles++;
    }

    private static String generateVehicleId() {
        vehicleCounter++;
        return String.format("V%03d", vehicleCounter);
    }

    public double rentVehicle(int days) {
        if (isAvailable && days > 0) {
            double rent = calculateRent(days);
            isAvailable = false;
            totalRentalDays += days;
            totalRentalDaysAll += days;
            return rent;
        }
        return 0;
    }

    public void returnVehicle() {
        isAvailable = true;
    }

    private double calculateRent(int days) {
        double rent = rentPerDay * days;
        totalRevenue += rent;
        return rent;
    }

    public void displayVehicleInfo() {
        System.out.println("Vehicle ID   : " + vehicleId);
        System.out.println("Brand        : " + brand);
        System.out.println("Model        : " + model);
        System.out.println("Rent/Day     : $" + rentPerDay);
        System.out.println("Available    : " + isAvailable);
        System.out.println("Days Rented  : " + totalRentalDays);
        System.out.println("-----------------------------");
    }

    public static void setCompanyName(String name) {
        companyName = name;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static double getAverageRentPerDay() {
        if (totalRentalDaysAll == 0) return 0;
        return totalRevenue / totalRentalDaysAll;
    }

    public static void displayCompanyStats() {
        System.out.println("Company Name       : " + companyName);
        System.out.println("Total Vehicles     : " + totalVehicles);
        System.out.println("Total Revenue      : $" + totalRevenue);
        System.out.println("Total Rental Days  : " + totalRentalDaysAll);
        System.out.println("Avg Rent per Day   : $" + getAverageRentPerDay());
        System.out.println("=============================");
    }
}

public class VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle.setCompanyName("AutoRent Solutions");

        Vehicle v1 = new Vehicle("Toyota", "Camry", 50);
        Vehicle v2 = new Vehicle("Honda", "Civic", 45);
        Vehicle v3 = new Vehicle("Ford", "Focus", 55);

        v1.rentVehicle(5);
        v2.rentVehicle(3);
        v1.returnVehicle();
        v1.rentVehicle(2);
        v3.rentVehicle(4);

        v1.displayVehicleInfo();
        v2.displayVehicleInfo();
        v3.displayVehicleInfo();

        Vehicle.displayCompanyStats();
    }
}
