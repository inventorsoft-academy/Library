package library.managment;

import library.model.User;

import java.util.List;
import java.util.Scanner;

import static library.managment.LibraryManager.openAdminPanel;
import static library.managment.LibraryManager.openUserPanel;

public class UserManager implements Changer {

    private   List<User> allUsers=FileManager.getUsersList();

    public List<User> getUsers() {
        return allUsers;
    }


      void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter user first name:");
        String fname = scanner.nextLine();

        System.out.println("Enter user last name:");
        String lname = scanner.nextLine();

        System.out.println("Enter your passport number:");
        String passportNum = scanner.nextLine();

        User user = new User(fname, lname, passportNum);
        allUsers.add(user);

        System.out.println("User added!");

        FileManager.saveUsers(allUsers);

    }
      void enterByUser() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter user first name:");
        String firstName = scanner.nextLine();

        System.out.println("Enter user last name:");
        String lastName = scanner.nextLine();

        for (int i = 0; i < allUsers.size(); i++) {
            if ((allUsers.get(i).getFirstName().equals(firstName))
                    && (allUsers.get(i).getLastName().equals(lastName)))
            {
                System.out.println("You logged as: " + allUsers.get(i).getFirstName()
                                                     + " " + allUsers.get(i).getLastName());

                openUserPanel(allUsers.get(i));
                break;
            } else if (("admin".equals(firstName)) && ("admin".equals(lastName))) {
                System.out.println("You logged as: ADMIN ");
                openAdminPanel();
                break;

            } else if ((allUsers.size() == i + 1)||(allUsers.isEmpty())) {
                System.out.println("There is no such user! Try again!");
                enterByUser();
                break;
            }
        }

    }



    public void printAllUsers() {
        for (User a : allUsers) {
            System.out.println("("+allUsers.indexOf(a)+")"
                    + " Reader name: " + allUsers.get(allUsers.indexOf(a)).getFirstName()
                    + "  " + allUsers.get(allUsers.indexOf(a)).getLastName()
                    + " ID: " + allUsers.get(allUsers.indexOf(a)).getReaderId());
        }
    }

    @Override
    public void delete() {
        Scanner sc=new Scanner(System.in);

        printAllUsers();

        System.out.println("Which user u want to delete? ");
        allUsers.remove(sc.nextInt());
        System.out.println("User deleted!");
        FileManager.saveUsers(allUsers);
     }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        Scanner sc1 = new Scanner(System.in);

        printAllUsers();

        System.out.println("\nWhich user u want to update? ");
        int index = sc.nextInt();
        System.out.println("\nWhich field u want to update?" +
                "\n1-- First name\n2-- Last name\n3-- Passport");
        int userNum=sc.nextInt();
        switch (userNum) {
            case 1: {
                System.out.println("\nEnter user new  first name:");
                allUsers.get(index).setFirstName(sc1.nextLine());
                break;
            }
            case 2: {
                System.out.println("\nEnter user new last name:");

                allUsers.get(index).setLastName(sc1.nextLine());
                break;
            }
            case 3: {
                System.out.println("\nEnter user new passport:");
                allUsers.get(index).setPassportNumber(sc1.nextLine());
                break;
            }

        }
        System.out.println("User updated!");
        FileManager.saveUsers(allUsers);
    }
}
