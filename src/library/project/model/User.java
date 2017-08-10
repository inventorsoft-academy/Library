package library.project.model;

import library.project.model.Book;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    public List<Book> readersListOfBooks=new ArrayList<>();
    private static int readersCounter = 1;


    private String readerId;

    private String passportNumber;

    public User(final String firstName, final String lastName, final String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.readerId = "№: " + readersCounter++;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public String getReaderId() {
        return readerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getReadersListOfBooks() {
        return readersListOfBooks;
    }

    public void setReadersListOfBooks(List<Book> readersListOfBooks) {
        this.readersListOfBooks = readersListOfBooks;
    }
}
