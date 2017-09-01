package co.inventorsoft.academy.library.repository;

import co.inventorsoft.academy.library.common.MyLogger;
import co.inventorsoft.academy.library.model.Book;
import co.inventorsoft.academy.library.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TXTFileRepository implements FileRepository {
    private static final MyLogger log = MyLogger.getCommonClass(TXTFileRepository.class);

    private static final String BOOKS_FILE_PATH = "src\\main\\resources\\Books.txt";

    private static final String USERS_FILE_PATH = "src\\main\\resources\\Users.txt";

@Override
     public List<User> getUsersList() {
         List<User> usersList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(USERS_FILE_PATH)))) {
                String line;
                String[] currentLine;
                while ((line = reader.readLine()) != null) {
                    currentLine = line.split("/");
                    usersList.add(new User(currentLine[0], currentLine[1], currentLine[2]));
                }
            } catch (IOException e) {
                log.error("FILE NOT FOUND!" +"\n"+e.getMessage());
            }
            log.info("Loaded users list from file");
            return usersList;
        }

    @Override
    public List<Book> getBooksList() {
         List<Book> booksList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(BOOKS_FILE_PATH)))) {
                String line;
                String[] currentLine;
                while ((line = reader.readLine()) != null) {
                    currentLine = line.split("/");
//                    booksList.add(new Book(Long.parseLong(currentLine[0]), currentLine[1], currentLine[2],
//                            currentLine[3],currentLine[4],Integer.parseInt(currentLine[5])));
                }
            } catch (IOException e) {
                log.error("FILE NOT FOUND!"+"\n"+e.getMessage());
            }
            log.info("Loaded books list from file");
            return booksList;
        }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return null;
    }

    @Override
    public Book deleteBook(Long id) {
        return null;
    }

    @Override
     public void saveUsers(List<User> users) {
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
            System.out.println("Users list saved!");
        } catch (IOException ex) {
            log.error("FILE NOT FOUND!"+"\n"+ex.getMessage());
        }
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        return null;
    }

    @Override
     public void saveBooks(List<Book> books) {
        File file = new File(BOOKS_FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            clearFile(file);
            for (Book book : books) {
                String string = book.getId()+
                        "/"+book.getName() +
                        "/" + book.getAuthor() +
                        "/" +book.getYear() +
                        "/" +book.getGenre()+
                        "/" +book.getQuantity();
                writer.append(string);
                writer.newLine();
                writer.flush();
            }
            log.info("Book's list saved!");
        } catch (IOException ex) {
            log.error("FILE NOT FOUND!"+"\n"+ex.getMessage());
        }
    }

private static void clearFile(File file) {
    try (PrintWriter writer = new PrintWriter(file)) {
        writer.print("");
    } catch (FileNotFoundException e) {
        log.error("FILE NOT FOUND!"+"\n"+e.getMessage());
    }
}

}
