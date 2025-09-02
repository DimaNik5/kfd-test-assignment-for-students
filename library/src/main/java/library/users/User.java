package library.users;

import library.book.Book;
import library.book.BorrowedBook;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class User {
    protected String name;
    protected String userId;
    protected String email;
    protected List<BorrowedBook> borrowedBooks;

    public User(String name, String userId, String email) {
        this.name = name;
        this.userId = userId;
        this.email = email;
        borrowedBooks = new ArrayList<>();
    }

    public abstract int getMaxBooks();
    public abstract int getBorrowDays();

    public boolean canBorrow() {
        return borrowedBooks.size() < getMaxBooks();
    }

    public boolean borrowBook(Book book){
        if(book == null) return false;
        if(canBorrow()){
            LocalDate today = LocalDate.now();
            LocalDate returnDay = LocalDate.now().plusDays(getBorrowDays());
            borrowedBooks.add(new BorrowedBook(book, today.toString(), returnDay.toString()));
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book){
        if(book == null) return false;
        for (BorrowedBook bb : borrowedBooks){
            if(bb.getIsbn().equals(book.getIsbn())){
                borrowedBooks.remove(bb);
                return true;
            }
        }
        return false;
    }

    public boolean returnBook(String isbn){
        if(isbn.isEmpty()) return false;
        for (BorrowedBook bb : borrowedBooks){
            if(bb.getIsbn().equals(isbn)){
                borrowedBooks.remove(bb);
                return true;
            }
        }
        return false;
    }

    public List<Book> getOverdueBooks(){
        List<Book> bb = new ArrayList<>();
        borrowedBooks.forEach(b -> {
            if(LocalDate.now().isAfter(LocalDate.parse(b.getReturnDay()))){
                bb.add(b);
            }
        });
        return bb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
