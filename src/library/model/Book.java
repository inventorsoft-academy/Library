package library.model;

import java.util.Date;

public class Book {


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
}
