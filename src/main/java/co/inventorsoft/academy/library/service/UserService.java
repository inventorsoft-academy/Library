package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.User;

import java.util.List;

public interface UserService {
    void saveUsers(List<User> allUsersList);

    List<User> getUsersList();

    User getUserById(Long id);

    User updateUser(Long id, User user);

    User deleteUser(Long id);
}
