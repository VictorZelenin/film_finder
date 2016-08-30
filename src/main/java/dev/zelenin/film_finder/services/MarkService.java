package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonMarkDAO;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieMarkDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 30.08.16.
 */
public class MarkService extends DatabaseService {

    public static MovieMark addMovieMark(int mark, Date date, String comment, Movie movie, Client client) {
        Connection connection = DatabaseManager.getConnection();
        IMovieMarkDAO movieMarkDAO = new DAOFactory(connection).getMovieMarkDAO();
        MovieMark movieMark = new MovieMark(mark, date, comment, movie, client);
        MovieMark storedMovieMark = getMovieMarkByClient(client, movie);

        if (storedMovieMark != null) {
            movieMarkDAO.update(storedMovieMark.getId(), movieMark);
        } else {
            movieMarkDAO.save(movieMark);
        }

        closeConnection(connection);

        return movieMark;
    }

    public static ActingPersonMark addActingPersonMark(int mark, Date date, String comment,
                                                       ActingPerson actingPerson, Client client) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonMarkDAO dao = new DAOFactory(connection).getActingPersonMarkDAO();
        ActingPersonMark actingPersonMark = new ActingPersonMark(mark, date, comment, actingPerson, client);
        ActingPersonMark storedActingPersonMark = getActingPersonMarkByClient(client, actingPerson);

        if (storedActingPersonMark != null) {
            dao.update(storedActingPersonMark.getId(), actingPersonMark);
        } else {
            dao.save(actingPersonMark);
        }

        closeConnection(connection);

        return actingPersonMark;
    }

    public static MovieMark getMovieMarkByClient(Client client, Movie movie) {
        Connection connection = DatabaseManager.getConnection();
        IMovieMarkDAO dao = new DAOFactory(connection).getMovieMarkDAO();
        MovieMark movieMark = dao.findMovieMarkByClientAndMovie(client, movie);

        closeConnection(connection);

        return movieMark;
    }

    public static List<MovieMark> getAllMovieMarks(Movie movie) {
        Connection connection = DatabaseManager.getConnection();
        IMovieMarkDAO dao = new DAOFactory(connection).getMovieMarkDAO();
        List<MovieMark> movieMarks = dao.findMovieMarksByMovie(movie);

        closeConnection(connection);

        return movieMarks;
    }

    public static ActingPersonMark getActingPersonMarkByClient(Client client, ActingPerson person) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonMarkDAO dao = new DAOFactory(connection).getActingPersonMarkDAO();
        ActingPersonMark mark = dao.findActingPersonMarkByClientAndPerson(client, person);

        closeConnection(connection);

        return mark;
    }

    public static List<ActingPersonMark> getAllPersonMarks(ActingPerson person) {
        Connection connection = DatabaseManager.getConnection();
        IActingPersonMarkDAO dao = new DAOFactory(connection).getActingPersonMarkDAO();
        List<ActingPersonMark> marks = dao.findActingPersonMarksByActingPerson(person);

        closeConnection(connection);

        return marks;
    }
}
