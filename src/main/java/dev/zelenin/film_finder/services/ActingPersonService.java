package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_factory.IDAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by victor on 10.08.16.
 */
public class ActingPersonService {

    public static Map<ActingRole, List<ActingPerson>> getMapByActingRoles(Movie movie) {
        Map<ActingRole, List<ActingPerson>> map = new HashMap<>();
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);
        IActingPersonDAO actingPersonDAO = factory.getActingPersonDAO();

        map.put(ActingRole.ACTOR, actingPersonDAO.getActorsByMovie(movie));
        map.put(ActingRole.DIRECTOR, actingPersonDAO.getDirectorsByMovie(movie));
        map.put(ActingRole.PRODUCER, actingPersonDAO.getProducersByMovie(movie));
        map.put(ActingRole.SCREEN_WRITER, actingPersonDAO.getScreenWritersByMovie(movie));

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static List<ActingPerson> getActingPeopleWithHighestMarks(double value) {
        IDAOFactory factory = new DAOFactory(DatabaseManager.getConnection());
        IActingPersonDAO actingPersonDAO = factory.getActingPersonDAO();

        return actingPersonDAO.getByClientsMark(value);
    }

    public static boolean exists(ActingPerson actingPerson) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonDAO dao = new DAOFactory(connection).getActingPersonDAO();

        return dao.exists(actingPerson);
    }
}
