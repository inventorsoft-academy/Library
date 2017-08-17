package library.managment;

import library.model.Book;
import library.model.User;
import library.service.MyLogger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static library.managment.LibraryManager.openUserPanel;

public class BookManager implements Changer {
    private static MyLogger logger = new MyLogger();

    private List<Book> allBooks = FileManager.getBooksList();

    List<Book> getBooks() {
        return allBooks;
    }


    void checkReaderBooks(User user) {
        if (user.readersListOfBooks.isEmpty()) {
            logger.info("\nYou do not reading now!");
        } else {
            printAllUsersBooks(user);
        }
    }

    private void printAllBooks() {
        allBooks.forEach(book -> System.out.println(
                "(" + allBooks.indexOf(book) + ") "
                        + "Name: " + book.getName()
                        + "  Author: " + book.getAuthor()
                        + "  Quantity: " + book.getQuantity()));
    }

    void printAllAvailableBooks() {
        allBooks.stream()
                .filter(book -> book.getQuantity() != 0)
                .forEach(book -> System.out.println("(" + (allBooks.indexOf(book)) + ") "
                        + "Name: " + book.getName()
                        + "  Author: " + book.getAuthor()
                        + "  Quantity: " + book.getQuantity()));
    }

    private void printAllUsersBooks(User user) {
        user.readersListOfBooks.forEach(book -> System.out.println("(" + (user.readersListOfBooks.indexOf(book)) + ") "
                + " Name: "
                + book.getName()
                + " Author: "
                + book.getAuthor()
                + "\nRent starts: "
                + book.getRentStart()
                + "\nRent expires: "
                + book.getRentExpires()));
    }


    void rentSomeBook(User user) {
        Scanner scanner = new Scanner(System.in);
        int bookNum;

        printAllAvailableBooks();

        System.out.println("\nWhich book u want to take:");

        bookNum = scanner.nextInt();

        if (!(bookNum >= allBooks.size())) {
            if (!user.readersListOfBooks.contains(allBooks.get(bookNum))) {

                user.readersListOfBooks.add(allBooks.get(bookNum));

                rentStarts(user.readersListOfBooks.get(user.readersListOfBooks.indexOf(allBooks.get(bookNum))));
                allBooks.get(bookNum).setQuantity(allBooks.get(bookNum).getQuantity() - 1);
                System.out.println("\nThe book was added to your reading list!");
            } else System.out.println("\nYou already have such book!");
            returnToPanelOrExit(user, scanner);
        }
        {
            System.out.println("\nIncorrect variant!!! Try again");
            rentSomeBook(user);
        }


    }

    void returnSomeBook(User user) {
        Scanner scanner = new Scanner(System.in);
        if (!user.readersListOfBooks.isEmpty()) {

            printAllUsersBooks(user);

            System.out.println("\nWhich book u want to return:");
            int bookNum = scanner.nextInt();

            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).setQuantity(
                    allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).getQuantity() + 1);

            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).rentStarts = null;
            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(bookNum))).rentExpires = null;

            user.readersListOfBooks.remove(user.readersListOfBooks.get(bookNum));
            System.out.println("\nGood job! You returned a book!");
        } else {
            System.out.println("\nReading list is empty!");
            openUserPanel(user);
        }
        returnToPanelOrExit(user, scanner);

    }

    private static void returnToPanelOrExit(User user, Scanner scanner) {
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

    void addNewBook() {
        Scanner sc = new Scanner(System.in);
        String name;
        String author;
        String year;
        String genre;
        System.out.println("\nEnter book name:");
        name = sc.nextLine();
        System.out.println("\nEnter book author:");
        author = sc.nextLine();
        System.out.println("\nEnter book year:");
        year = sc.nextLine();
        System.out.println("\nEnter book genre:");
        genre = sc.nextLine();
        Book newBook = new Book(name, author, year, genre, setQuantity());
        allBooks.add(newBook);

        System.out.println("\nThe book was added to library!");
    }


    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        printAllBooks();
        System.out.println("\nWhich book u want to delete?");
        int index = sc.nextInt();
        allBooks.remove(index);
    }


    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);


        printAllBooks();

        System.out.println("\nWhich book u want to update?");
        int index = sc.nextInt();

        System.out.println("\nWhich field u want to update?" +
                "\n1-- Name\n2-- Author\n3-- Year\n4-- Genre\n5-- Quantity");
        switch (sc.nextInt()) {
            case 1: {
                System.out.println("\nEnter new name:");
                allBooks.get(index).setName(sc1.nextLine());
                break;
            }
            case 2: {
                System.out.println("\nEnter new author:");
                allBooks.get(index).setAuthor(sc1.nextLine());
                break;
            }
            case 3: {
                System.out.println("\nEnter new year:");
                allBooks.get(index).setYear(sc1.nextLine());
                break;
            }
            case 4: {
                System.out.println("\nEnter new genre:");
                allBooks.get(index).setGenre(sc1.nextLine());
                break;
            }
            case 5: {
                allBooks.get(index).setQuantity(setQuantity());
                break;
            }
            default: {
                System.out.println("Wrong variant!Try again!!!");
                update();
            }
        }
        System.out.println("The book was updated!");
    }

    int setQuantity() {
        Scanner sc = new Scanner(System.in);
        int quantity;
        try {
            System.out.println("\nEnter new quantity:");
            quantity = sc.nextInt();
            if (quantity <= 0) throw new InputMismatchException();
        } catch (InputMismatchException a) {
            System.out.println("\nBad quantity!! Enter new quantity:");
            quantity = sc.nextInt();
        }
        return quantity;
    }
}