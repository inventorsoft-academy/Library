package library.model;

import library.service.Validator;

import java.util.Date;
import java.util.Map;

public class Book implements Validator{
    private String name;
    private String year;
    private String author;
    private String genre;
    public Date rentStarts;
    public Date rentExpires;
    public int quantity;

    public Book(final String name, final String author, final String year, final String genre, int quantity) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
        this.quantity = quantity;
    }



    public Date getRentStart() {
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
    public Map<Object, String> validate() {
        return null;
    }
}
