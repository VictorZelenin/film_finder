package service_test;

import dev.zelenin.film_finder.data.dao.dao_impl.ClientDAO;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.MovieService;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by victor on 22.08.16.
 */
public class MovieServiceTest {


    @Test
    public void getMovieMarksTest() throws SQLException {
        final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
                "?useUnicode=true&characterEncoding=UTF8";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        ClientDAO dao = new ClientDAO(connection);
        Client client = dao.get(1);

        List<MovieMark> list = MovieService.getMovieMarks(client);
        System.out.println(list);
        Assert.assertNotNull(list);
    }
}
