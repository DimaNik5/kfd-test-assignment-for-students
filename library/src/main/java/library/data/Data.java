package library.data;

import library.book.Book;
import library.users.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public final class Data {
    public static Map<String, Book> books;
    public static Map<String, User> users;

    private final static String BOOK = "books";
    private final static String USER = "users";

    public static void load(String file){
        books = new HashMap<>();
        users = new HashMap<>();
        try(BufferedReader bf = new BufferedReader(new FileReader(file))){
            String line;
            int i = 0;
            while((line = bf.readLine()) != null){
                if(i == 0){
                    if(line.equals(BOOK)){
                        i++;
                        continue;
                    }
                    break;
                } else if (i == 1) {
                    if(line.equals(USER)){
                        i++;
                        continue;
                    }
                    Book book = Book.load(line);
                    if(book != null){
                        books.put(book.getIsbn(), book);
                    }
                }
                else {
                    User user = User.load(line);
                    if(user != null){
                        users.put(user.getUserId(), user);
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void save(String file){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            bw.write(BOOK);
            bw.write('\n');
            for (Book b : books.values()){
                bw.write(b.toString());
                bw.write('\n');
            }
            bw.write(USER);
            bw.write('\n');
            for(User user : users.values()){
                bw.write(user.toString());
                bw.write('\n');
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
