package library.model;

import library.model.validation.Validator;

import java.util.*;

public class    User implements Validator {

    private String firstName;
    private String lastName;
    private String readerId;
    private String passportNumber;
    public List<Book> readersListOfBooks=new ArrayList<>();
    private static int readersCounter = 1;

   public User(){};

    public User(final String firstName, final String lastName, final String passportNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.readerId = "№: " + readersCounter++;
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

    @Override
    public Map<String, String> validate() {

        Map<String, String> valid = new HashMap<>();
        firstName=firstName.trim();
        lastName=lastName.trim();
        passportNumber=passportNumber.trim();

        String passportNumbers=passportNumber.substring(2);
        String passportVersion=passportNumber.replaceAll(passportNumbers,"");

        if (firstName.isEmpty()){  valid.put("First name","Empty string with first name!"); }

        if ((firstName.length() < 2)|| (firstName.length() > 15)){valid.put("First name","Not Correct first name!");}

        if (lastName.isEmpty()){  valid.put("Last name","Empty string with last name!"); }

        if ((lastName.length() < 2)|| (lastName.length() > 15)){valid.put("Last name","Not Correct last name!");}

        if(passportNumber.length()!=8){valid.put("Passport number","Wrong passport number!");}

        if(passportVersion.matches(".*\\d+.*")){valid.put("Passport version","Wrong passport version!");}

        if(passportNumbers.length()!=6){valid.put("Passport number","Wrong passport symbol amount!");}

        if(passportNumbers.matches(".*[A-Z].*")){valid.put("Passport numbers","Passport numbers contain chars!");}

        return valid;
    }



}
