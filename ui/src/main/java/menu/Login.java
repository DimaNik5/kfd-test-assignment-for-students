package menu;

import library.LibraryOperations;

import java.util.Scanner;

public class Login {

    public static void MainMenu(){
        LibraryOperations lb = null;
        while (lb == null){
            System.out.println("Enter your Id:");
            String id = (new Scanner(System.in)).nextLine();
            lb = LibraryOperations.login(id);
        }
        // Пользователь может получить информацию только о себе
        if (lb.findUser("") != null) UserMenu.MainMenu(lb);
        else AdminMenu.MainMenu(lb);
    }
}
