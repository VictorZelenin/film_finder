package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IAdminDAO;
import dev.zelenin.film_finder.data.data_sets.users.Admin;
import dev.zelenin.film_finder.data.data_sets.users.User;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import java.sql.Connection;

/**
 * Created by victor on 31.08.16.
 */
public class AdminService extends DatabaseService {

    public static Admin createAdmin(String email, String password) throws Exception {
        Connection connection = DatabaseManager.getConnection();
        IAdminDAO dao = new DAOFactory(connection).getAdminDAO();
        Admin admin = dao.findByEmail(email);
        checkPassword(admin, password);

        if (admin == null) {
            throw new Exception("User not found or incorrect password");
        }

        closeConnection(connection);

        return admin;
    }

    private static void checkPassword(User user, String password) throws Exception {
        if (!user.getPassword().equals(password)) {
            throw new Exception("Incorrect password");
        }
    }

}
