// File: StudentHashSetDemo.java
import java.util.*;

class Student {
    int rollNo;
    String name;

    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return rollNo == other.rollNo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNo);
    }

    @Override
    public String toString() {
        return "Student [RollNo=" + rollNo + ", Name=" + name + "]";
    }
}

public class StudentHashSetDemo {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();
        set.add(new Student(1, "Tamil"));
        set.add(new Student(2, "Selvan"));
        set.add(new Student(1, "Tamil")); // duplicate rollNo

        System.out.println("Students in HashSet:");
        for (Student s : set) {
            System.out.println(s);
        }
    }
}
