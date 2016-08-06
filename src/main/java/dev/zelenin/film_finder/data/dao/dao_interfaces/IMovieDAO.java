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

    List<Genre> getMovieGenres(Movie movie);

    List<Movie> getMoviesByGenres(List<Genre> genres);

    List<Movie> getMoviesByActingPerson(ActingPerson person);

    List<Movie> getMoviesByRegExpInTitle(String regexp);

    List<Movie> getMoviesEarlierByDate(Date date);

    List<Movie> getMoviesLaterByDate(Date date);

    List<Movie> getMoviesByCountry(String country);

    List<Movie> getMoviesByImdbRating(double rating);

    List<Movie> getTheMostChosenMovies(int value);

    List<Movie> getTheMostChosenMoviesByMale(int value);

    List<Movie> getTheMostChosenMoviesByFemale(int value);

    List<Movie> getMoviesWithHighestMarks(double lowBoundary);

    int addGenreToMovie(Movie movie, Genre genre);
}
