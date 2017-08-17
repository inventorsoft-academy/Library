package library.model;

import library.service.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User implements Validator{
    private String firstName;
    private String lastName;
    private String readerId;
    private String passportNumber;
    public List<Book> readersListOfBooks=new ArrayList<>();
    private static int readersCounter = 1;

    public User(final String firstName, final String lastName, final String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.readerId = "№: " + readersCounter++;
    }

    @Override
    public Map<Object, String> validate() {
        return null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
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

}
