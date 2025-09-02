package library;

import library.book.Book;
import library.data.Data;
import library.users.User;
import library.users.UserType;

import java.util.ArrayList;
import java.util.List;

public interface LibraryOperations {
    default boolean addBook(String title, String author, String isbn, String genre){
        return false;
    }
    default boolean removeBook(String isbn){
        return false;
    }
    default Book findBook(String isbn){
        return Data.books.get(isbn);
    }
    default List<Book> searchBooks(String query){
        List<Book> b = new ArrayList<>();
        for (Book book : Data.books.values()){
            if(book.getTitle().contains(query)){
                b.add(book);
            }
        }
        return b;
    }
    default boolean registerUser(String name, String userId, String email, UserType type){
        return false;
    }

    User findUser(String userId);

    // Borrowing operations
    boolean borrowBook(String isbn);
    boolean returnBook(String isbn);
    List<Book> getOverdueBooks();
}
