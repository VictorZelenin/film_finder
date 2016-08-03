package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.ClientDAO;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by victor on 03.08.16.
 */
// TODO create a sets of tests
public class ClientDAOTests {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
            "?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private ClientDAO clientDAO;

    @Before
    public void setUp() throws SQLException {

        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        clientDAO = new ClientDAO(connection);
    }

    @Test
    public void testSave() {
        Client client = new Client("test11", Gender.FEMALE, "1unique5Test3", "test", "test");

        assertTrue(clientDAO.save(client) == 1);
    }

    // passed
    @Test
    public void testSaveUTF8() {
        Client client = new Client("тест2", Gender.UNKNOWN, "тест2дада", "тест2", "тест2");

        assertTrue(clientDAO.save(client) == 1);
    }

    @Test
    public void testGet() {
        Client client = clientDAO.get(1);
        System.out.println(client.getGender());
        assertNotNull(client);
    }

    @Test
    public void testGetAll() {
        List<Client> clients = clientDAO.getAll();

        System.out.println(clients);

        assertNotNull(clients);
        assertTrue(clients.size() > 0);
    }

    @Test
    public void testUpdate() {
        Client client = new Client();
        client.setId(11);
        client.setName("client_guest");
        client.setEmail("33client_email");

        assertTrue(clientDAO.update(4, client) == 1);
    }

    @Test
    public void testDelete() {
        assertTrue(clientDAO.remove(clientDAO.get(0)) == 1);
    }

    @Test
    public void testGetChosenMovies() {
        List<Movie> movies = clientDAO.getClientChosenMovies(clientDAO.get(2));
        System.out.println(movies);
        assertTrue(movies.size() > 1);
    }

    @Test
    public void testGetMovieMarks() {
        List<MovieMark> movieMarks = clientDAO.getClientMovieMarks(clientDAO.get(1));
        System.out.println(movieMarks);
        assertTrue(movieMarks.size() > 1);
    }

    @Test
    public void testGetPersonMarks() {
        List<ActingPersonMark> movieMarks = clientDAO.getClientActingPersonMarks(clientDAO.get(1));
        System.out.println(movieMarks);
        assertTrue(movieMarks.size() > 1);
    }

    @Test
    public void testGetClientsByGender() {
        assertTrue(clientDAO.getClientsByGender(Gender.MALE).size() > 1);
    }

}
