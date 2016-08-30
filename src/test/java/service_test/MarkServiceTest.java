package service_test;

import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.movies.MovieType;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;
import dev.zelenin.film_finder.services.MarkService;
import org.junit.Test;

import java.util.Date;

/**
 *
 */
public class MarkServiceTest {

    @Test
    public void testAddingMovieMark() {
        MarkService.addMovieMark(7, new Date(), "", new Movie(1, "", MovieType.EPISODE, new Date(), 100, "",
                "", 0, 0, 0, ""), new Client(0, "", Gender.FEMALE, "", ""));
    }
}
