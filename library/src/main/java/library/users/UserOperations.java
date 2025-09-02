package library.users;

import library.LibraryOperations;
import library.book.Book;

import java.util.List;

public final class UserOperations implements LibraryOperations {

    private User user;

    public UserOperations(User user) {
        this.user = user;
    }

    @Override
    public User findUser(String userId) {
        return user;
    }

    @Override
    public boolean borrowBook(String isbn) {
        if(!user.canBorrow()) return false;
        Book book = findBook(isbn);
        if(book.isBorrowed()) return false;

        if(user.borrowBook(book)){
            book.setBorrowed(true);
            book.setUser(user);
            return true;
        }

        return false;
    }

    @Override
    public boolean returnBook(String isbn) {
        if(user.returnBook(isbn)){
            findBook(isbn).setBorrowed(false);
            return true;
        }
        return false;
    }

    @Override
    public List<Book> getOverdueBooks() {
        return user.getOverdueBooks();
    }
}
