class Employee {
    private String empId;
    private String empName;
    private String department;
    private double baseSalary;
    private String empType;
    private static int totalEmployees = 0;
    private static int empCounter = 0;

    public Employee(String name, String department, double baseSalary) {
        this.empName = name;
        this.department = department;
        this.baseSalary = baseSalary;
        this.empType = "Full-Time";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public Employee(String name, String department, double hourlyRate, int hoursWorked) {
        this.empName = name;
        this.department = department;
        this.baseSalary = hourlyRate * hoursWorked;
        this.empType = "Part-Time";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    public Employee(String name, String department, double contractAmount, boolean isContract) {
        this.empName = name;
        this.department = department;
        this.baseSalary = contractAmount;
        this.empType = "Contract";
        this.empId = generateEmpId();
        totalEmployees++;
    }

    private static String generateEmpId() {
        empCounter++;
        return String.format("EMP%03d", empCounter);
    }

    public double calculateSalary() {
        if (empType.equals("Full-Time")) {
            return baseSalary + 2000;
        } else if (empType.equals("Part-Time")) {
            return baseSalary;
        } else {
            return baseSalary;
        }
    }

    public double calculateTax() {
        if (empType.equals("Full-Time")) {
            return calculateSalary() * 0.2;
        } else if (empType.equals("Part-Time")) {
            return calculateSalary() * 0.1;
        } else {
            return calculateSalary() * 0.05;
        }
    }

    public void generatePaySlip() {
        System.out.println("Employee ID    : " + empId);
        System.out.println("Name           : " + empName);
        System.out.println("Department     : " + department);
        System.out.println("Type           : " + empType);
        System.out.println("Gross Salary   : $" + calculateSalary());
        System.out.println("Tax Deduction  : $" + calculateTax());
        System.out.println("Net Salary     : $" + (calculateSalary() - calculateTax()));
        System.out.println("------------------------------");
    }

    public void displayEmployeeInfo() {
        System.out.println("ID   : " + empId);
        System.out.println("Name : " + empName);
        System.out.println("Dept : " + department);
        System.out.println("Type : " + empType);
        System.out.println("------------------------------");
    }

    public static int getTotalEmployees() {
        return totalEmployees;
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        Employee[] employees = new Employee[3];

        employees[0] = new Employee("Alice", "HR", 50000);
        employees[1] = new Employee("Bob", "IT", 200, 100);
        employees[2] = new Employee("Charlie", "Finance", 60000, true);

        for (int i = 0; i < employees.length; i++) {
            employees[i].displayEmployeeInfo();
            employees[i].generatePaySlip();
        }

        System.out.println("Total Employees: " + Employee.getTotalEmployees());
    }
}
