package library.model;

import library.model.validation.Validator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Book implements Validator {
    private String name;
    private String year;
    private String author;
    private String genre;
    public Date rentStarts;
    public Date rentExpires;
    public int quantity;

    public Book(){};

    public Book(Book book){
        this.name = book.getName();
        this.year = book.getYear();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
        this.quantity = book.getQuantity();

    };
    public Book(final String name, final String author, final String year, final String genre, int quantity) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }


    public Date getRentStarts() {
        return rentStarts;
    }

    public Date getRentExpires() {
        return rentExpires;
    }


    public String getName() {
        return name;
    }


    public String getYear() {
        return year;
    }


    public String getAuthor() {
        return author;
    }


    public String getGenre() {
        return genre;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public Map<String, String> validate() {

        Map<String, String> valid = new HashMap<>();

        if (name.isEmpty() || name.equals(" ")) {
            valid.put("Name", "Empty name");
        }

        if (name.length() > 35 || name.length() < 4) {
            valid.put("Name", "Impossible name length!");
        }

        if (year.matches(".*[A-Z].*")) {
            valid.put("Year", "Year contains char!");
        }

        if (author.isEmpty() || author.equals(" ")) {
            valid.put("Author", "Empty author");
        }

        if (author.length() > 35 || author.length() < 4) {
            valid.put("Name", "Impossible name length!");
        }
        if (quantity<=0) {
            valid.put("Quantity", "Wrong quantity!");
        }

        return valid;
    }
}
