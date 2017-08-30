package library.reprository.libraryreprository;

import library.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepro {
    List<User> findAll();

    User save(final User songInfo);

    Optional<User> findByName(final String name);

    Optional<User> findById(final Long id);

    boolean update(final Long id, final User newInfo);

    boolean remove(final Long id);
}
