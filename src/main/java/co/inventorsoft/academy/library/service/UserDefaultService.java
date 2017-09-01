package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.User;
import co.inventorsoft.academy.library.repository.FileRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserDefaultService implements UserService {

    private FileRepository fileRepository;

    @PostConstruct
    public void init(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void saveUsers(List<User> allUsersList) {
        fileRepository.saveUsers(allUsersList);
    }

    @Override
    public List<User> getUsersList() {
        return fileRepository.getUsersList();
    }

    @Override
    public User getUserById(Long id) {
        return fileRepository.getUserById(id);
    }

    @Override
    public User updateUser(Long id, User user) {
        return fileRepository.updateUser(id, user);
    }

    @Override
    public User deleteUser(Long id) {
        return fileRepository.deleteUser(id);
    }
}
