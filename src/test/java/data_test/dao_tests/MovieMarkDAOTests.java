package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.ClientDAO;
import dev.zelenin.film_finder.data.dao.dao_impl.MovieDAO;
import dev.zelenin.film_finder.data.dao.dao_impl.MovieMarkDAO;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by victor on 06.08.16.
 */
public class MovieMarkDAOTests {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
            "?useUnicode=true&characterEncoding=UTF8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private MovieMarkDAO dao;

    @Before
    public void setUp() throws SQLException {

        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        dao = new MovieMarkDAO(connection);
    }

    @Test
    public void getTest() {
        assertNotNull(dao.find(1));
    }

    @Test
    public void saveTest() {
        Client client = new ClientDAO(connection).find(13);
        Movie movie = new MovieDAO(connection).find(6);
        MovieMark mark = new MovieMark(5, null, null, movie, client);
        assertTrue(dao.save(mark) == 1);
    }

    @Test
    public void updateTest() {
        MovieMark mark = new MovieMark();
        mark.setMark(4);
        assertTrue(dao.update(5, mark) == 1);
    }

    @Test
    public void getAllTest() {
        List<MovieMark> movieMarks = dao.findAll();
        System.out.println(movieMarks);

        assertTrue(movieMarks.size() > 1);
    }

}
