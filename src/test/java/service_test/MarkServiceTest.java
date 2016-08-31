package service_test;

import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.movies.MovieType;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;
import dev.zelenin.film_finder.services.MarkService;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 *
 */
public class MarkServiceTest {

    @Test
    public void testAddingMovieMark() {
        assertNotNull(MarkService.addMovieMark(7, new Date(), "", new Movie(1, "", MovieType.EPISODE,
                new Date(), 0, "", "", 0, 0, 0, ""), new Client(0, "", Gender.FEMALE, "", "")));
    }

    @Test
    public void testGettingAllMovieMarks() {
        List<MovieMark> movieMarks = MarkService.getAllMovieMarks(new Movie(1, "", MovieType.EPISODE,
                new Date(), 0, "", "", 0, 0, 0, ""));
        System.out.println(movieMarks);
        assertNotNull(movieMarks);
    }
}
