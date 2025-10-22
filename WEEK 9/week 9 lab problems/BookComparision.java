// File: BookComparison.java
class Book {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Book other = (Book) obj;
        return title.equals(other.title) && author.equals(other.author);
    }
}

public class BookComparison {
    public static void main(String[] args) {
        Book b1 = new Book("Java Programming", "James Gosling");
        Book b2 = new Book("Java Programming", "James Gosling");
        Book b3 = b1; // same reference

        System.out.println("b1 == b2 : " + (b1 == b2));          // false (different objects)
        System.out.println("b1.equals(b2) : " + b1.equals(b2));  // true (same content)
        System.out.println("b1 == b3 : " + (b1 == b3));          // true (same reference)
    }
}
