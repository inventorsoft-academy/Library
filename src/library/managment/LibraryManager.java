package library.managment;

import library.model.User;

import java.util.*;

public class LibraryManager {

    private static BookManager bookManager = new BookManager();
    private static UserManager userManager = new UserManager();

    public static void startScreen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1-- Register new user "
                + "\n2-- Login"
                + "\n3-- Exit");
        try {
            switch (sc.nextInt()) {
                case 1:
                    userManager.registerUser();
                    startScreen();
                    break;
                case 2:
                    userManager.enterByUser();
                    break;
                case 3:
                    exit();
                default: {
                    System.out.println("Incorrect variant!!!Try again!");
                    startScreen();
                }
                break;
            }
        } catch (InputMismatchException a) {
            System.out.println("String inputted!!! Try again!");
            startScreen();
        }

    }

    static void openAdminPanel() {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(
                    "\n1-- Register new user"
                            + "\n2-- Update user"
                            + "\n3-- Delete user"
                            + "\n4-- Add new book"
                            + "\n5-- Update book"
                            + "\n6-- Delete book"
                            + "\n7-- Save book's list to file"
                            + "\n8-- Show all arrears"
                            + "\n9-- Back to start screen"
                            + "\n0-- Exit");

            switch (sc.nextInt()) {
                case 1: {
                    userManager.registerUser();
                    openAdminPanel();
                    break;
                }
                case 2: {
                    userManager.update();
                    openAdminPanel();
                    break;
                }
                case 3: {
                    userManager.delete();
                    openAdminPanel();
                    break;
                }
                case 4: {
                    bookManager.addNewBook();
                    openAdminPanel();
                    break;
                }
                case 5: {
                    bookManager.update();
                    openAdminPanel();
                    break;
                }
                case 6: {
                    bookManager.delete();
                    openAdminPanel();
                    break;
                }
                case 7: {
                    FileManager.saveBooks(bookManager.getBooks());
                    openAdminPanel();
                    break;
                }
                case 8: {
                    showAllRentedBooks();
                    openAdminPanel();
                    break;
                }
                case 9: {
                    startScreen();
                    break;
                }
                case 0: {
                    exit();
                    break;
                }

            }
        }
        catch (IndexOutOfBoundsException|InputMismatchException e)
        {   System.out.println(e.getMessage());
            e.printStackTrace();}
    }

    private static void showAllRentedBooks() {
        for (User user : userManager.getUsers()) {
            if (!user.getReadersListOfBooks().isEmpty())
                System.out.println("\n"+"User: " + user.getFirstName() + " " + user.getLastName());
                bookManager.checkReaderBooks(user);
        }
    }


    static void openUserPanel(User user) {
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
                    bookManager.printAllAvailableBooks();
                    openUserPanel(user);
                    break;
                }

                case 2: {
                    bookManager.rentSomeBook(user);
                    break;
                }

                case 3: {
                    bookManager.returnSomeBook(user);
                    break;
                }

                case 4: {
                    bookManager.checkReaderBooks(user);
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
                    System.out.println("Incorrect variant!!! Try again!");
                    openUserPanel(user);
                }
            }
        } catch (InputMismatchException | IndexOutOfBoundsException e) {
            System.out.println("Incorrect variant!!! Try again!(user panel exception)");
            openUserPanel(user);
        }
    }

    private static void exit() {
        System.out.println("Goodbye! Have a nice day!");
        System.exit(1);
    }
}
