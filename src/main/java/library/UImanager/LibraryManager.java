package library.UImanager;

import library.model.Book;
import library.model.User;
import library.common.MyLogger;
import library.service.BookManager;
import library.service.UserManager;

import java.util.*;

import static library.UImanager.BooksPrinter.printAllAvailableBooks;
import static library.UImanager.BooksPrinter.printAllBooks;
import static library.UImanager.BooksPrinter.printAllUsersBooks;
import static library.UImanager.UsersPrinter.printAllUsers;

/**
 * The main UI method, which contains users and books manager objects.
 */

public class LibraryManager {
/*

    private static final MyLogger log = MyLogger.getCommonClass(LibraryManager.class);

    private static BookManager bookManager = new BookManager();
    private static UserManager userManager = new UserManager();

    private List<User> allUsers = userManager.getAllUsers();
    private List<Book> allBooks = bookManager.getAllBooks();


    public void startScreen() {
        System.out.println("1-- Register new user "
                + "\n2-- Login"
                + "\n3-- Exit");
        try {
            switch (intReturner()) {
                case 1:
                    userManager.registerUser(askUser());
                    startScreen();
                    break;
                case 2:
                    enterByUser();
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

    private void openUserPanel(User user) {
        System.out.println("" +
                "\n1-- Check available book's " +
                "\n2-- Order book " +
                "\n3-- Return book " +
                "\n4-- Check my book's" +
                "\n5-- Back to main screen" +
                "\n6-- Exit");
        try {
            switch (intReturner()) {
                case 1: {
                    printAllAvailableBooks(bookManager);
                    openUserPanel(user);
                    break;
                }

                case 2: {
                    printAllAvailableBooks(bookManager);
                    log.info("\nWhich book u want to take:");
                    int bookNum = intReturner();
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
                        bookManager.returnSomeBook(user, intReturner());
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


    private void openAdminPanel() {
        try {
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

            switch (intReturner()) {
                case 1: {
                    userManager.registerUser(askUser());
                    openAdminPanel();
                    break;
                }
                case 2: {
                    printAllUsers(allUsers);
                    System.out.println("\nWhich user u want to update? ");
                    userManager.update(intReturner(), askUser());
                    log.info("User updated!");
                    openAdminPanel();
                    break;
                }
                case 3: {
                    printAllUsers(allUsers);
                    System.out.println("Which user u want to delete? ");
                    int id =intReturner();
                    userManager.delete(id);
                    System.out.println("User deleted! ");
                    openAdminPanel();
                    break;
                }
                case 4: {
                    if (bookManager.createNewBook(askBook())) {
                        System.out.println("New book created!!!");
                    } else {
                        System.out.println("Not valid data!!!" + "\n" + askBook().validate());
                    }

                    openAdminPanel();
                    break;
                }
                case 5: {
                    printAllBooks(bookManager);
                    System.out.println("\nWhich book u want to update?");
                    bookManager.update(intReturner(), askBook());
                    log.info("The book was updated!");
                    openAdminPanel();
                    break;
                }
                case 6: {
                    printAllBooks(bookManager);
                    System.out.println("\nWhich book u want to delete?");
                    if (bookManager.delete(intReturner())) {
                        System.out.println("Book was deleted!!!");
                    } else {
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
            log.error(e.getMessage());
            openAdminPanel();
        }
    }

    int intReturner(){
        Scanner scanner1 = new Scanner(System.in);
        int id = scanner1.nextInt();
        return id;
    }
    String stringReturner(){
        Scanner stringScanner = new Scanner(System.in);
        String line = stringScanner.nextLine();
        return line;
    }

    @NotNull
    private Book askBook() {
        String name, author, year, genre;
        int quantity;
        System.out.println("\nEnter book name:");
        name = stringReturner();
        System.out.println("\nEnter book author:");
        author = stringReturner();
        System.out.println("\nEnter book year:");
        year = stringReturner();
        System.out.println("\nEnter book genre:");
        genre = stringReturner();
        System.out.println("\nEnter books quantity:");
        quantity = intReturner();
        return new Book(name, author, year, genre, quantity);

    }

    @NotNull
    private User askUser() {
        String firstName, lastName, passport;
        Scanner scString = new Scanner(System.in);
        System.out.println("\nEnter user first Name:");
        firstName = scString.nextLine();
        System.out.println("\nEnter user lastName:");
        lastName = scString.nextLine();
        System.out.println("\nEnter user password:");
        passport = scString.nextLine();
        return new User(firstName, lastName, passport);
    }

    private void returnToPanelOrExit(User user) {
        System.out.println("\n1-- Return to user panel \n2-- Exit");
        switch (intReturner()) {
            case 1:
                openUserPanel(user);
                break;
            case 2:
                exit();
                break;
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

    public void enterByUser() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter user first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter user last name:");
        String lastName = scanner.nextLine();

        for (int i = 0; i < allUsers.size(); i++) {
            if ((allUsers.get(i).getFirstName().equals(firstName))
                    && (allUsers.get(i).getLastName().equals(lastName))) {
                log.info("You logged as: " + allUsers.get(i).getFirstName()
                        + " " + allUsers.get(i).getLastName());
                openUserPanel(allUsers.get(i));////////////OPEN USER PANEL
                break;

            } else if (("admin".equals(firstName)) && ("admin".equals(lastName))) {
                log.info("You logged as: ADMIN ");
                openAdminPanel();////////////OPEN ADMINISTRATOR PANEL (CASUAL USER WITH NAME: admin admin)!!!!!!!!!!!
                break;

            } else if ((allUsers.size() == i + 1) || (allUsers.isEmpty())) {
                log.info("There is no such user! Try again!");
                enterByUser();
                break;
            }
        }

    }


    private static void exit() {
        log.info("Goodbye! Have a nice day!");
        System.exit(1);
    }
*/

}
