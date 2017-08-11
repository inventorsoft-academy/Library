package library.project.administration;

import library.project.model.User;

import java.util.List;
import java.util.Scanner;

import static library.project.administration.LibraryManager.openAdminPanel;
import static library.project.administration.LibraryManager.openUserPanel;
import static library.project.administration.LibraryManager.startScreen;

public class UserManager {

    public static List<User> allUsers;





      void registerUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user first name:");
        String fname = scanner.nextLine();
        System.out.println("Enter user last name:");
        String lname = scanner.nextLine();
        System.out.println("Enter your passport number:");
        String passportNum = scanner.nextLine();
        User user = new User(fname, lname, passportNum);
        allUsers.add(user);
        System.out.println("User added!");
        startScreen();

    }
      void enterByUser() {
        System.out.println("git really works?");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter user first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter user last name:");
        String lastName = scanner.nextLine();

        for (int i = 0; i < allUsers.size(); i++) {
            if ((allUsers.get(i).getFirstName().equals(firstName)) && (allUsers.get(i).getLastName().equals(lastName))) {
                System.out.println("You logged as: " + allUsers.get(i).getFirstName() + " " + allUsers.get(i).getLastName());
                openUserPanel(allUsers.get(i));
                break;
            } else if ((firstName.equals("admin")) && (lastName.equals("admin"))) {
                System.out.println("You logged as: admin ");
                openAdminPanel();
                break;

            } else if (allUsers.size() == i + 1) {
                System.out.println("There is no such user!");
                break;
            }
        }

    }

    public void getAllUsers() {
        for (User a : allUsers) {
            System.out.println("Reader name: " + allUsers.get(allUsers.indexOf(a)).getFirstName()
                    + "  " + allUsers.get(allUsers.indexOf(a)).getLastName()
                    + " ID: " + allUsers.get(allUsers.indexOf(a)).getReaderId());
        }
    }
}
