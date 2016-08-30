package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IClientDAO;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.DatabaseManager;
import dev.zelenin.film_finder.utils.Util;

import java.sql.Connection;

/**
 * Created by victor on 30.08.16.
 */
public class ClientEditService extends DatabaseService {

    public static Client updateClient(long clientID, String name, String gender, String email,
                                      String password, String photoURL) {
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();

        Client client = new Client(name, Util.parseGender(gender), email, password, photoURL);

        dao.update(clientID, client);


        closeConnection(connection);

        return client;
    }


}