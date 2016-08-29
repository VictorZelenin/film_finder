package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_factory.IDAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonDAO;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IClientDAO;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieMarkDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by victor on 08.08.16.
 */
public class MovieService extends DatabaseService {

    public static List<Movie> getMoviesByImdbRating() {
        Connection connection = DatabaseManager.getConnection();
        IMovieDAO dao = new DAOFactory(connection).getMovieDAO();
        List<Movie> list = dao.findMoviesByImdbRating();

        closeConnection(connection);

        return list;
    }

    public static List<Movie> getMoviesByClientsRating(double minRatingValue) {
        Connection connection  = DatabaseManager.getConnection();
        IMovieDAO dao = new DAOFactory(connection).getMovieDAO();
        List<Movie> movies = dao.findMoviesWithHighestMarks(minRatingValue);

        closeConnection(connection);

        return movies;
    }

    public static List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();

        Collections.addAll(genres, Genre.values());

        return genres;
    }

    public static List<Movie> getMoviesByGenre(Genre genre) {
        IMovieDAO dao = new DAOFactory(DatabaseManager.getConnection()).getMovieDAO();

        return dao.findMoviesByGenre(genre);
    }

    public static List<Movie> getMoviesByClient(Client client) {
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();

        List<Movie> list = dao.findClientChosenMovies(client);

        closeConnection(connection);

        return list;
    }

    public static List<MovieMark> getMovieMarks(Client client) {
        Connection connection = DatabaseManager.getConnection();
        IMovieMarkDAO dao = new DAOFactory(connection).getMovieMarkDAO();
        List<MovieMark> list = dao.findMovieMarksByClient(client);

        closeConnection(connection);

        return list;
    }

    public static Movie getMovie(int id) {
        Connection connection = DatabaseManager.getConnection();
        IMovieDAO dao = new DAOFactory(connection).getMovieDAO();
        Movie movie = dao.find(id);

        closeConnection(connection);

        return movie;
    }

    public static void prepareMovieData(HttpServletRequest request, Movie movie) {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);

        IActingPersonDAO dao = factory.getActingPersonDAO();
        IMovieDAO iMovieDAO = factory.getMovieDAO();

        List<ActingPerson> directors = dao.findDirectorsByMovie(movie);
        List<ActingPerson> actors = dao.findActorsByMovie(movie);
        List<ActingPerson> producers = dao.findProducersByMovie(movie);
        List<ActingPerson> screenWriters = dao.findScreenWritersByMovie(movie);
        List<Genre> movieGenres = iMovieDAO.findMovieGenres(movie);

        closeConnection(connection);

        request.setAttribute("movie", movie);
        request.setAttribute("date", movie.getReleaseDate().toGMTString());
        request.setAttribute("genres", movieGenres);

        request.setAttribute("directors", directors);
        request.setAttribute("actors", actors);
        request.setAttribute("screenWriters", producers);
        request.setAttribute("producers", screenWriters);
    }
}
