// File: PersonClone.java
class Address {
    String city;
    String country;

    public Address(String city, String country) {
        this.city = city;
        this.country = country;
    }

    @Override
    public String toString() {
        return city + ", " + country;
    }
}

class Person implements Cloneable {
    String name;
    Address address;

    public Person(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    // Shallow copy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    // Deep copy method
    protected Person deepClone() {
        return new Person(name, new Address(address.city, address.country));
    }

    @Override
    public String toString() {
        return "Person [Name=" + name + ", Address=" + address + "]";
    }
}

public class PersonClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        Address addr = new Address("Chennai", "India");
        Person p1 = new Person("Tamil", addr);

        // Shallow Clone
        Person shallow = (Person) p1.clone();
        // Deep Clone
        Person deep = p1.deepClone();

        System.out.println("Original: " + p1);
        System.out.println("Shallow Clone: " + shallow);
        System.out.println("Deep Clone: " + deep);

        // Modify address
        addr.city = "Bangalore";
        System.out.println("\nAfter modifying original address:");
        System.out.println("Original: " + p1);
        System.out.println("Shallow Clone: " + shallow);
        System.out.println("Deep Clone: " + deep);
    }
}
