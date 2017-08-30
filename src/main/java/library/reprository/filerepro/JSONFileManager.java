package library.reprository.filerepro;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.type.CollectionType;
import library.model.Book;
import library.model.User;
import library.common.MyLogger;

import java.io.File;

import java.io.IOException;

import java.util.List;



public class JSONFileManager implements FileManager {
    private static final MyLogger log = MyLogger.getCommonClass(JSONFileManager.class);

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
