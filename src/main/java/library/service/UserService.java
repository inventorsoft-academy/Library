package library.service;

import library.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getSongs();

    User saveSong(final User songInfo);

    Optional<User> findByName(final String name);

    Optional<User> findById(final Long id);

    boolean update(final Long id, final User updates);

    boolean remove(final Long id);
}
