package co.inventorsoft.academy.library.repository;

import co.inventorsoft.academy.library.model.Book;
import co.inventorsoft.academy.library.model.User;
import co.inventorsoft.academy.library.util.PropertiesApp;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataBaseRepository implements FileRepository {
    private Connection connection;

    @PostConstruct
    public void init() {
        try {
            connection = DriverManager.getConnection(PropertiesApp.URL_DB, PropertiesApp.USER, PropertiesApp.PASSWORD);
            Class.forName(PropertiesApp.DRIVER);
        } catch (SQLException | ClassNotFoundException e) {
            //todo handle exception (program not run(404))
            e.printStackTrace();
        }
    }

    //User
    @Override
    public void saveUsers(List<User> allUsersList) {
        List<User> users = getUsersList();
        allUsersList.removeAll(users);
        String saveUser = "INSERT INTO users (id, first_name, last_name, passport) VALUES(?,?,?,?);";
        try (PreparedStatement statement = connection.prepareStatement(saveUser)) {
            for (User user : allUsersList) {
                statement.setLong(1, user.getId());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getPassportNumber());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //todo handle exception (program not run(417 or 500))
        }
    }

    @Override
    public List<User> getUsersList() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor")) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));//string
                user.setLastName(resultSet.getString(3));//string
                user.setPassportNumber(resultSet.getString(4));//string
                users.add(user);
            }
        } catch (SQLException e) {
            //todo handle exception (continue(417 or 500))
        }
        return users;
    }

    public User getUserById(Long id) {
        List<User> users = getUsersList();
        for (User user : users) {
            if (id.equals(user.getId())) {
                return user;
            }
        }
        return new User();
    }

    public User updateUser(Long id, User user) {
        User newUser = getUserById(id);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPassportNumber(user.getPassportNumber());
        return newUser;
    }

    public User deleteUser(Long id) {
        User user = getUserById(id);
        StringBuilder deleteUser = new StringBuilder("DELETE FROM users WHERE id = ").append(id);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteUser.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            //todo handle exception (continue(417 or 500))
        }
        return user;
    }

    //Book
    @Override
    public void saveBooks(List<Book> allBooksList) {

    }

    @Override
    public List<Book> getBooksList() {
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM books")) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong(1));
                book.setIdUser(resultSet.getLong(2));
                book.setName(resultSet.getString(3));
                book.setAuthor(resultSet.getString(4));
                book.setYear(resultSet.getString(5));
                book.setQuantity(resultSet.getInt(6));
                book.setGenre(resultSet.getString(7));
                book.setRentStarts(LocalDateTime.parse(resultSet.getString(8)));
                book.setRentExpires(LocalDateTime.parse(resultSet.getString(9)));
                books.add(book);
            }
        } catch (SQLException e) {
            //todo handle exception (continue(417 or 500))
        }
        return books;
    }

    public Book getBookById(Long id) {
        List<Book> books = getBooksList();
        for (Book book : books) {
            if (id.equals(book.getId())) {
                return book;
            }
        }
        return new Book();
    }

    public Book updateBook(Long id, Book book) {
        Book newBook = getBookById(id);
        newBook.setIdUser(book.getIdUser());
        newBook.setRentStarts(book.getRentStarts());
        newBook.setRentExpires(book.getRentExpires());
        newBook.setQuantity(book.getQuantity());
        return newBook;
    }

    public Book deleteBook(Long id) {
        Book book = getBookById(id);
        StringBuilder deleteBook = new StringBuilder("DELETE FROM books WHERE id = ").append(id);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(deleteBook.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            //todo handle exception (continue(417 or 500))
        }
        return book;
    }

}
