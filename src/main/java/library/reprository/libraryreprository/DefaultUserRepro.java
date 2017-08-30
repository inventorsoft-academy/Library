package library.reprository.libraryreprository;

import library.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class DefaultUserRepro implements UserRepro {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User save(User songInfo) {
        return null;
    }

    @Override
    public Optional<User> findByName(String name) {
        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return null;
    }

    @Override
    public boolean update(Long id, User newInfo) {
        return false;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }
}
