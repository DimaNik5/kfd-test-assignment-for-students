package menu;

import java.util.List;
import java.util.Scanner;

import static java.lang.Math.min;

class Cons {

    private final static String CLR = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";

    private final static Scanner scanner = new Scanner(System.in);

    public static int readConsole(){
        return scanner.nextByte();
    }

    public static void waite(){
        if(scanner.hasNext()){
            scanner.next();
        }
    }

    public static String readString(){
       // if(scanner.hasNext()) {}
        return scanner.next();
    }

    public static void writeConsole(String title, List<String> list, int offset) {
        clearCons();
        System.out.println(title);
        boolean up = offset > 0;
        boolean down = list.size() - offset > 9 - (up ? 1 : 0);
        if (up) System.out.println("1. Up");

        int el = 9 - (up ? 1 : 0) - (down ? 1 : 0);
        for (int i = 0; i < el && offset + i < list.size(); i++) {
            int j = i + 1 + (up ? 1 : 0);
            System.out.println(j + ". " + list.get(offset + i));
        }
        if (down) System.out.println("9. Down");
        System.out.println("0. Return");
    }

    public static void clearCons(){
        System.out.println(CLR);
    }

}
