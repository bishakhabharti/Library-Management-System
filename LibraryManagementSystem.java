import java.util.ArrayList;
import java.util.Scanner;

// Book Class
class Book {

    private String isbn;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book(String isbn, String title, String author, String genre) {

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = true;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean status) {
        available = status;
    }

    public void displayBook() {

        System.out.println("------------------------------");
        System.out.println("ISBN: " + isbn);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Status: " + (available ? "Available" : "Borrowed"));
    }
}

// Member Class
class Member {

    private String memberId;
    private String name;
    private String contact;
    private ArrayList<Book> borrowedBooks;

    public Member(String memberId, String name, String contact) {

        this.memberId = memberId;
        this.name = name;
        this.contact = contact;
        borrowedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public boolean borrowBook(Book book) {

        if (book.isAvailable()) {

            borrowedBooks.add(book);
            book.setAvailable(false);

            return true;
        }

        return false;
    }

    public boolean returnBook(Book book) {

        if (borrowedBooks.contains(book)) {

            borrowedBooks.remove(book);
            book.setAvailable(true);

            return true;
        }

        return false;
    }
}

// Library Class
class Library {

    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();

    public void addBook(Book book) {

        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void addMember(Member member) {

        members.add(member);
        System.out.println("Member registered successfully!");
    }

    public void displayBooks() {

        if (books.isEmpty()) {

            System.out.println("No books available.");
            return;
        }

        for (Book b : books)
            b.displayBook();
    }

    public void displayAvailableBooks() {

        boolean found = false;

        for (Book b : books) {

            if (b.isAvailable()) {

                b.displayBook();
                found = true;
            }
        }

        if (!found)
            System.out.println("No available books.");
    }

    public Book findBook(String isbn) {

        for (Book b : books) {

            if (b.getIsbn().equals(isbn))
                return b;
        }

        return null;
    }

    public Member findMember(String id) {

        for (Member m : members) {

            if (m.getMemberId().equals(id))
                return m;
        }

        return null;
    }

    public void searchBook(String keyword) {

        boolean found = false;

        for (Book b : books) {

            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase())
                    || b.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {

                b.displayBook();
                found = true;
            }
        }

        if (!found)
            System.out.println("No book found.");
    }

}

// Main Class
public class LibraryManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Library library = new Library();

        int choice;

        do {

            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");

            System.out.println("1. Add Book");
            System.out.println("2. Register Member");
            System.out.println("3. Display All Books");
            System.out.println("4. Display Available Books");
            System.out.println("5. Search Book");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter ISBN: ");
                    String isbn = sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    System.out.print("Enter Genre: ");
                    String genre = sc.nextLine();

                    library.addBook(new Book(isbn, title, author, genre));

                    break;

                case 2:

                    System.out.print("Enter Member ID: ");
                    String id = sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();

                    library.addMember(new Member(id, name, contact));

                    break;

                case 3:

                    library.displayBooks();
                    break;

                case 4:

                    library.displayAvailableBooks();
                    break;

                case 5:

                    System.out.print("Enter keyword: ");
                    String keyword = sc.nextLine();

                    library.searchBook(keyword);
                    break;

                case 6:

                    System.out.print("Enter Member ID: ");
                    String memberId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    String bookIsbn = sc.nextLine();

                    Member member = library.findMember(memberId);
                    Book book = library.findBook(bookIsbn);

                    if (member != null && book != null && member.borrowBook(book))

                        System.out.println("Book borrowed successfully!");

                    else

                        System.out.println("Borrow failed.");

                    break;

                case 7:

                    System.out.print("Enter Member ID: ");
                    memberId = sc.nextLine();

                    System.out.print("Enter Book ISBN: ");
                    bookIsbn = sc.nextLine();

                    member = library.findMember(memberId);
                    book = library.findBook(bookIsbn);

                    if (member != null && book != null && member.returnBook(book))

                        System.out.println("Book returned successfully!");

                    else

                        System.out.println("Return failed.");

                    break;

                case 8:

                    System.out.println("Exiting...");
                    break;
            }

        } while (choice != 8);

    }

}
