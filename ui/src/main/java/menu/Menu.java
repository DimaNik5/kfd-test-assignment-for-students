package menu;

import library.LibraryOperations;

import java.util.Map;
import java.util.function.Consumer;

class Menu {
    private Map<String, Menu> menu;
    private String title;

    private Consumer<LibraryOperations> run = null;

    public Menu(String title, Map<String, Menu> menu){
        this.title = title;
        this.menu = menu;
    }

    public Menu(String title, Consumer<LibraryOperations> run){
        this.title = title;
        this.run = run;
    }

    void runMenu(LibraryOperations lb){
        if(run != null) {
            run.accept(lb);
            return;
        }
        int offset = 0;
        while (true) {
            Cons.writeConsole(title, menu.keySet().stream().toList(), offset);
            int c = Cons.readConsole();

            if (c == 0) break;
            boolean up = offset > 0;
            boolean down = menu.size() - offset > 9 - (up ? 1 : 0);

            if (down && c == 9) {
                offset += 8 - (up ? 1 : 0);
                continue;
            }
            if (up && c == 1) {
                offset = Math.max(0, offset - 8 + (offset > 8 ? 1 : 0));
                continue;
            }

            int el = 9 - (up ? 1 : 0) - (down ? 1 : 0);
            int i = c - 1 - (up ? 1 : 0);

            if (i >= 0 && i < el && offset + i < menu.size()) {
                menu.values().stream().toList().get(offset + i).runMenu(lb);
            }
        }
    }
}
