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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by victor on 08.08.16.
 */
public class MovieService {
    public static List<Movie> getMoviesByImdbRating() {
        Connection connection = DatabaseManager.getConnection();
        IMovieDAO dao = new DAOFactory(connection).getMovieDAO();
        List<Movie> list = dao.getMoviesByImdbRating();

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<Movie> getMoviesByClientsRating(double minRatingValue) {
        IMovieDAO dao = new DAOFactory(DatabaseManager.getConnection())
                .getMovieDAO();

        return dao.getMoviesWithHighestMarks(minRatingValue);
    }

    public static List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();

        Collections.addAll(genres, Genre.values());

        return genres;
    }

    public static List<Movie> getMoviesByGenre(Genre genre) {
        IMovieDAO dao = new DAOFactory(DatabaseManager.getConnection()).getMovieDAO();

        return dao.getMoviesByGenre(genre);
    }

    public static List<Movie> getMoviesByClient(Client client) {
        Connection connection = DatabaseManager.getConnection();
        IClientDAO dao = new DAOFactory(connection).getClientDAO();

        List<Movie> list = dao.getClientChosenMovies(client);

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static List<MovieMark> getMovieMarks(Client client) {
        Connection connection = DatabaseManager.getConnection();
        IMovieMarkDAO dao = new DAOFactory(connection).getMovieMarkDAO();
        List<MovieMark> list = dao.getMovieMarksByClient(client);

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static Movie getMovie(int id) {
        Connection connection = DatabaseManager.getConnection();
        IMovieDAO dao = new DAOFactory(connection).getMovieDAO();
        Movie movie = dao.get(id);

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movie;
    }

    public static void prepareMovieData(HttpServletRequest request, Movie movie) {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);

        IActingPersonDAO dao = factory.getActingPersonDAO();
        IMovieDAO iMovieDAO = factory.getMovieDAO();

        List<ActingPerson> directors = dao.getDirectorsByMovie(movie);
        List<ActingPerson> actors = dao.getActorsByMovie(movie);
        List<ActingPerson> producers = dao.getProducersByMovie(movie);
        List<ActingPerson> screenWriters = dao.getScreenWritersByMovie(movie);
        List<Genre> movieGenres = iMovieDAO.getMovieGenres(movie);

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("movie", movie);
        request.setAttribute("date", movie.getReleaseDate().toGMTString());
        request.setAttribute("genres", movieGenres);

        request.setAttribute("directors", directors);
        request.setAttribute("actors", actors);
        request.setAttribute("screenWriters", producers);
        request.setAttribute("producers", screenWriters);
    }
}
