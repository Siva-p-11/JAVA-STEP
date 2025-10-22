// File: EmployeeAuth.java

import java.util.HashSet;
import java.util.Set;

class Employee {
    private String empCode;
    private String name;

    public Employee(String empCode, String name) {
        this.empCode = empCode;
        this.name = name;
    }

    // ✅ Override equals() - same empCode → same employee
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee other = (Employee) obj;
        return this.empCode.equals(other.empCode);
    }

    // ✅ Override hashCode() based only on empCode
    @Override
    public int hashCode() {
        return empCode.hashCode();
    }

    // ✅ toString() for displaying fields
    @Override
    public String toString() {
        return "Employee{empCode='" + empCode + "', name='" + name + "'}";
    }
}

public class EmployeeAuth {
    public static void main(String[] args) {
        // 1. Create employees
        Employee e1 = new Employee("BL001", "Ritika");
        Employee e2 = new Employee("BL001", "Ritika S.");

        // 2. Compare using '==' and equals()
        System.out.println("e1 == e2 ? " + (e1 == e2));       // false (different objects in memory)
        System.out.println("e1.equals(e2) ? " + e1.equals(e2)); // true (same empCode)

        // 3. Use in HashSet
        Set<Employee> employees = new HashSet<>();
        employees.add(e1);
        employees.add(e2);

        System.out.println("HashSet: " + employees);
        System.out.println("HashSet size: " + employees.size()); // should be 1 since e1.equals(e2)
    }
}
