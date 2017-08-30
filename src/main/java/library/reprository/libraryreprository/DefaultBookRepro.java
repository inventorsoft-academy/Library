package library.reprository.libraryreprository;

import library.model.Book;
import library.model.User;
import library.reprository.filerepro.FileManager;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Repository
public class DefaultBookRepro implements BookRepro  {

    private FileManager fileManager;

    private List<Book> storage;

    public DefaultBookRepro(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @PostConstruct
    public void init() {
        storage = fileManager.getBooksList();
    }

    @Override
    public List<Book> getAllBooks() {
        return storage;
    }

    @Override
    public void rentSomeBook(User user, int id) {

    }

    @Override
    public void returnSomeBook(User user, int id) {

    }

    @Override
    public void rentStarts(Book book) {

    }

    @Override
    public boolean createNewBook(Book book) {
        return false;
    }

    @Override
    public void update(int book_id, Book changedBook) {

    }

    @Override
    public Optional<Book> findByName(String name) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return storage.stream().filter(info -> info.getId().equals(id)).findAny();
    }

}
