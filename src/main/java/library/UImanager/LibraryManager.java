package library.UImanager;

import library.model.Book;
import library.model.User;
import library.common.MyLogger;
import library.service.BookManager;
import library.service.UserManager;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import static library.UImanager.BooksPrinter.printAllAvailableBooks;
import static library.UImanager.BooksPrinter.printAllBooks;
import static library.UImanager.BooksPrinter.printAllUsersBooks;
import static library.UImanager.UsersPrinter.printAllUsers;

/**
 * The main UI method, which contains users and books manager objects.
 */
//contoller,service,repro
public class LibraryManager {

    private static final MyLogger log = MyLogger.getCommonClass(LibraryManager.class);
    private static BookManager bookManager = new BookManager();
    private static UserManager userManager = new UserManager();
    private List<User> allUsers = userManager.getAllUsers();
    private List<Book> allBooks = bookManager.getAllBooks();
    private Scanner sc = new Scanner(System.in);

    private void returnToPanelOrExit(User user) {
        System.out.println("\n1-- Return to user panel \n2-- Exit");
        switch (sc.nextInt()) {
            case 1:
                openUserPanel(user);
                break;
            case 2:
                System.out.println("Goodbye! Have a nice day!");
                break;
        }
    }


    public void startScreen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1-- Register new user "
                + "\n2-- Login"
                + "\n3-- Exit");
        try {
            switch (sc.nextInt()) {
                case 1:
                    userManager.registerUser(askUser());
                    startScreen();
                    break;
                case 2:
                    userManager.enterByUser();
                    break;
                case 3:
                    exit();
                default: {
                    log.info("Incorrect variant!!!Try again!");
                    startScreen();
                }
                break;
            }
        } catch (InputMismatchException a) {
            log.warn("String inputted!!! Try again!");
            startScreen();
        }

    }

    private void openAdminPanel() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(
                    "\n1-- Register new user"
                            + "\n2-- Update user"
                            + "\n3-- Delete user"
                            + "\n4-- Add new book"
                            + "\n5-- Update book"
                            + "\n6-- Delete book"
                            + "\n7-- Print all books"
                            + "\n8-- Show all arrears"
                            + "\n9-- Print all users"
                            + "\n10- Back to start screen"
                            + "\n0-- Exit");

            switch (sc.nextInt()) {
                case 1: {
                    userManager.registerUser(askUser());
                    openAdminPanel();
                    break;
                }
                case 2: {
                    printAllUsers(allUsers);
                    System.out.println("\nWhich user u want to update? ");
                    int userId=sc.nextInt();
                    userManager.update(userId,askUser());
                    log.info("User updated!");
                    openAdminPanel();
                    break;
                }
                case 3: {
                    printAllUsers(allUsers);
                    System.out.println("Which user u want to delete? ");
                    int id=sc.nextInt();
                    userManager.delete(id);
                    openAdminPanel();
                    break;
                }
                case 4: {
                    if (bookManager.createNewBook(askBook()))
                    {
                        bookManager.createNewBook(askBook());
                        System.out.println("New book created!!!");}
                    else{
                        System.out.println("Not valid data!!!"+"\n"+askBook().validate());
                    }

                    openAdminPanel();
                    break;
                }
                case 5: {
                    printAllBooks(bookManager);
                    System.out.println("\nWhich book u want to update?");
                    int id=sc.nextInt();
                    bookManager.update(id,askBook());
                    log.info("The book was updated!");
                    openAdminPanel();
                    break;
                }
                case 6: {
                    printAllBooks(bookManager);
                    System.out.println("\nWhich book u want to delete?");
                    int id=sc.nextInt();
                    if(bookManager.delete(id)){
                        bookManager.delete(id);
                        System.out.println("Book was deleted!!!");
                    }else{
                        System.out.println("Unable to delete!!!");
                    }
                    openAdminPanel();
                    break;
                }
                case 7: {
                    printAllBooks(bookManager);
                    openAdminPanel();
                    break;

                }
                case 8: {
                    showAllRentedBooks();
                    openAdminPanel();
                    break;
                }
                case 9: {
                    printAllUsers(allUsers);
                    openAdminPanel();
                    break;
                }
                case 0: {
                    exit();
                    break;
                }
                case 10: {
                    startScreen();
                    break;
                }
                default: {
                    log.warn("Wrong variant!");
                    openAdminPanel();
                }

            }
        } catch (IndexOutOfBoundsException | InputMismatchException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


@NotNull
private Book askBook(){
    String name,author,year,genre;
    Scanner scString = new Scanner(System.in);
    int quantity;
    System.out.println("\nEnter book name:");
    name = scString.nextLine();
    System.out.println("\nEnter book author:");
    author = scString.nextLine();
    System.out.println("\nEnter book year:");
    year = scString.nextLine();
    System.out.println("\nEnter book genre:");
    genre = scString.nextLine();
    System.out.println("\nEnter books quantity:");
    quantity = sc.nextInt();
return new Book(name,author,year,genre,quantity);

}

    @NotNull
    private User askUser(){
        String firstName,lastName,passport;
        Scanner scString = new Scanner(System.in);
        System.out.println("\nEnter user first Name:");
        firstName = scString.nextLine();
        System.out.println("\nEnter user lastName:");
        lastName = scString.nextLine();
        System.out.println("\nEnter user password:");
        passport = scString.nextLine();
        return new User(firstName,lastName,passport);
    }


    private void openUserPanel(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("" +
                "\n1-- Check available book's " +
                "\n2-- Order book " +
                "\n3-- Return book " +
                "\n4-- Check my book's" +
                "\n5-- Back to main screen" +
                "\n6-- Exit");
        try {
            switch (sc.nextInt()) {
                case 1: {
                    printAllAvailableBooks(bookManager);
                    openUserPanel(user);
                    break;
                }

                case 2: {
                    printAllAvailableBooks(bookManager);
                    log.info("\nWhich book u want to take:");
                    int bookNum = sc.nextInt();
                    //CHECKS BOOK availability

                    if ((bookNum <= allBooks.size())) {
                        if (!user.readersListOfBooks.contains(allBooks.get(bookNum))) {

                            bookManager.rentSomeBook(user, bookNum);
                            System.out.println("\nThe book was added to your reading list!");
                        } else {
                            log.info("u r reading such book already! Try again!");
                            openUserPanel(user);

                        }
                    } else {
                        log.info("\nIncorrect variant! Try again!");
                        openUserPanel(user);

                    }
                    returnToPanelOrExit(user);
                    break;
                }

                case 3: {
                    printAllUsersBooks(user);
                    if (user.readersListOfBooks.isEmpty()) {
                        log.info("\nReading list is empty!");
                        openUserPanel(user);
                    } else {
                        log.info("\nWhich book u want to return:");
                        int bookNum = sc.nextInt();
                        bookManager.returnSomeBook(user, bookNum);
                        log.info("\nGood job! You returned a book!");
                    }
                    returnToPanelOrExit(user);
                    break;
                }

                case 4: {
                    checkReaderBooks(user);
                    openUserPanel(user);
                    break;
                }
                case 5: {
                    startScreen();
                    break;
                }
                case 6: {
                    exit();
                }
                default: {
                    log.warn("Incorrect variant!!! Try again!");
                    openUserPanel(user);
                }
            }
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            log.warn("Incorrect variant!!! Try again!(user panel exception)");
            openUserPanel(user);
        }
    }


    private void showAllRentedBooks() {
        for (User user : userManager.getUsers()) {
            if (!user.getReadersListOfBooks().isEmpty()) {
                System.out.println("\n" + "User: " + user.getFirstName() + " " + user.getLastName());
                checkReaderBooks(user);
            }
        }
    }

    private void checkReaderBooks(User user) {
        if (user.readersListOfBooks.isEmpty()) {

            log.info("\nYou do not reading now!");
        } else {
            printAllUsersBooks(user);
        }
    }




    private static void exit() {
        log.info("Goodbye! Have a nice day!");
        System.exit(1);
    }

}
