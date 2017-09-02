package co.inventorsoft.academy.library.util;

public class AppConstants {
    public static final String URL_DB = "jdbc:postgresql://localhost:5432/library";
    public static final String USER = "postgres";
    public static final String PASSWORD = "postgres";
    public static final String DRIVER = "org.postgresql.Driver";
    public static final String CREATE_BOOK = "INSERT INTO books (name, author, year, genre) VALUES(?,?,?,?);";
    public static final String READ_BOOKS = "SELECT * FROM books";
    public static final String UPDATE_BOOK = "UPDATE books SET user_id=?,rent_start=?,rent_end=? WHERE id = ?;";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE id = ?;";
    public static final String CREATE_USER = "INSERT INTO users (first_name, last_name, passport) VALUES(?,?,?);";
    public static final String READ_USERS = "SELECT * FROM users";
    public static final String UPDATE_USER = "UPDATE users SET  first_name=?, last_name=?, passport=? WHERE id = ?;";
    public static final String DELETE_USER = "DELETE FROM users WHERE id = ?;";
}