package library.project;

import library.project.LibraryAdministration.Administration;
import library.project.model.Book;
import library.project.model.User;

import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {

        Administration admin = new Administration();
        admin.allBooks = new ArrayList<>();
        admin.allUsers = new ArrayList<>();

        admin.allBooks.add(new Book("100lie pod vodoi", "1973", "Jul Vern", "fantacy", 1));
        admin.allBooks.add(new Book("200lie pod vodoi", "1973", "Jul Vern", "fantacy", 3));
        admin.allBooks.add(new Book("300lie pod vodoi", "1973", "Jul Vern", "fantacy", 3));

        admin.allUsers.add(new User("Ivo",   "Bobul", "KT5347777"));
        admin.allUsers.add(new User("Viktor","Pavlik","KT78667877"));
        admin.allUsers.add( new User("Cory",  "Tailor","KT12345679"));
        admin.allUsers.add(new User("Curt",  "Cobain","KT53466156"));

        //admin.rentSomeBook(book2);

       /* for (Book a : admin.allBooks) {
            System.out.println("Name: " + admin.allBooks.get(admin.allBooks.indexOf(a)).getName()
                    + " Quantity: " + admin.allBooks.get(admin.allBooks.indexOf(a)).getQuantity()
                    + "\n Rent starts: " +admin.allBooks.get(admin.allBooks.indexOf(a)).getRentStart()
                    + "\n Rent expires: " +admin.allBooks.get(admin.allBooks.indexOf(a)).getRentExpires() );
        }*/

       // admin.returnSomeBook(book1);

       // admin.registerUser();
       // admin.getAllUsers();
       // admin.getAllBooks();


        //TODO:Login or Register

    admin.startScreen();



    }





}
