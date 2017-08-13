package library.managment;

import library.model.Book;
import library.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class FileManager {



    private static final String BOOKS_FILE_PATH = "src\\resource\\Books.txt";

    private static final String USERS_FILE_PATH = "src\\resource\\Users.txt";

    private static List<User> usersList;
    private static List<Book> booksList;

     static List<User> getUsersList() {

        if (usersList != null) {
            System.out.println("Users list already exist");
            return usersList;
        } else {
            usersList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(USERS_FILE_PATH)))) {
                String line;
                String[] currentLine;
                while ((line = reader.readLine()) != null) {
                    currentLine = line.split("/");
                    usersList.add(new User(currentLine[0], currentLine[1],
                            currentLine[2]));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Load users list from file");
            return usersList;
        }
    }

     static List<Book> getBooksList() {
        if (booksList != null) {
            System.out.println("Books list already exist");
            return booksList;
        } else {
            booksList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(BOOKS_FILE_PATH)))) {
                String line;
                String[] currentLine;
                while ((line = reader.readLine()) != null) {
                    currentLine = line.split("/");
                    booksList.add(new Book(currentLine[0], currentLine[1], currentLine[2],
                            currentLine[3], Integer.parseInt(currentLine[4])));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            System.out.println("Load books list from file");
            return booksList;
        }
    }

    public static void saveUsers(List<User> users) {
        File file = new File(USERS_FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            clearFile(file);
            for (User user : users) {
                String string = user.getFirstName() +
                        "/" +
                        user.getLastName() +
                        "/" +
                        user.getPassportNumber();
                writer.append(string);
                writer.newLine();
                writer.flush();
            }
            System.out.println("Users list saved");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    public static void saveBooks(List<Book> books) {
        File file = new File(BOOKS_FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            clearFile(file);
            for (Book book : books) {
                String string = book.getName() +
                        "/" +
                        book.getAuthor() +
                        "/" +
                        book.getYear() +
                        "/" +
                        book.getGenre();
                writer.append(string);
                writer.newLine();
                writer.flush();
            }
            System.out.println("part list saved");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

private static void clearFile(File file) {
    try (PrintWriter writer = new PrintWriter(file)) {
        writer.print("");
        System.out.println("file: " + file.getName() + " clean");
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}

}
