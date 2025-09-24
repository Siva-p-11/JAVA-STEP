class Book {
    private String bookId;
    private String title;
    private String author;
    private boolean isAvailable;
    private static int totalBooks = 0;
    private static int availableBooks = 0;
    private static int bookCounter = 0;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.bookId = generateBookId();
        this.isAvailable = true;
        totalBooks++;
        availableBooks++;
    }

    private static String generateBookId() {
        bookCounter++;
        return String.format("B%03d", bookCounter);
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getBookId() {
        return bookId;
    }

    public void issueBook() {
        if (isAvailable) {
            isAvailable = false;
            availableBooks--;
        }
    }

    public void returnBook() {
        if (!isAvailable) {
            isAvailable = true;
            availableBooks++;
        }
    }

    public void displayBookInfo() {
        System.out.println("Book ID   : " + bookId);
        System.out.println("Title     : " + title);
        System.out.println("Author    : " + author);
        System.out.println("Available : " + isAvailable);
        System.out.println("---------------------------");
    }

    public static int getTotalBooks() {
        return totalBooks;
    }

    public static int getAvailableBooks() {
        return availableBooks;
    }
}

class Member {
    private String memberId;
    private String memberName;
    private String[] booksIssued;
    private int bookCount;
    private static int memberCounter = 0;

    public Member(String name) {
        this.memberName = name;
        this.memberId = generateMemberId();
        this.booksIssued = new String[5];
        this.bookCount = 0;
    }

    private static String generateMemberId() {
        memberCounter++;
        return String.format("M%03d", memberCounter);
    }

    public void borrowBook(Book book) {
        if (book.isAvailable() && bookCount < booksIssued.length) {
            book.issueBook();
            booksIssued[bookCount] = book.getBookId();
            bookCount++;
        }
    }

    public void returnBook(String bookId, Book[] books) {
        for (int i = 0; i < bookCount; i++) {
            if (booksIssued[i].equals(bookId)) {
                for (int j = 0; j < books.length; j++) {
                    if (books[j].getBookId().equals(bookId)) {
                        books[j].returnBook();
                        booksIssued[i] = null;
                        shiftBooksIssuedLeft(i);
                        bookCount--;
                        break;
                    }
                }
                break;
            }
        }
    }

    private void shiftBooksIssuedLeft(int index) {
        for (int i = index; i < booksIssued.length - 1; i++) {
            booksIssued[i] = booksIssued[i + 1];
        }
        booksIssued[booksIssued.length - 1] = null;
    }

    public void displayMemberInfo() {
        System.out.println("Member ID   : " + memberId);
        System.out.println("Name        : " + memberName);
        System.out.print("Books Issued: ");
        for (int i = 0; i < bookCount; i++) {
            System.out.print(booksIssued[i] + " ");
        }
        System.out.println("\n---------------------------");
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Book[] books = new Book[3];
        books[0] = new Book("Java Programming", "James Gosling");
        books[1] = new Book("C++ Basics", "Bjarne Stroustrup");
        books[2] = new Book("Python for All", "Guido van Rossum");

        Member[] members = new Member[2];
        members[0] = new Member("Alice");
        members[1] = new Member("Bob");

        members[0].borrowBook(books[0]);
        members[0].borrowBook(books[1]);
        members[1].borrowBook(books[1]);
        members[1].borrowBook(books[2]);

        members[0].returnBook("B001", books);
        members[1].borrowBook(books[0]);

        for (int i = 0; i < books.length; i++) {
            books[i].displayBookInfo();
        }

        for (int i = 0; i < members.length; i++) {
            members[i].displayMemberInfo();
        }

        System.out.println("Total Books     : " + Book.getTotalBooks());
        System.out.println("Available Books : " + Book.getAvailableBooks());
    }
}
