// File: Registration.java

class ContactInfo implements Cloneable {
    String email;
    String phone;

    public ContactInfo(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    // ✅ Shallow copy clone
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); 
    }

    @Override
    public String toString() {
        return "ContactInfo{email='" + email + "', phone='" + phone + "'}";
    }
}

class Student implements Cloneable {
    String id;
    String name;
    ContactInfo contact;

    public Student(String id, String name, ContactInfo contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    // ✅ Shallow copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();  // ContactInfo reference is shared
    }

    // ✅ Deep copy
    protected Student deepClone() throws CloneNotSupportedException {
        Student cloned = (Student) super.clone();
        cloned.contact = (ContactInfo) contact.clone(); // create new ContactInfo
        return cloned;
    }

    @Override
    public String toString() {
        return "Student{id='" + id + "', name='" + name + "', contact=" + contact + "}";
    }
}

public class Registration {
    public static void main(String[] args) throws CloneNotSupportedException {
        // 1. Create a student with contact info
        ContactInfo c1 = new ContactInfo("alice@example.com", "9999999999");
        Student s1 = new Student("S101", "Alice", c1);

        // 2. Clone (shallow copy)
        Student s2 = (Student) s1.clone();

        // 3. Clone (deep copy)
        Student s3 = s1.deepClone();

        System.out.println("Before modification:");
        System.out.println("Original: " + s1);
        System.out.println("Shallow : " + s2);
        System.out.println("Deep    : " + s3);

        // 4. Modify contact info of original
        s1.contact.email = "alice_new@example.com";

        System.out.println("\nAfter modifying s1.contact.email:");
        System.out.println("Original: " + s1);
        System.out.println("Shallow : " + s2); // Affected (shares same ContactInfo)
        System.out.println("Deep    : " + s3); // Unaffected (has its own ContactInfo)
    }
}
