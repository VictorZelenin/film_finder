package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IClientDAO;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.DatabaseManager;
import dev.zelenin.film_finder.utils.Util;

import java.sql.Connection;

/**
 * Created by victor on 08.08.16.
 */
public class ClientSignupService extends DatabaseService {

    public static Client register(String name, String gender, String email, String password, String photoUrl) {
        Client client = new Client(name, Util.parseGender(gender), email, password, photoUrl);
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();
        dao.save(client);

        closeConnection(connection);

        return client;
    }


    public static boolean exists(String email) {
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();
        boolean exists = dao.getClientByEmail(email) != null;

        closeConnection(connection);

        return exists;
    }
}
