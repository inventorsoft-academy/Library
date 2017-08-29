package library.demo;

import library.managment.CustomFileManager;
import library.managment.FileManager;
import library.managment.JSONFileManager;
import library.managment.LibraryManager;
import library.model.Book;
import library.model.User;

import java.util.List;


public class Main {

    public static void main(String[] args) {
     /*   FileManager fileManager=new JSONFileManager();

        FileManager fileManagerTXT=new CustomFileManager();
        List<User> users=fileManagerTXT.getUsersList();
        List<Book> books= fileManagerTXT.getBooksList();



        List<User> listFromJSON=fileManager.getUsersList();
        List<Book> listFromJSON1=fileManager.getBooksList();

        fileManager.saveUsers(listFromJSON);
         fileManager.saveBooks(listFromJSON1);
*/
        LibraryManager.startScreen();

    }
}
