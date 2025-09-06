package menu;

import library.LibraryOperations;
import library.book.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

class UserMenu {
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
    private static final Consumer<LibraryOperations> info = lb -> {
        Cons.clearCons();
        System.out.println(lb.findUser(""));
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> borBook = lb -> {
        Cons.clearCons();
        System.out.println("Your borrowed books: ");
        List<Book> books = lb.getBorrowedBooks();
        for(Book book : books){
            System.out.println(book);
        }
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> overBook = lb -> {
        Cons.clearCons();
        System.out.println("Your overdue books: ");
        List<Book> books = lb.getOverdueBooks();
        for(Book book : books){
            System.out.println(book);
        }
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> ret = lb -> {
        Cons.clearCons();
        System.out.println("Enter isbn of book what you want return");
        String isbn = Cons.readString();
        if(lb.returnBook(isbn)) System.out.println("Success");
        else System.out.println("Error");
        System.out.println("Press any key");
        Cons.waite();
    };
    private static final Consumer<LibraryOperations> bor = lb -> {
        Cons.clearCons();
        System.out.println("Enter isbn of book what you want borrow");
        String isbn = Cons.readString();
        if(lb.borrowBook(isbn)) System.out.println("Success");
        else System.out.println("Error");
        System.out.println("Press any key");
        Cons.waite();
    };

    public static void MainMenu(LibraryOperations lb){
        Map<String,Menu> mm = new TreeMap<>();
        mm.put("Find book", new Menu("Find book", findBook));
        mm.put("Search books", new Menu("Search books", searchBooks));
        mm.put("My info", new Menu("My info", info));
        mm.put("My borrowed books", new Menu("My borrowed books", borBook));
        mm.put("My overdue books", new Menu("My overdue books", overBook));
        mm.put("Return books", new Menu("Return books", ret));
        mm.put("Boorow book", new Menu("Boorow book", bor));
        Menu mainMenu = new Menu("Main menu", mm);
        mainMenu.runMenu(lb);
    }
}
