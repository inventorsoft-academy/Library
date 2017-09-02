package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.User;
import co.inventorsoft.academy.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean saveUsers(User user) {
        return userRepository.createUsers(user);
    }

    @Override
    public List<User> getUsersList() {
        return userRepository.readUsersList();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.readUserById(id);
    }

    @Override
    public boolean updateUser(Long id, User user) {
        return userRepository.updateUser(id, user);
    }

    @Override
    public boolean deleteUser(Long id) {
        return userRepository.deleteUser(id);
    }
}