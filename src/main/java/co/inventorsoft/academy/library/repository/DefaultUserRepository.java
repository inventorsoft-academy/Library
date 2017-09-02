package co.inventorsoft.academy.library.repository;

import co.inventorsoft.academy.library.common.MyLogger;
import co.inventorsoft.academy.library.model.User;
import co.inventorsoft.academy.library.util.AppConstants;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DefaultUserRepository implements UserRepository {

    private Connection connection;
    private MyLogger log = new MyLogger();

    @PostConstruct
    private void init() {
        try {
            connection = DriverManager.getConnection(AppConstants.URL_DB, AppConstants.USER, AppConstants.PASSWORD);
            Class.forName(AppConstants.DRIVER);
        } catch (SQLException | ClassNotFoundException e) {
            //todo handle exception (program not run(404))
            log.error(e.getMessage());
        }
    }

    @Override
    public boolean createUsers(User user) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(AppConstants.CREATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassportNumber());
            statement.execute();
            statement.close();
            log.info(statement.toString());
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            //todo handle exception (program not run(417 or 500))
            return false;
        }
    }

    @Override
    public List<User> readUsersList() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(AppConstants.READ_USERS)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                user.setPassportNumber(resultSet.getString("passport"));
                users.add(user);
            }
            statement.close();
            log.info(statement.toString());
            return users;
        } catch (SQLException e) {
            log.error(e.getMessage());
            //todo handle exception (continue(417 or 500))
        }
        return null;
    }

    @Override
    public User readUserById(Long id) {
        List<User> users = readUsersList();
        if (users != null) {
            for (User user : users) {
                if (id.equals(user.getId())) {
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateUser(Long id, User user) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(AppConstants.UPDATE_USER);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPassportNumber());
            statement.setLong(4, id);
            statement.execute();
            statement.close();
            log.info(statement.toString());
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            //todo handle exception (program not run(417 or 500))
            return false;
        }
    }

    @Override
    public boolean deleteUser(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(AppConstants.DELETE_USER);
            statement.setLong(1, id);
            statement.execute();
            statement.close();
            log.info(statement.toString());
            return true;
        } catch (SQLException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @PreDestroy
    private void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            //todo
            log.error(e.getMessage());
        }
    }

}
