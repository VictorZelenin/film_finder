package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IClientDAO;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieMarkDAO;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by victor on 08.08.16.
 */
public class MovieService {
    public static List<Movie> getMoviesByImdbRating() {
        IMovieDAO dao = new DAOFactory(DatabaseManager.getConnection())
                .getMovieDAO();

        return dao.getMoviesByImdbRating();
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
        IClientDAO dao = new DAOFactory(DatabaseManager.getConnection()).getClientDAO();

        return dao.getClientChosenMovies(client);
    }

    public static List<MovieMark> getMovieMarks(Client client) {
        IMovieMarkDAO dao = new DAOFactory(DatabaseManager.getConnection()).getMovieMarkDAO();

        return dao.getMovieMarksByClient(client);
    }
}
