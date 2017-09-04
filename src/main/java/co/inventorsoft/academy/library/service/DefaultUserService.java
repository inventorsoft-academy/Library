package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.common.custom_exception.DBConnectionException;
import co.inventorsoft.academy.library.common.custom_exception.DBWorkException;
import co.inventorsoft.academy.library.model.User;
import co.inventorsoft.academy.library.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) throws DBConnectionException{
        this.userRepository = userRepository;
    }

    @Override
    public boolean saveUsers(User user) throws DBWorkException {
        return userRepository.createUsers(user);
    }

    @Override
    public List<User> getUsersList() throws DBWorkException {
        return userRepository.readUsersList();
    }

    @Override
    public User getUserById(Long id) throws DBWorkException {
        return userRepository.readUserById(id);
    }

    @Override
    public boolean updateUser(Long id, User user) throws DBWorkException {
        return userRepository.updateUser(id, user);
    }

    @Override
    public boolean deleteUser(Long id) throws DBWorkException {
        return userRepository.deleteUser(id);
    }
}