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
    int savePersonRoles(ActingPerson person, List<ActingRole> roles);

    int savePersonGenres(ActingPerson person, List<Genre> genres);

    List<ActingPerson> findByMovie(Movie movie);

    List<ActingRole> findAllRoles();

    List<ActingRole> findActingPersonRoleList(ActingPerson actingPerson);

    ActingPerson findActingPersonByName(String name);

    ActingPerson findActingPersonByLastName(String lastName);

    List<Genre> findActingPersonGenreList(ActingPerson actingPerson);

    List<ActingPerson> findByRegExp(String regexp);

    List<ActingPerson> findByCountry(String country);

    List<ActingPerson> findAliveActingPeople();

    List<ActingPerson> findDeadActingPeople();

    List<ActingPerson> findByTotalMovieQuantity(int value); // more then value

    List<ActingPerson> findByClientsMark(double value);

    List<ActingPerson> findByTemplate(String template); // like as

    List<ActingPerson> findActors();

    List<ActingPerson> findDirectors();

    List<ActingPerson> findProducers();

    List<ActingPerson> findScreenWriters();

    List<ActingPerson> findActorsByMovie(Movie movie);

    List<ActingPerson> findDirectorsByMovie(Movie movie);

    List<ActingPerson> findProducersByMovie(Movie movie);

    List<ActingPerson> findScreenWritersByMovie(Movie movie);

    int addNewGenre(ActingPerson actingPerson, Genre genre);

    int addNewMovie(ActingPerson actingPerson, Movie movie, boolean isActor, boolean isDirector,
                    boolean isProducer, boolean isScreenWriter);

    int addNewRole(ActingPerson person, ActingRole role);
}
