package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.ActingPersonDAO;
import dev.zelenin.film_finder.data.dao.dao_impl.ActingPersonMarkDAO;
import dev.zelenin.film_finder.data.dao.dao_impl.ClientDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
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
public class ActingPersonMarkDAOTests {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
            "?useUnicode=true&characterEncoding=UTF8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private ActingPersonMarkDAO markDAO;

    @Before
    public void setUp() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        markDAO = new ActingPersonMarkDAO(connection);
    }

    @Test
    public void getTest() {
        ActingPersonMark mark = markDAO.get(6);
        System.out.println(mark);
        assertNotNull(mark);
    }

    @Test
    public void saveTest() {
        ActingPerson actingPerson = new ActingPersonDAO(connection).get(16);
        Client client = new ClientDAO(connection).get(18);
        ActingPersonMark mark = new ActingPersonMark(10, null, null, actingPerson, client);
        assertTrue(markDAO.save(mark) == 1);
    }

    @Test
    public void getAllTest() {
        List<ActingPersonMark> marks = markDAO.getAll();
        System.out.println(marks);
        assertTrue(marks.size() > 0);
    }

    @Test
    public void updateTest() {

        ActingPersonMark mark = new ActingPersonMark();
        mark.setMark(9);

        assertTrue(markDAO.update(6, mark) == 1);
    }

    @Test
    public void removeTest() {
        assertTrue(markDAO.remove(markDAO.get(6)) == 1);
    }

    @Test
    public void existsTest() {
        assertTrue(markDAO.exists(markDAO.get(1)));
    }

    @Test
    public void getByClient(){
        List<ActingPersonMark> marks = markDAO.getActingPersonMarksByClient(new ClientDAO(connection).get(2));
        System.out.println(marks);
        assertTrue(marks.size() > 0);
    }


}
