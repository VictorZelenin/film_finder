package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;

import java.util.List;

/**
 * Created by victor on 03.08.16.
 */
public interface IClientDAO extends IDAO<Client> {
    List<Movie> findClientChosenMovies(Client client);

    List<MovieMark> findClientMovieMarks(Client client);

    List<ActingPersonMark> findClientActingPersonMarks(Client client);

    List<Client> findClientsByGender(Gender gender);

    int addNewMovie(Client client, Movie movie);

    Client getClientByEmail(String email);
}
