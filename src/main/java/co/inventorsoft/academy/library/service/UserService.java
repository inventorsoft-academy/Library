package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.User;

import java.util.List;

public interface UserService {
    boolean saveUsers(User user);

    List<User> getUsersList();

    User getUserById(Long id);

    boolean updateUser(Long id, User user);

    boolean deleteUser(Long id);
}
