package library.reprository.filerepro;

import library.model.Book;
import library.model.User;

import java.util.List;
//todo rename repository!
public interface FileManager {
    List<Book> getBooksList();
    List<User> getUsersList();
    void saveUsers(List<User> allUsersList);
    void  saveBooks(List<Book> allBooksList );
}
