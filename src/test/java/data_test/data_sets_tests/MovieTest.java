package data_test.data_sets_tests;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.exceptions.IncompatibleActingRoleException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void testMovieDefaultConstructor() {
        assertNotNull(movie.getActorsList());
        assertNotNull(movie.getGenres());
        assertNotNull(movie.getScreenWriters());
        assertNotNull(movie.getProducers());
    }

    @Test(expected = IncompatibleActingRoleException.class)
    public void testAddingActor() {
        ActingPerson person = mock(ActingPerson.class);
        Set<ActingRole> roles = new HashSet<>();
        roles.add(ActingRole.PRODUCER);
        roles.add(ActingRole.SCREEN_WRITER);
        when(person.getRolesList()).thenReturn(roles);

        movie.addActor(person);
    }

    @Test
    public void testEquals2Movies() {
        Movie movie2 = new Movie();

        assertEquals(movie2, movie);
    }
}
