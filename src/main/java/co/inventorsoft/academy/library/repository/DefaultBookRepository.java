package co.inventorsoft.academy.library.repository;

import co.inventorsoft.academy.library.common.MyLogger;
import co.inventorsoft.academy.library.common.custom_exception.DBConnectionException;
import co.inventorsoft.academy.library.common.custom_exception.DBWorkException;
import co.inventorsoft.academy.library.model.Book;
import co.inventorsoft.academy.library.util.AppConstants;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultBookRepository implements BookRepository {
    private Connection connection;
    private MyLogger log = new MyLogger();

    @PostConstruct
    private void init() throws DBConnectionException{
        try {
            connection = DriverManager.getConnection(AppConstants.URL_DB, AppConstants.USER, AppConstants.PASSWORD);
            Class.forName(AppConstants.DRIVER);
        } catch (SQLException | ClassNotFoundException e) {
            log.error(e.getMessage());
            throw new DBConnectionException("Error connecting to data base",e.fillInStackTrace());
        }
    }

    @Override
    public boolean createBooks(Book book) throws DBWorkException{
        try {
            PreparedStatement statement = connection
                    .prepareStatement(AppConstants.CREATE_BOOK);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getYear());
            statement.setString(4, book.getGenre());
            statement.execute();
            statement.close();
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBConnectionException("Error create book",e.fillInStackTrace());
        }
    }

    @Override
    public List<Book> readBooks() throws DBWorkException{
        List<Book> books = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(AppConstants.READ_BOOKS)) {
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setIdUser(resultSet.getLong("user_id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                book.setYear(resultSet.getString("year"));
                book.setGenre(resultSet.getString("genre"));
                book.setRentStarts(resultSet.getString("rent_start"));
                book.setRentExpires(resultSet.getString("rent_end"));
                books.add(book);
            }
            statement.close();
            log.info(statement.toString());
            return books;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBConnectionException("Error read book",e.fillInStackTrace());
        }
    }

    public Book readBookById(Long id) throws DBWorkException{
        List<Book> books = readBooks();
        for (Book book : books) {
            if (id.equals(book.getId())) {
                return book;
            }
        }
        return null;
    }

    public boolean updateBook(Long id, Book book) throws DBWorkException{
        try {
            PreparedStatement statement = connection
                    .prepareStatement(AppConstants.UPDATE_BOOK);
            statement.setLong(1, book.getIdUser());
            statement.setString(2, book.getRentStarts());
            statement.setString(3, book.getRentExpires());
            statement.setLong(4, id);
            statement.execute();
            statement.close();
            log.info(statement.toString());
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBConnectionException("Error update book",e.fillInStackTrace());
        }
    }

    public boolean deleteBook(Long id) throws DBWorkException{
        try {
            PreparedStatement statement = connection.prepareStatement(AppConstants.DELETE_BOOK);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            log.info(statement.toString());
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBConnectionException("Error delete book",e.fillInStackTrace());
        }
    }

    @PreDestroy
    private void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new DBConnectionException("Error disconnecting to data base",e.fillInStackTrace());
        }
    }
}
