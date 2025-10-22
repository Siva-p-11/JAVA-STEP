// File: UniversityDemo.java
class University {
    String universityName = "ABC University";

    // Member inner class
    class Department {
        String deptName;

        Department(String deptName) {
            this.deptName = deptName;
        }

        void display() {
            System.out.println("Department: " + deptName + ", University: " + universityName);
        }
    }

    // Static nested class
    static class ExamCell {
        static void conductExam() {
            System.out.println("ExamCell: Conducting university exams...");
        }
    }
}

public class UniversityDemo {
    public static void main(String[] args) {
        University u = new University();

        // Access member inner class
        University.Department dept = u.new Department("Computer Science");
        dept.display();

        // Access static nested class
        University.ExamCell.conductExam();
    }
}
