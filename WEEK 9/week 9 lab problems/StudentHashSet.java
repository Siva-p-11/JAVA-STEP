// File: StudentHashSet.java
import java.util.*;

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student other = (Student) obj;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Student [ID=" + id + ", Name=" + name + "]";
    }
}

public class StudentHashSet {
    public static void main(String[] args) {
        HashSet<Student> set = new HashSet<>();
        set.add(new Student(101, "Alice"));
        set.add(new Student(102, "Bob"));
        set.add(new Student(101, "Alice")); // duplicate id

        System.out.println("Students in HashSet:");
        for (Student s : set) {
            System.out.println(s);
        }
    }
}
