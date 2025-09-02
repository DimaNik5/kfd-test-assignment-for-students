package library.users;

import library.LibraryOperations;
import library.book.Book;
import library.data.Data;

import java.util.ArrayList;
import java.util.List;

public final class AdminOperations implements LibraryOperations {
    @Override
    public boolean addBook(String title, String author, String isbn, String genre) {
        if(Data.books.get(isbn) != null) return false;
        Data.books.put(isbn, new Book(title, author, isbn, genre));
        return true;
    }

    @Override
    public boolean removeBook(String isbn) {
        Book book = Data.books.get(isbn);
        if(book != null && !book.isBorrowed()){
            Data.books.remove(isbn);
            return true;
        }
        return false;
    }

    @Override
    public boolean registerUser(String name, String userId, String email, UserType type) {
        if(Data.users.get(userId) != null) return false;
        User user;
        switch (type){
            case GUEST -> user = new Guest(name, userId, email);
            case STUDENT -> user = new Student(name, userId, email);
            case FACULTY -> user = new Faculty(name, userId, email);
            default -> throw new RuntimeException("Uncorrected UserType: " + type.toString());
        }
        Data.users.put(userId, user);
        return true;
    }

    @Override
    public User findUser(String userId) {
        return Data.users.get(userId);
    }

    @Override
    public boolean borrowBook(String isbn) {
        return false;
    }

    @Override
    public boolean returnBook(String isbn) {
        return false;
    }

    @Override
    public List<Book> getOverdueBooks() {
        List<Book> b = new ArrayList<>();
        for(Book book : Data.books.values()){
            if(book.isBorrowed()) b.add(book);
        }
        return b;
    }
}
