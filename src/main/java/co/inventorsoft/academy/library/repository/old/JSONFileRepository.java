package co.inventorsoft.academy.library.repository.old;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import co.inventorsoft.academy.library.common.MyLogger;
import co.inventorsoft.academy.library.model.Book;
import co.inventorsoft.academy.library.model.User;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class JSONFileRepository implements FileRepository {
    private static final MyLogger log = MyLogger.getCommonClass(JSONFileRepository.class);

    private static final String BOOKS_FILE_PATH = "src\\main\\resources\\Books.json";

    private static final String USERS_FILE_PATH = "src\\main\\resources\\Users.json";

    private <T> List<T> readValues(String pathToFile, Class<T> clazz) {
        ObjectMapper mapper =new ObjectMapper();
        CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return mapper.readValue(new File(pathToFile), collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getBooksList() {
        return readValues(BOOKS_FILE_PATH,Book.class);

    }

    @Override
    public Book getBookById(Long id) {
        return null;
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return null;
    }

    @Override
    public Book deleteBook(Long id) {
        return null;
    }

    @Override
    public List<User> getUsersList() {
        return readValues(USERS_FILE_PATH,User.class);
    }

    @Override
    public void saveUsers(List<User> allUsersList) {
        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            mapper.writeValue(new File(USERS_FILE_PATH), allUsersList);
            log.info("Users saved!");
        } catch (IOException a) {
            log.error("FILE NOT FOUND!" +"\n"+a.getMessage());
        }
    }

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public User updateUser(Long id, User user) {
        return null;
    }

    @Override
    public User deleteUser(Long id) {
        return null;
    }


    @Override
    public void saveBooks(List<Book> allBooksList) {
        ObjectWriter mapper = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            mapper.writeValue(new File(BOOKS_FILE_PATH), allBooksList);
            log.info("Books saved!");
        } catch (IOException a) {
            log.error("FILE NOT FOUND!" +"\n"+a.getMessage());
        }
    }
}
