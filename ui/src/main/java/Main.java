import library.LibraryOperations;
import menu.Login;

public class Main {
    public static void main(String[] args) {
        LibraryOperations.loadData("data.dat");
        Login.MainMenu();
        LibraryOperations.saveData("data.dat");
    }
}
