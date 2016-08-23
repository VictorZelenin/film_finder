package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IClientDAO;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.DatabaseManager;
import dev.zelenin.film_finder.utils.Util;

/**
 * Created by victor on 08.08.16.
 */
public class ClientSignupService {

    public static Client register(String name, String gender, String email, String password, String photoUrl) {
        Client client = new Client(name, Util.parseGender(gender), email, password, photoUrl);
        IClientDAO dao = new DAOFactory(DatabaseManager.getConnection()).getClientDAO();
        dao.save(client);

        return client;
    }


    public static boolean exists(String email) {
        return new DAOFactory(DatabaseManager.getConnection())
                .getClientDAO()
                .getClientByEmail(email) != null;
    }

}
