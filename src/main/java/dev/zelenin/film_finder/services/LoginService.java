package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_factory.IDAOFactory;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.User;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import java.sql.Connection;

/**
 * Created by victor on 08.08.16.
 */
public class LoginService {

    public static Client createClient(String email, String password) throws Exception {
        Connection connection = DatabaseManager.getConnection();
//        Connection connection = DatabaseManager.getDebugConnection();
        IDAOFactory daoFactory = new DAOFactory(connection);

        Client user = daoFactory.getClientDAO().getClientByEmail(email);
        checkPassword(user, password);

        System.out.println(user);
        if (user == null) {
            throw new Exception("User not found or incorrect password");
        }

        return user;
    }

    private static void checkPassword(User user, String password) throws Exception {
        if (!user.getPassword().equals(password)) {
            throw new Exception("Incorrect password");
        }
    }
}
