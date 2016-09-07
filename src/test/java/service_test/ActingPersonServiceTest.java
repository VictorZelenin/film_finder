package service_test;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.services.ActingPersonService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 07.09.16.
 */
public class ActingPersonServiceTest {

    @Test
    public void testSaveActingPerson() {
        List<ActingRole> roles = new ArrayList<>();
        roles.add(ActingRole.ACTOR);
        List<Genre> genres = new ArrayList<>();
        genres.add(Genre.COMEDY);
        genres.add(Genre.ANIMATION);

        Assert.assertNotNull(ActingPersonService.saveActingPerson("aa", "bb", 200, "accca", 12, null, 2, "aaa", roles, genres));
    }
}
