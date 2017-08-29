package library.service;

import library.UImanager.Deleter;
import library.reprository.filemanager.FileManager;
import library.reprository.filemanager.TXTFileManager;
import library.model.User;
import library.common.MyLogger;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Scanner;

public class UserManager implements Deleter {
    private static final MyLogger log = MyLogger.getCommonClass(UserManager.class);

    private FileManager fileManager =new TXTFileManager();

    public List<User> getAllUsers() {
        return allUsers;
    }

    private List<User> allUsers=fileManager.getUsersList();

    public List<User> getUsers() {
        return allUsers;
    }


      public void registerUser(User user) {

          if (user.validate().isEmpty()) {
              allUsers.add(user);
              log.info("User added!");
              fileManager.saveUsers(allUsers);
          } else {
              log.info("Not valid data!");
              System.out.println(user.validate());
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
                    && (allUsers.get(i).getLastName().equals(lastName)))
            {
               log.info("You logged as: " + allUsers.get(i).getFirstName()
                       + " " + allUsers.get(i).getLastName());
                openUserPanel(allUsers.get(i));////////////OPEN USER PANEL
                break;

            } else if (("admin".equals(firstName)) && ("admin".equals(lastName))) {
                log.info("You logged as: ADMIN ");
                openAdminPanel();////////////OPEN ADMINISTRATOR PANEL (CASUAL USER WITH NAME: admin admin)!!!!!!!!!!!
                break;

            } else if ((allUsers.size() == i + 1)||(allUsers.isEmpty())) {
                log.info("There is no such user! Try again!");
                enterByUser();
                break;
            }
        }

    }

    @Override
    public boolean delete(int id) {
        if( allUsers.remove(id)!=null){
            allUsers.remove(id);
            fileManager.saveUsers(allUsers);
            return true;
        }
        return false;
     }

    public void update(int whichUser,User user) {
        allUsers.get(whichUser).setFirstName(user.getFirstName());
        allUsers.get(whichUser).setLastName(user.getLastName());
        allUsers.get(whichUser).setPassportNumber(user.getPassportNumber());
        fileManager.saveUsers(allUsers);
    }
}
