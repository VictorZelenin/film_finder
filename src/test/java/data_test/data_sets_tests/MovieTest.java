package data_test.data_sets_tests;

import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by victor on 01.08.16.
 */
public class MovieTest {
    private Movie movie;

    @Before
    public void setUp() {
        movie = new Movie();
    }

    @Test
    public void testEquals2Movies() {
        Movie movie2 = new Movie();

        assertEquals(movie2, movie);
    }
}
