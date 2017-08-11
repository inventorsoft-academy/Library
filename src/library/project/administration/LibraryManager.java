package library.project.administration;


import library.project.model.User;
import java.util.*;

import static library.project.administration.UserManager.enterByUser;

public class LibraryManager {

    private  BookManager bookManager=new BookManager();
    private  UserManager userManager=new UserManager();

    public  void startScreen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Register new user(1) \nLogin(2)");
        int a = sc.nextInt();
        switch (a) {
            case 1:
                userManager.registerUser();
                break;
            case 2:
                userManager.enterByUser();
                break;
        }
    }

       void openAdminPanel() {
        Scanner sc = new Scanner(System.in);
        System.out.println(
                "Add new user(1) " +
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

        int a = sc.nextInt();
       /* switch (a) {
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


     void openUserPanel(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Check aviable book's(1) \nOrder book(2) \nReturn book(3) \nCheck my book's(4)\nExit(5)");
        int a = sc.nextInt();
        switch (a) {
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
                break;
            }
        }

    }


}
