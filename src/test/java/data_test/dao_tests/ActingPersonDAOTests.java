package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.ActingPersonDAO;
import dev.zelenin.film_finder.data.dao.dao_impl.MovieDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by victor on 04.08.16.
 */
public class ActingPersonDAOTests {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
            "?useUnicode=true&characterEncoding=UTF8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private ActingPersonDAO actingPersonDAO;

    @Before
    public void setUp() throws SQLException {

        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        actingPersonDAO = new ActingPersonDAO(connection);
    }

    @Test
    public void saveTest() {
        assertTrue(actingPersonDAO.save(new ActingPerson()) == 1);
    }

    @Test
    public void getTest() {
        ActingPerson person = actingPersonDAO.get(3);
        System.out.println(person);
        assertNotNull(person);
    }

    @Test
    public void getAllTest() {
        List<ActingPerson> personList = actingPersonDAO.getAll();
        System.out.println(personList);
        assertTrue(personList.size() > 1);
    }

    @Test
    public void updateTest() {
        assertTrue(actingPersonDAO.update(11, new ActingPerson()) == 1);
    }

    @Test
    public void removeTest() {
        assertTrue(actingPersonDAO.remove(actingPersonDAO.get(12)) == 1);
    }

    @Test
    public void getByMovieTest() {
        List<ActingPerson> personList = actingPersonDAO.getByMovie(
                new MovieDAO(connection).get(1));
        System.out.println(personList);
        assertTrue(personList.size() > 0);
    }

    @Test
    public void getRolesTest() {
        List<ActingRole> roles = actingPersonDAO.getActingPersonRoleList(actingPersonDAO.get(4));
        System.out.println(roles);
        assertTrue(roles.size() > 0);
    }

    @Test
    public void getGenresTest() {
        List<Genre> genres = actingPersonDAO.getActingPersonGenreList(actingPersonDAO.get(4));
        System.out.println(genres);
        assertTrue(genres.size() > 0);
    }

    @Test
    public void getByCountryTest() {
        List<ActingPerson> list = actingPersonDAO.getByCountry("USA");
        System.out.println(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void getAliveTest() {
        List<ActingPerson> list = actingPersonDAO.getAliveActingPeople();
        System.out.println(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void getDeadTest() {
        List<ActingPerson> list = actingPersonDAO.getDeadActingPeople();
        System.out.println(list);
        assertTrue(list.size() > 0);
    }

    @Test
    public void getByTotalMoviesTest() {
        List<ActingPerson> list = actingPersonDAO.getByTotalMovieQuantity(40);
        System.out.println(list);
        assertTrue(list.size() > 1);
    }

    @Test
    public void getActorsTest() {
        List<ActingPerson> actingPersons = actingPersonDAO.getActors();
        System.out.println(actingPersons);
        assertTrue(actingPersons.size() > 1);
    }
}
