package library.demo;

import library.managment.CustomFileManager;
import library.managment.FileManager;
import library.managment.JSONFileManager;
import library.managment.LibraryManager;
import library.model.User;

import java.util.List;


public class Main {

    public static void main(String[] args) {
        FileManager fileManager=new JSONFileManager();

        FileManager fileManagerTXT=new CustomFileManager();
        List<User> users=fileManagerTXT.getUsersList();

        fileManager.saveUsers(users);
List<User> userss=fileManager.getUsersList();
System.out.println(userss.get(0).getFirstName()+" "+userss.get(0).getLastName());
      //  LibraryManager.startScreen();

    }
}
