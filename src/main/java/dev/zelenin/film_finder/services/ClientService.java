package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_factory.IDAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IClientDAO;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.User;
import dev.zelenin.film_finder.data.database.DatabaseManager;
import dev.zelenin.film_finder.utils.Util;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.sql.Connection;

/**
 * Created by victor on 31.08.16.
 */
public class ClientService extends DatabaseService {
    public static Client updateClient(long clientID, String name, String gender, String email,
                                      String password, String photoURL) {
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();

        Client client = new Client(name, Util.parseGender(gender), email, password, photoURL);

        dao.update(clientID, client);


        closeConnection(connection);

        return client;
    }

    public static Client register(String name, String gender, String email, String password,
                                  String photoUrl) {
        Client client = new Client(name, Util.parseGender(gender), email, password, photoUrl);
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();

        dao.save(client);

        Client clientWithCorrectID = dao.getClientByEmail(email);

        closeConnection(connection);

        return clientWithCorrectID;
    }


    public static boolean exists(String email) {
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();
        boolean exists = dao.getClientByEmail(email) != null;

        closeConnection(connection);

        return exists;
    }

    public static Client createClient(String email, String password) throws Exception {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory daoFactory = new DAOFactory(connection);

        Client user = daoFactory.getClientDAO().getClientByEmail(email);
        checkPassword(user, password);

        System.out.println(user);
        if (user == null) {
            throw new Exception("User not found or incorrect password");
        }

        closeConnection(connection);

        return user;
    }

    private static void checkPassword(User user, String password) throws Exception {
        if (!user.getPassword().equals(password)) {
            throw new Exception("Incorrect password");
        }
    }


    public static void uploadClientImage(String path, FileItem imageItem) throws Exception {
        File file = new File(path + imageItem.getName());
        imageItem.write(file);
    }

    public static void addMovieToFavourites(Movie movie, Client client) {
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();

        if (!dao.isAddedMovie(movie, client)) {
            dao.addNewMovie(client, movie);
        }

        closeConnection(connection);
    }

    public static void removeMovieFormFavourites(Movie movie, Client client) {
        // TODO implement this
    }
}
