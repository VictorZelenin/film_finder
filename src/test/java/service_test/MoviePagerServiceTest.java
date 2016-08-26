package service_test;

import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.services.MoviePagerService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 26.08.16.
 */
public class MoviePagerServiceTest {

    @Test
    public void splitTest() {
        List<Movie> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        int a = 5;
        int b = 2;
        List<Movie> list1 = MoviePagerService.splitMovieList(list, a, b);
        System.out.println(list1);
        Assert.assertTrue(list1.size() == 1);
    }

}
