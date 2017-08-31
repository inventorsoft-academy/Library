package library.reprository.filerepro;

import library.model.Book;
import library.model.User;
import library.reprository.filerepro.FileManager;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//todo repository!
//todo: remove DB open to PostConstuct
public class DataBaseManager implements FileManager {
    //todo remove constants to app properties
    private static final String URL_DB = "jdbc:postgresql://localhost:5432/Library123";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";

    private List<Book> books;

    public DataBaseManager() {
        this.books = new ArrayList<>();
    }
//( 1 Long id, 2 String name, 3 String author, 4 String year, 5 String genre, 6 int quantity) {
//todo create update delete read  crud
    @Override
    public List<Book> getBooksList() {
        //todo MOVE:get connection to post construct
        try (Statement statement = (DriverManager.getConnection(URL_DB, USER, PASSWORD)).createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
            while (resultSet.next()) {
                Book book = new Book(resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(6),
                        resultSet.getInt(5));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println("Can't upload data!");
            return new ArrayList<>();
        }
        return books;
    }

    @Override
    public List<User> getUsersList() {
        return null;
    }

    @Override
    public void saveUsers(List<User> allUsersList) {

    }

    @Override
    public void saveBooks(List<Book> allBooksList) {

    }
}
