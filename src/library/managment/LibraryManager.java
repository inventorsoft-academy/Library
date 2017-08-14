package library.managment;

import library.model.User;

import java.util.*;

public class LibraryManager {

    private static BookManager bookManager = new BookManager();
    private static UserManager userManager = new UserManager();

    public static void startScreen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("1-- Register new user "
                        +"\n2-- Login");
        try {
            switch (sc.nextInt()) {
                case 1:
                    userManager.registerUser();
                    break;
                case 2:
                    userManager.enterByUser();
                    break;
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
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "\nAdd new user(1) " +
                        "\nUpdate user(2) " +
                        "\nDelete user(3) " +
                        "\nAdd new book(4)" +
                        "\nUpdate book(5)" +
                        "\nDelete book(6)" +
                        "\nUpload book's list from file(7)" +
                        "\nUpload user's list from file(8)" +
                        "\nSave book's list to file(9)" +
                        "\nSave user's list to file(0)" +
                        "\nExit(10)");

       /* switch (sc.nextInt()) {
            case 1:
                addUser();
                break;
            case 2:
                updateUser();
                break;
            case 3:
                deleteUser();
                break;
            case 4:
                addBook();
                break;
            case 5:
                updateBook();
                break;
            case 6:
                deleteBook();
                break;
            case 7:
                uploadBooksListFromFile();
                break;
            case 8:
                uploadUsersListFromFile();
                break;
            case 9:
                saveBooksListToFile();
                break;
            case 0:
               { saveUsersListToFile();
                break;
                case 10:
                break;
        }*/

        System.out.println("Admin panel");
    }


    static void openUserPanel(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("" +
                "\n1-- Check available book's " +
                "\n2-- Order book " +
                "\n3-- Return book " +
                "\n4-- Check my book's" +
                "\n5-- Exit");
        try {
            switch (sc.nextInt()) {
                case 1: {
                    bookManager.getAllBooks(user);
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
                    bookManager.checkMyBooks(user);
                    break;
                }

                case 5: {
                    System.out.println("Goodbye! Have a nice day!");
                    break;
                }
                default: {
                    System.out.println("Incorrect variant!!! Try again!");
                    openUserPanel(user);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("String inputted!!! Try again!");
            openUserPanel(user);
        }
    }

}
