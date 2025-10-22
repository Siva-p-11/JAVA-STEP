// EmployeeDemo.java
// LAB PROBLEM 4: Abstract Employee and Payable Interface

abstract class Employee {
    protected String name;
    protected double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public abstract double calculateBonus();
}

interface Payable {
    void generatePaySlip();
}

class Manager extends Employee implements Payable {
    private String department;

    public Manager(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    @Override
    public double calculateBonus() {
        return salary * 0.20;  // 20% bonus
    }

    @Override
    public void generatePaySlip() {
        System.out.println("Pay Slip for Manager: " + name);
        System.out.println("Department: " + department);
        System.out.println("Base Salary: " + salary);
        System.out.println("Bonus: " + calculateBonus());
        System.out.println("Total Pay: " + (salary + calculateBonus()));
    }
}

public class EmployeeDemo {
    public static void main(String[] args) {
        Manager mgr = new Manager("Alice", 50000, "HR");
        mgr.generatePaySlip();
    }
}
