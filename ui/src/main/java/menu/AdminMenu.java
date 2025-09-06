package menu;

import library.LibraryOperations;
import library.book.Book;
import library.users.UserType;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class AdminMenu {

    private static final Consumer<LibraryOperations> addBook = lb -> {
        Cons.clearCons();
        System.out.println("Enter title of book: ");
        String title = Cons.readString();
        System.out.println("Enter isbn of book: ");
        String isbn = Cons.readString();
        System.out.println("Enter author of book: ");
        String author = Cons.readString();
        System.out.println("Enter genre of book: ");
        String genre = Cons.readString();
        if(lb.addBook(title, author, isbn, genre)) System.out.println("Success");
        else System.out.println("Error");
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> removeBook = lb -> {
        Cons.clearCons();
        System.out.println("Enter isbn of book for remove: ");
        String isbn = Cons.readString();
        if(lb.removeBook(isbn)) System.out.println("Success");
        else System.out.println("Error");
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> findUser = lb -> {
        Cons.clearCons();
        System.out.println("Enter id of user: ");
        String id = Cons.readString();
        System.out.println(lb.findUser(id));
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> regUser = lb -> {
        Cons.clearCons();
        System.out.println("Enter name of user: ");
        String name = Cons.readString();
        System.out.println("Enter id of user: ");
        String id = Cons.readString();
        System.out.println("Enter email of user: ");
        String email = Cons.readString();
        System.out.println("Enter type of user: ");
        String type = Cons.readString();
        if(lb.registerUser(name, id, email, UserType.valueOf(type))) System.out.println("Success");
        else System.out.println("Error");
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> findBook = lb -> {
        Cons.clearCons();
        System.out.println("Enter isbn of book: ");
        String isbn = Cons.readString();
        System.out.println(lb.findBook(isbn));
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> searchBooks = lb -> {
        Cons.clearCons();
        System.out.println("Enter name of book: ");
        String name = Cons.readString();
        List<Book> books = lb.searchBooks(name);
        for(Book book : books){
            System.out.println(book);
        }
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> borBook = lb -> {
        Cons.clearCons();
        System.out.println("Borrowed books: ");
        List<Book> books = lb.getBorrowedBooks();
        for(Book book : books){
            System.out.println(book);
        }
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> overBook = lb -> {
        Cons.clearCons();
        System.out.println("Overdue books: ");
        List<Book> books = lb.getOverdueBooks();
        for(Book book : books){
            System.out.println(book);
        }
        System.out.println("Press any key");
        Cons.waite();
    };

    public static void MainMenu(LibraryOperations lb){
        Map<String, Menu> mm = new TreeMap<>();
        mm.put("Add book", new Menu("Add book", addBook));
        mm.put("Remove book", new Menu("Remove book", removeBook));
        mm.put("Find user", new Menu("Find user", findUser));
        mm.put("Register user", new Menu("Register user", regUser));
        mm.put("Find book", new Menu("Find book", findBook));
        mm.put("Search books", new Menu("Search books", searchBooks));
        mm.put("Borrowed books", new Menu("Borrowed books", borBook));
        mm.put("Overdue books", new Menu("Overdue books", overBook));
        Menu mainMenu = new Menu("Main menu", mm);
        mainMenu.runMenu(lb);
    }
}
