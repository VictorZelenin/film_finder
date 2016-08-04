package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;

import java.util.List;

/**
 * Created by victor on 04.08.16.
 */
public interface IActingPersonDAO extends IDAO<ActingPerson> {
    List<ActingPerson> getByMovie(Movie movie);

    List<ActingRole> getActingPersonRoleList(ActingPerson actingPerson);

    List<Genre> getActingPersonGenreList(ActingPerson actingPerson);

    List<ActingPerson> getByRegExp(String regexp);

    List<ActingPerson> getByCountry(String country);

    List<ActingPerson> getAliveActingPeople();

    List<ActingPerson> getDeadActingPeople();

    List<ActingPerson> getByTotalMovieQuantity(int value); // more then value

    List<ActingPerson> getByClientsMark(double value);

    List<ActingPerson> getByTemplate(String template); // like as

    List<ActingPerson> getActors();

    List<ActingPerson> getDirectors();

    List<ActingPerson> getProducers();

    List<ActingPerson> getScreenWriters();

    List<ActingPerson> getActorsByMovie(Movie movie);

    List<ActingPerson> getDirectorsByMovie(Movie movie);

    List<ActingPerson> getProducersByMovie(Movie movie);

    List<ActingPerson> getScreenWritersByMovie(Movie movie);

    boolean exists(ActingPerson person);
}
