package library.managment;

import library.model.Book;
import library.model.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static library.managment.LibraryManager.openUserPanel;

public class BookManager {


     private static List<Book> allBooks=FileManager.getBooksList();

    void checkMyBooks(User user) {
        if (!user.readersListOfBooks.isEmpty()) {
            System.out.println("\nThe list of books you are reading: \n");
            for (Book a : user.readersListOfBooks) {
                System.out.println(
                        "Name: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getName()
                        + "\nAuthor: " + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getAuthor()
                        + "\nRent starts: " + allBooks.get(allBooks.indexOf(a)).getRentStart()
                        + "\nRent expires: " + allBooks.get(allBooks.indexOf(a)).getRentExpires());
            }
        } else
            System.out.println("\nYou do not reading now!");
        openUserPanel(user);
    }


     void getAllBooks(User user) {

        for (Book a : allBooks) {
            if (a.quantity != 0) System.out.println("Name: " + allBooks.get(allBooks.indexOf(a)).getName()
                    + "  Author: " + allBooks.get(allBooks.indexOf(a)).getAuthor()
                    + "  Quantity: " + allBooks.get(allBooks.indexOf(a)).getQuantity());
        }
        openUserPanel(user);
    }

    void rentSomeBook(User user) {
        Scanner scanner = new Scanner(System.in);
        int bookNum;


            for (Book a : allBooks) {
                if (allBooks.get(allBooks.indexOf(a)).getQuantity() != 0)
                    System.out.println("(" + (allBooks.indexOf(a)) + ") "
                            + "Name: " + allBooks.get(allBooks.indexOf(a)).getName()
                            + "  Author: " + allBooks.get(allBooks.indexOf(a)).getAuthor()
                            + "  Quantity: " + allBooks.get(allBooks.indexOf(a)).getQuantity());
            }
            System.out.println("\nWhich book u want to take:");

            bookNum = scanner.nextInt();
        if(!(bookNum>=allBooks.size())) {
            if (!user.readersListOfBooks.contains(allBooks.get(bookNum))) {

                user.readersListOfBooks.add(allBooks.get(bookNum));
                rentStarts(user.readersListOfBooks.get(user.readersListOfBooks.indexOf(allBooks.get(bookNum))));
                allBooks.get(bookNum).setQuantity(allBooks.get(bookNum).getQuantity() - 1);
                System.out.println("\nThe book was added to your reading list!");
            } else System.out.println("\nYou already have such book!");
            returnToPanelOrExit(user,scanner);
        }
        {System.out.println("\nIncorrect variant!!! Try again");
            rentSomeBook(user);
        }


    }

     void returnSomeBook(User user) {
         Scanner scanner = new Scanner(System.in);
        if (!user.readersListOfBooks.isEmpty()) {
            for (Book a : user.readersListOfBooks) {
                System.out.println("(" + (user.readersListOfBooks.indexOf(a)) + ") "
                        + " Name: "
                        + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getName()
                        + " Author: "
                        + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getAuthor()
                        + "\nRent starts: "
                        + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getRentStart()
                        + "\nRent expires: "
                        + user.readersListOfBooks.get(user.readersListOfBooks.indexOf(a)).getRentExpires());
            }

            System.out.println("\nWhich book u want to return:");

            int bookNum = scanner.nextInt();

            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).setQuantity(
                    allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).getQuantity() + 1);

            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).rentStarts = null;
            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).rentExpires = null;

            user.readersListOfBooks.remove(user.readersListOfBooks.get(bookNum));
            System.out.println("\nGood job! You returned a book!");
            openUserPanel(user);
        } else {
            System.out.println("\nYour reading list is empty!");
            openUserPanel(user);
        }

        returnToPanelOrExit(user,scanner);

    }

private static void returnToPanelOrExit(User user,Scanner scanner){
    System.out.println("\n1-- Return to user panel \n2-- Exit");
    switch (scanner.nextInt()) {
        case 1:
            openUserPanel(user);
            break;
        case 2:
            System.out.println("Goodbye! Have a nice day!");
            break;
    }


}

    private static void rentStarts(Book book) {
        book.rentStarts = new Date();
        book.rentExpires = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(book.rentStarts);
        book.rentStarts = c.getTime();
        LocalDateTime ldt = LocalDateTime.ofInstant(book.rentStarts.toInstant(), ZoneId.systemDefault()).plusDays(7);
        book.rentExpires = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }


}
