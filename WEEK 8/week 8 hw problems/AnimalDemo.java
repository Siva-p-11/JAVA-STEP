// AnimalDemo.java

interface Animal {
    void eat();
}

interface Pet extends Animal {
    void play();
}

class Dog implements Pet {
    @Override
    public void eat() {
        System.out.println("Dog is eating food.");
    }

    @Override
    public void play() {
        System.out.println("Dog is playing fetch.");
    }
}

public class AnimalDemo {
    public static void main(String[] args) {
        Dog d = new Dog();
        d.eat();
        d.play();
    }
}
