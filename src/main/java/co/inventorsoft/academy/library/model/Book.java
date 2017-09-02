package co.inventorsoft.academy.library.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Setter
@Getter
@EqualsAndHashCode
public class Book implements Validator {

    private Long id;
    private Long idUser;
    private String name;
    private String year;
    private String author;
    private String genre;
    private String rentStarts;
    private String rentExpires;

    public Book() {
    }

    public Book(String name, String year, String author, String genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getRentStarts() {
        return rentStarts;
    }

    public void setRentStarts(String rentStarts) {
        this.rentStarts = rentStarts;
    }

    public String getRentExpires() {
        return rentExpires;
    }

    public void setRentExpires(String rentExpires) {
        this.rentExpires = rentExpires;
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

        return valid;
    }
}
