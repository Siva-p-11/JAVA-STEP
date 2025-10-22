// File: LibraryCloneDemo.java
import java.util.*;

class Book implements Cloneable {
    String title;

    public Book(String title) {
        this.title = title;
    }

    @Override
    protected Book clone() throws CloneNotSupportedException {
        return (Book) super.clone();
    }

    @Override
    public String toString() {
        return title;
    }
}

class Library implements Cloneable {
    List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    // Shallow clone
    @Override
    protected Library clone() throws CloneNotSupportedException {
        return (Library) super.clone();
    }

    // Deep clone
    protected Library deepClone() throws CloneNotSupportedException {
        List<Book> copiedBooks = new ArrayList<>();
        for (Book b : books) {
            copiedBooks.add(b.clone());
        }
        return new Library(copiedBooks);
    }

    @Override
    public String toString() {
        return books.toString();
    }
}

public class LibraryCloneDemo {
    public static void main(String[] args) throws CloneNotSupportedException {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book("Java"));
        bookList.add(new Book("C++"));
        Library lib1 = new Library(bookList);

        Library shallow = lib1.clone();
        Library deep = lib1.deepClone();

        System.out.println("Original Library: " + lib1);
        System.out.println("Shallow Clone: " + shallow);
        System.out.println("Deep Clone: " + deep);

        // Modify one book
        lib1.books.get(0).title = "Python";

        System.out.println("\nAfter modification:");
        System.out.println("Original Library: " + lib1);
        System.out.println("Shallow Clone: " + shallow);
        System.out.println("Deep Clone: " + deep);
    }
}
