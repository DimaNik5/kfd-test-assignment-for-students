package library.users;

public class Guest extends User{
    public Guest(String name, String userId, String email) {
        super(name, userId, email);
    }

    @Override
    public int getMaxBooks() {
        return 1;
    }

    @Override
    public int getBorrowDays() {
        return 7;
    }
}
