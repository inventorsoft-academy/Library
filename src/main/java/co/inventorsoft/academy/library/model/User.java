package co.inventorsoft.academy.library.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Setter
@Getter
@EqualsAndHashCode
public class User implements Validator {
    private Long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private List<Book> readersListOfBooks;

    public User() {
        readersListOfBooks = new ArrayList<>();
    }

    public User(String firstName, String lastName, String passportNumber) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public List<Book> getReadersListOfBooks() {
        return readersListOfBooks;
    }

    public void setReadersListOfBooks(List<Book> readersListOfBooks) {
        this.readersListOfBooks = readersListOfBooks;
    }

    @Override
    public Map<String, String> validate() {

        Map<String, String> valid = new HashMap<>();
        firstName = firstName.trim();
        lastName = lastName.trim();
        passportNumber = passportNumber.trim();

        String passportNumbers = passportNumber.substring(2);
        String passportVersion = passportNumber.replaceAll(passportNumbers, "");

        if (firstName.isEmpty()) {
            valid.put("First name", "Empty string with first name!");
        }

        if ((firstName.length() < 2) || (firstName.length() > 15)) {
            valid.put("First name", "Not Correct first name!");
        }

        if (lastName.isEmpty()) {
            valid.put("Last name", "Empty string with last name!");
        }

        if ((lastName.length() < 2) || (lastName.length() > 15)) {
            valid.put("Last name", "Not Correct last name!");
        }

        if (passportNumber.length() != 8) {
            valid.put("Passport number", "Wrong passport number!");
        }

        if (passportVersion.matches(".*\\d+.*")) {
            valid.put("Passport version", "Wrong passport version!");
        }

        if (passportNumbers.length() != 6) {
            valid.put("Passport number", "Wrong passport symbol amount!");
        }

        if (passportNumbers.matches(".*[A-Z].*")) {
            valid.put("Passport numbers", "Passport numbers contain chars!");
        }

        return valid;
    }
}