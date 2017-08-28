package library.managment;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import library.model.Book;
import library.model.User;
import library.service.MyLogger;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class JSONFileManager implements FileManager {
    private static final MyLogger log = MyLogger.getCommonClass(JSONFileManager.class);

    private static final String BOOKS_FILE_PATH = "src\\main\\resources\\Books.json";

    private static final String USERS_FILE_PATH = "src\\main\\resources\\Users.json";


    @Override
    public List<Book> getBooksList() {
        List<Book> booksList = new ArrayList<>();
        /*    try {
                Scanner scn = new Scanner(new File(BOOKS_FILE_PATH));

                JSONParser parser = new JSONParser(String);
                JSONArray array = (JSONArray) parser.parse(scn.nextLine());
                JSONObject obj;
                for (int i = 0; i < array.size(); i++) {
                    obj = (JSONObject) parser.parse(array.get(i).toString());
                   booksList.add(new Book((String) obj.get("name"),(String) obj.get("author")),
                           (String)obj.get("genre"), Integer.parseInt("" + obj.get("quantity")));
                }
            } catch (ParseException | FileNotFoundException e) {
                e.printStackTrace();
            }*/
        return booksList;
    }


    @Override
    public List<User> getUsersList() {
        List<User> users;
        ObjectMapper mapper =new ObjectMapper();
        try{

            BufferedReader br = new BufferedReader(
                    new FileReader(USERS_FILE_PATH));

            //convert the json string back to object
            users = mapper.readValue(br, new TypeReference<List<User>>(){});
            return users;
        } catch (IOException e) {
            log.error("FILE NOT FOUND!" +"\n"+e.getMessage());
        }
        return null;
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
