package library.service;

import library.UImanager.Deleter;
import library.reprository.filerepro.FileManager;
import library.reprository.filerepro.TXTFileManager;
import library.model.User;

import java.util.List;

public class UserManager implements Deleter {
    private List<User> allUsers;
    private FileManager fileManager;

    public UserManager() {
        this.fileManager =new TXTFileManager();
        this.allUsers=fileManager.getUsersList();
    }

    public List<User> getAllUsers() {
        return allUsers;
    }


      public void registerUser(User user) {
          if (user.validate().isEmpty()) {
              allUsers.add(user);
              fileManager.saveUsers(allUsers);
          } else {
              System.out.println(user.validate());
          }


    }


    @Override
    public boolean delete(int id) {
        if( allUsers.remove(id)!=null){
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
