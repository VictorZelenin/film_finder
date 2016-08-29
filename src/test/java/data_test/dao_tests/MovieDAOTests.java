package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.MovieDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by victor on 03.08.16.
 */
public class MovieDAOTests {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
            "?useUnicode=true&characterEncoding=UTF8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private MovieDAO movieDAO;

    @Before
    public void setUp() throws SQLException {

        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        movieDAO = new MovieDAO(connection);
    }

    @Test
    public void testGet() {
        Movie movie = movieDAO.find(1);
        System.out.println(movie);
        assertNotNull(movie);
    }

    @Test
    public void saveTest() {
        assertTrue(movieDAO.save(new Movie()) == 1);
    }

    @Test
    public void getAllTest() {
        List<Movie> movies = movieDAO.findAll();
        System.out.println(movies);
        assertTrue(movies.size() > 1);
    }

    @Test
    public void updateTest() {
        Movie movie = movieDAO.find(3);

        assertTrue(movieDAO.update(7, movie) == 1);
    }

    @Test
    public void removeTest() {
        assertTrue(movieDAO.remove(movieDAO.find(7)) == 1);
    }

    @Test
    public void getGenres() {
        List<Genre> genres = movieDAO.findMovieGenres(movieDAO.find(6));
        System.out.println(genres);
        assertTrue(genres.size() > 1);
    }

    @Test
    public void getMoviesByGenres() {
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.COMEDY);
        genres.add(Genre.CRIME);

        List<Movie> movies = movieDAO.findMoviesByGenres(genres);
        System.out.println(movies);
        assertTrue(movies.size() > 1);
    }

    @Test
    public void getMoviesByActingPerson() {
        ActingPerson actingPerson = new ActingPerson();
        actingPerson.setId(3);
        List<Movie> movies = movieDAO.findMoviesByActingPerson(actingPerson);
        System.out.println(movies);
        assertTrue(movies.size() > 0);
    }

    @Test
    public void getMoviesByDate() {
        List<Movie> movies = movieDAO.findMoviesEarlierByDate(new Date("01 Jan 2003"));
        System.out.println(movies);
        assertTrue(movies.size() > 1);
    }

    @Test
    public void getMoviesByCountry() {
        List<Movie> movies = movieDAO.findMoviesByCountry("СССР");
        System.out.println(movies);
        assertNotNull(movies);
        assertTrue(movies.size() > 0);
    }

    @Test
    public void getMoviesByImdbRating() {
        List<Movie> movies = movieDAO.getMoviesByImdbRating(8.5);
        System.out.println(movies);
        assertTrue(movies.size() > 1);
    }

    @Test
    public void getTheMostPopularMovies() {
        List<Movie> movies = movieDAO.findTheMostChosenMovies(1);
        System.out.println(movies);
        assertTrue(movies.size() > 1);
    }

    @Test
    public void getMoviesByMales() {
        List<Movie> movies = movieDAO.findTheMostChosenMoviesByMale(1);
        System.out.println(movies);
        assertTrue(movies.size() > 1);
    }

    @Test
    public void getMoviesByFemales() {
        List<Movie> movies = movieDAO.findTheMostChosenMoviesByFemale(0);
        System.out.println(movies);
        assertTrue(movies.size() > 0);
    }

    @Test
    public void searchQueryTest() {
        List<Movie> movies = movieDAO.findMoviesBySearchQuery("select distinct " +
                "id, title, movie_type, release_date, runtime, plot, country, imdb_rating, imdb_votes, " +
                "average_client_mark, poster_url from movies " +
                " left join acting_person_movies on acting_person_movies.movie_id = movies.id " +
                " join movies_genres on movies.id = movies_genres.movie_id " +
                "where id = 1");

        System.out.println(movies);
        assertTrue(movies.size() == 1);
    }
}
