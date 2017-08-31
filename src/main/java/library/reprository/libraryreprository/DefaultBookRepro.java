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
    public void update(int id, Book changedBook) {
        storage.get(id).setName(changedBook.getName());
        storage.get(id).setAuthor(changedBook.getAuthor());
        storage.get(id).setYear(changedBook.getYear());
        storage.get(id).setGenre(changedBook.getGenre());
        storage.get(id).setQuantity(changedBook.getQuantity());
    }

    @Override
    public Optional<Book> findByName(String name) {
        return storage.stream().filter(info -> info.getName().equals(name)).findAny();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return storage.stream().filter(info -> info.getId().equals(id)).findAny();
    }

    @Override
    public boolean delete(Long id) {
        return storage.removeIf(info -> info.getId().equals(id));
    }

}
