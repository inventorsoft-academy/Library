package co.inventorsoft.academy.library.repository;

import co.inventorsoft.academy.library.model.Book;
import co.inventorsoft.academy.library.model.User;

import java.util.List;

//todo rename repository!
public interface FileRepository {

    void saveUsers(List<User> allUsersList);

    List<User> getUsersList();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    User deleteUser(Long id);

    void saveBooks(List<Book> allBooksList);

    List<Book> getBooksList();

    Book getBookById(Long id);

    Book updateBook(Long id, Book book);

    Book deleteBook(Long id);

}
