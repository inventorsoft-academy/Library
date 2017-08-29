package library.UImanager;

import library.model.User;

import java.util.List;

public class UsersPrinter {

    static void printAllUsers(List<User> allUsers) {
        for (User a : allUsers) {
            System.out.println("("+allUsers.indexOf(a)+")"
                    + " Reader name: " + allUsers.get(allUsers.indexOf(a)).getFirstName()
                    + "  " + allUsers.get(allUsers.indexOf(a)).getLastName()
                    + " Passport: " +allUsers.get(allUsers.indexOf(a)).getPassportNumber() );
        }
    }


}
