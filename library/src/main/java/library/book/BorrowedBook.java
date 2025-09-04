package library.book;

public class BorrowedBook extends Book{
    private String borrowDay;
    private String returnDay;

    public BorrowedBook(String title, String author, String isbn, String genre) {
        super(title, author, isbn, genre);
    }

    public BorrowedBook(Book book, String borrowDay, String returnDay){
        super(book.title, book.author, book.isbn, book.genre);
        this.borrowDay = borrowDay;
        this.returnDay = returnDay;
    }

    public String getBorrowDay() {
        return borrowDay;
    }

    public void setBorrowDay(String borrowDay) {
        this.borrowDay = borrowDay;
    }

    public String getReturnDay() {
        return returnDay;
    }

    public void setReturnDay(String returnDay) {
        this.returnDay = returnDay;
    }
}
