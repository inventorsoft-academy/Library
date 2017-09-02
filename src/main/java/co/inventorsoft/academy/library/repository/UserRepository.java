package co.inventorsoft.academy.library.repository;

import co.inventorsoft.academy.library.model.User;

import java.util.List;

public interface UserRepository {

    boolean createUsers(User user);

    List<User> readUsersList();

    User readUserById(Long id);

    boolean updateUser(Long id, User user);

    boolean deleteUser(Long id);

}
