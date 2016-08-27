package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;

import java.util.Date;
import java.util.List;

/**
 * Created by victor on 03.08.16.
 */
public interface IMovieDAO extends IDAO<Movie> {

    int addGenreToMovie(Movie movie, Genre genre);

    List<Genre> findMovieGenres(Movie movie);

    List<Movie> findMoviesByGenre(Genre genre);

    List<Movie> findMoviesByGenres(List<Genre> genres);

    List<Movie> findMoviesByActingPerson(ActingPerson person);

    List<Movie> findMoviesByRegExpInTitle(String regexp);

    List<Movie> findMoviesEarlierByDate(Date date);

    List<Movie> findMoviesLaterByDate(Date date);

    List<Movie> findMoviesByCountry(String country);

    List<Movie> findMoviesByImdbRating();

    List<Movie> findTheMostChosenMovies(int value);

    List<Movie> findTheMostChosenMoviesByMale(int value);

    List<Movie> findTheMostChosenMoviesByFemale(int value);

    List<Movie> findMoviesWithHighestMarks(double lowBoundary);

    List<Movie> findMoviesByRatingInRange(int limit, int offset);

    List<Movie> findMoviesBySearchQuery(String query);
}
