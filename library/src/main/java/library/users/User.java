package library.users;

import library.book.Book;
import library.book.BorrowedBook;
import library.data.Data;

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

    public static User load(String info){
        String[] comp = info.split(";");
        if(comp.length < 4) return null;
        User user;
        switch (UserType.valueOf(comp[3])){
            case GUEST -> user = new Guest(comp[0], comp[1], comp[2]);
            case STUDENT -> user = new Student(comp[0], comp[1], comp[2]);
            case FACULTY -> user = new Faculty(comp[0], comp[1], comp[2]);
            default -> throw new RuntimeException("Uncorrected userType:" + comp[3]);
        }
        for (int i = 4; i < comp.length; i+=3) {
            Book book = Data.books.get(comp[i]);
            if(book == null) throw new RuntimeException("Not found book: " + comp[i]);
            user.borrowedBooks.add(new BorrowedBook(book, comp[i+1], comp[i+2]));
        }
        return user;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(name).append(';')
                .append(userId).append(';')
                .append(email).append(';')
                .append(getUserType().toString());
        for(BorrowedBook book : borrowedBooks){
            str.append(';').append(book.getIsbn())
                    .append(';').append(book.getBorrowDay())
                    .append(';').append(book.getReturnDay());
        }
        return str.toString();
    }

    public abstract UserType getUserType();
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

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public void setBorrowedBooks(List<BorrowedBook> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
