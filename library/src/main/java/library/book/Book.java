package library.book;

import library.users.User;

public class Book {
    protected String title;
    protected String author;
    protected String isbn;
    protected String genre;

    private boolean isBorrowed;
    private String userId;

    public Book(String title, String author, String isbn, String genre) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;

        isBorrowed = false;
        userId = "";
    }

    public static Book load(String info){
        String[] comp = info.split(";");
        if(comp.length < 4) return null;
        Book book = new Book(comp[0], comp[1], comp[2], comp[3]);
        if(comp.length == 5){
            book.setBorrowed(true);
            book.setUserId(comp[4]);
        }
        return book;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(title).append(';')
                                    .append(author).append(';')
                                    .append(isbn).append(';')
                                    .append(genre);
        if(isBorrowed){
            str.append(';').append(userId);
        }
        return str.toString();
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
