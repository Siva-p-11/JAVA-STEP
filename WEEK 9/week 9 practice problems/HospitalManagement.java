// File: Hospital.java

class Hospital {
    private String name;

    public Hospital(String name) {
        this.name = name;
    }

    // ✅ Member Inner Class
    public class Department {
        private String deptName;

        public Department(String deptName) {
            this.deptName = deptName;
        }

        // ✅ Display department info with hospital name
        public void displayInfo() {
            System.out.println("Hospital: " + name + ", Department: " + deptName);
        }
    }

    // ✅ Method to create Department object
    public Department createDepartment(String deptName) {
        return new Department(deptName);
    }
}

public class HospitalManagement {
    public static void main(String[] args) {
        // 1. Create Hospital
        Hospital hospital = new Hospital("Apollo Hospital");

        // 2. Create 2 Departments
        Hospital.Department dept1 = hospital.createDepartment("Cardiology");
        Hospital.Department dept2 = hospital.createDepartment("Neurology");

        // 3. Display info
        dept1.displayInfo();
        dept2.displayInfo();
    }
}
