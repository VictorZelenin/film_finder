package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_factory.IDAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.zelenin.film_finder.utils.Util.parseGender;

/**
 * Created by victor on 10.08.16.
 */
public class ActingPersonService extends DatabaseService {
    public static List<ActingRole> getRoles() {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);
        IActingPersonDAO dao = factory.getActingPersonDAO();

        List<ActingRole> roles = dao.findAllRoles();

        closeConnection(connection);

        return roles;
    }

    public static Map<ActingRole, List<ActingPerson>> getMapByActingRoles(Movie movie) {
        Map<ActingRole, List<ActingPerson>> map = new HashMap<>();
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);
        IActingPersonDAO actingPersonDAO = factory.getActingPersonDAO();

        map.put(ActingRole.ACTOR, actingPersonDAO.findActorsByMovie(movie));
        map.put(ActingRole.DIRECTOR, actingPersonDAO.findDirectorsByMovie(movie));
        map.put(ActingRole.PRODUCER, actingPersonDAO.findProducersByMovie(movie));
        map.put(ActingRole.SCREEN_WRITER, actingPersonDAO.findScreenWritersByMovie(movie));

        closeConnection(connection);

        return map;
    }

    public static List<ActingPerson> getActingPeopleWithHighestMarks(double value) {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);
        IActingPersonDAO actingPersonDAO = factory.getActingPersonDAO();
        List<ActingPerson> list = actingPersonDAO.findByClientsMark(value);

        closeConnection(connection);

        return list;
    }

    public static boolean exists(ActingPerson actingPerson) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonDAO dao = new DAOFactory(connection).getActingPersonDAO();
        boolean exists = dao.exists(actingPerson);

        closeConnection(connection);

        return exists;
    }

    public static ActingPerson getActingPersonById(long id) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonDAO dao = new DAOFactory(connection).getActingPersonDAO();
        ActingPerson actingPerson = dao.find(id);

        closeConnection(connection);

        return actingPerson;
    }

    public static ActingPerson getActingPersonById(String name) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonDAO dao = new DAOFactory(connection).getActingPersonDAO();
        ActingPerson actingPerson = dao.findActingPersonByName(name);

        closeConnection(connection);

        return actingPerson;
    }

    public static ActingPerson getActingPersonByLastName(String lastName) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonDAO dao = new DAOFactory(connection).getActingPersonDAO();
        ActingPerson actingPerson = dao.findActingPersonByLastName(lastName);


        closeConnection(connection);

        return actingPerson;
    }

    public static void prepareActingPersonData(HttpServletRequest request, ActingPerson actingPerson) {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);
        IActingPersonDAO dao = factory.getActingPersonDAO();

        List<ActingRole> actingRoles = dao.findActingPersonRoleList(actingPerson);
        List<Genre> genres = dao.findActingPersonGenreList(actingPerson);

        request.setAttribute("acting_person", actingPerson);
        request.setAttribute("roles", actingRoles);
        request.setAttribute("person_genres", genres);
    }

    public static ActingPerson saveActingPerson(String name, String gender, double height,
                                                String country, int age, Date deathDate, int moviesNumber,
                                                String photoUrl, List<ActingRole> roles,
                                                List<Genre> genres) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonDAO actingPersonDAO = new DAOFactory(connection).getActingPersonDAO();
        ActingPerson actingPerson = new ActingPerson(name, parseGender(gender), height, country, age,
                deathDate, moviesNumber, photoUrl);

        actingPersonDAO.save(actingPerson);

        ActingPerson actingPersonWithCorrectId =
                actingPersonDAO.findActingPersonByLastName(actingPerson.getName());

        actingPersonDAO.savePersonRoles(actingPersonWithCorrectId, roles);
        actingPersonDAO.savePersonGenres(actingPersonWithCorrectId, genres);

        closeConnection(connection);

        return actingPersonWithCorrectId;
    }

    public static ActingPerson updateActingPerson(long id, String name, String gender, double height,
                                                  String country, int age, Date deathDate,
                                                  int moviesNumber, String photoUrl) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonDAO actingPersonDAO = new DAOFactory(connection).getActingPersonDAO();
        ActingPerson actingPerson = new ActingPerson(name, parseGender(gender), height, country, age,
                deathDate, moviesNumber, photoUrl);

        actingPersonDAO.update(id, actingPerson);

        closeConnection(connection);

        return actingPerson;
    }
}
