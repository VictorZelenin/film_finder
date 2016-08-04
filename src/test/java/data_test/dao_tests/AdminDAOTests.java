package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.AdminDAO;
import dev.zelenin.film_finder.data.data_sets.users.Admin;
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
public class AdminDAOTests {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
            "?useUnicode=true&characterEncoding=UTF8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private AdminDAO adminDAO;

    @Before
    public void setUp() throws SQLException {

        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        adminDAO = new AdminDAO(connection);
    }

    @Test
    public void saveTest() {
        Admin admin = new Admin("test", "test", "test");

        assertTrue(adminDAO.save(admin) == 1);
    }

    @Test
    public void getTest() {
        Admin admin = adminDAO.get(5);
        System.out.println(admin);

        assertNotNull(admin);
    }

    @Test
    public void getAllTest() {
        List<Admin> admins = adminDAO.getAll();
        System.out.println(admins);

        assertTrue(admins.size() > 1);
    }

    @Test
    public void updateTest() {
        Admin admin = new Admin("te2", "test2", "test2");
        assertTrue(adminDAO.update(9, admin) == 1);
    }

    @Test
    public void removeTest(){
        assertTrue(adminDAO.remove(adminDAO.get(8)) == 1);
    }

}
