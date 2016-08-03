package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.MovieDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        Assert.assertNotNull(movieDAO.get(1));
    }


}
