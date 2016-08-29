package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;

import java.util.List;

/**
 * Created by victor on 04.08.16.
 */
public interface IMovieMarkDAO extends IDAO<MovieMark> {
    List<MovieMark> findMovieMarksByClient(Client client);

    List<MovieMark> findMovieMarksByMovie(Movie movie);

}
