package library.UImanager;


import library.model.User;
import library.service.BookManager;

class BooksPrinter {

    static void printAllUsersBooks(User user) {
        user.readersListOfBooks.forEach(book -> System.out.println("(" + (user.readersListOfBooks.indexOf(book)) + ") "
                + " Name: "
                + book.getName()
                + " Author: "
                + book.getAuthor()
                + "\nRent starts: "
                + book.getRentStarts()
                + "\nRent expires: "
                + book.getRentExpires()));
    }



    static void printAllBooks(BookManager bookManager) {
        bookManager.getAllBooks().forEach(book -> System.out.println(
                "(" + bookManager.getAllBooks().indexOf(book) + ") "
                        + "Name: " + book.getName()
                        + "  Author: " + book.getAuthor()
                        + "  Year: " + book.getYear()
                        + "  Quantity: " + book.getQuantity()));
    }


    static void printAllAvailableBooks(BookManager bookManager) {
        bookManager.getAllBooks().stream()
                .filter(book -> book.getQuantity() != 0)
                .forEach(book -> System.out.println("(" + (bookManager.getAllBooks().indexOf(book)) + ") "
                        + "Name: " + book.getName()
                        + "  Author: " + book.getAuthor()
                        + "  Quantity: " + book.getQuantity()));
    }

}
