package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.executor.Executor;
import dev.zelenin.film_finder.utils.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by victor on 04.08.16.
 */
public class ActingPersonDAO extends DAO<ActingPerson> implements IActingPersonDAO {
    private static final Logger LOG = LogManager.getLogger(ActingPersonDAO.class);

    public ActingPersonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(ActingPerson object) {
        String insertQuery = "insert into acting_people(name, gender, height, country, age, death_date," +
                " total_movies_number, average_client_mark, photo_url) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        LOG.trace("QUERY: " + insertQuery);
        int updated = -1;

        try {
            updated = putActingPerson(object, insertQuery);
            LOG.info("Acting Person saved correctly");
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Acting person can not be saved");
        }

        return updated;
    }

    @Override
    public int rowsCount() {
        return rowsCount("acting_people");
    }

    @Override
    public ActingPerson get(long id) {
        String query = "select * from acting_people where id = " + id;
        LOG.trace("QUERY: " + query);

        try {
            return Executor.executeQuery(connection, query, resultSet -> {
                resultSet.next();
                ActingPerson person = constructActingPeople(resultSet);
                LOG.info("returned acting person: " + person);

                return person;
            });
        } catch (SQLException e) {
            LOG.error("Acting person can not be returned");
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getAll() {
        String query = "select * from acting_people";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> people = new ArrayList<>();

        try {
            people = fillUpList(people, query);
            LOG.info("returned: " + people);

            return people;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not get list of acting people, returned null");
        }

        return null;
    }

    @Override
    public int update(long id, ActingPerson newObject) {
        String updateQuery = "update acting_people" +
                " set " +
                "name = ?, " +
                "gender = ?, " +
                "height = ?, " +
                "country = ?, " +
                "age = ?, " +
                "death_date = ?, " +
                "total_movies_number = ?, " +
                "average_client_mark = ?, " +
                "photo_url = ? " +
                "where id = " + id;
        LOG.trace("QUERY: " + updateQuery);

        int updated = -1;

        try {
            updated = putActingPerson(newObject, updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not update acting person");
        }

        LOG.info("returned, updated rows: " + updated);
        return updated;
    }

    @Override
    public int remove(ActingPerson object) {
        String removeQuery = "delete from acting_people where id = " + object.getId();
        LOG.trace("QUERY: " + removeQuery);
        int updated = -1;

        try {
            updated = Executor.executeUpdate(connection, removeQuery);
            LOG.info("Updated rows: " + updated);
        } catch (SQLException e) {
            LOG.error("can not remove acting person");
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public int removeAll() {
        String removeAllQuery = "delete from acting_people";
        LOG.trace("QUERY: " + removeAllQuery);
        int updated = 0;

        try {
            updated = Executor.executeUpdate(connection, removeAllQuery);
        } catch (SQLException e) {
            LOG.error("can not delete all");
            e.printStackTrace();
        }

        LOG.info("returned(updated rows): " + updated);
        return updated;
    }

    @Override
    public List<ActingPerson> getByMovie(Movie movie) {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date,  " +
                "total_movies_number, acting_people.average_client_mark, photo_url from acting_person_movies" +
                " join acting_people on acting_person_id = acting_people.id " +
                " where movie_id = " + movie.getId();
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned list by movie(" + movie + ")");

            return null;
        }
    }

    @Override
    public List<ActingRole> getActingPersonRoleList(ActingPerson actingPerson) {
        String query = "select role from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "where acting_person_id = " + actingPerson.getId();
        LOG.trace("QUERY: " + query);
        List<ActingRole> roles = new ArrayList<>();

        try {
            Executor.executeQuery(connection, query, resultSet -> {
                while (resultSet.next()) {
                    roles.add(ActingRole.valueOf(resultSet.getString(1).toUpperCase()));
                }

                LOG.info("Roles of acting person -  " + actingPerson + ": " + roles);
                return roles;
            });
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned roles of acting person - " + actingPerson);
        }

        return roles;
    }

    @Override
    public List<Genre> getActingPersonGenreList(ActingPerson actingPerson) {
        String query = "select genre from acting_person_genres " +
                "join genres on genres.id = genre_id " +
                "where acting_person_id = " + actingPerson.getId();
        LOG.trace("QUERY: " + query);
        List<Genre> genres = new ArrayList<>();

        try {
            return Util.getGenres(genres, connection, query);
        } catch (SQLException e) {
            LOG.error("can not returned acting people by genres");
            e.printStackTrace();
            return null;
        }
    }

    // TODO not tested
    @Override
    public List<ActingPerson> getByRegExp(String regexp) {
        String query = "select * from acting_people where name regexp '" + regexp + "'";
        List<ActingPerson> list = new ArrayList<>();
        LOG.trace("QUERY: " + query);

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            LOG.error("can not returned by regexp(" + regexp + ")");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<ActingPerson> getByCountry(String country) {
        String query = "select * from acting_people where country = '" + country + "'";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned acting person list by country - " + country);

            return null;
        }
    }

    @Override
    public List<ActingPerson> getAliveActingPeople() {
        String query = "select * from acting_people " +
                "where death_date is null || death_date = '0000-00-00'";
        LOG.trace("QUERY: " + query);

        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned alive acting people");

            return null;
        }
    }

    @Override
    public List<ActingPerson> getDeadActingPeople() {
        String query = "select * from acting_people " +
                "where death_date is not null && death_date != '0000-00-00'";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned dead acting people");

            return null;
        }
    }

    @Override
    public List<ActingPerson> getByTotalMovieQuantity(int value) {
        String query = "select * from acting_people where total_movies_number > " + value;
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned acting people by total movie quantity: " + value);

            return null;
        }
    }

    @Override
    public List<ActingPerson> getByClientsMark(double value) {
        String query = "select * from acting_people where average_client_mark = " + value;
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned acting people by clients mark");

            return null;
        }
    }

    // TODO not tested
    @Override
    public List<ActingPerson> getByTemplate(String template) {
        String query = "select * from acting_people where name like '" + template + "'";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned acting people by template");

            return null;
        }
    }

    // TODO rewrite into Map
    @Override
    public List<ActingPerson> getActors() {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where role = 'actor'";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned actors");

            return null;
        }
    }

    @Override
    public List<ActingPerson> getDirectors() {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where role = 'director'";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned directors");

            return null;
        }
    }

    @Override
    public List<ActingPerson> getProducers() {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where role = 'producer'";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned producers");

            return null;
        }
    }

    @Override
    public List<ActingPerson> getScreenWriters() {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where role = 'screen_writer'";
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned screen writers");

            return null;
        }
    }

    @Override
    public List<ActingPerson> getActorsByMovie(Movie movie) {
        // movie_id must be checked
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_movies " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where isActor = 1 && movie_id = " + movie.getId();
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned actors by movie: " + movie);

            return null;
        }
    }

    @Override
    public List<ActingPerson> getDirectorsByMovie(Movie movie) {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_movies " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where isDirector = 1 && movie_id = " + movie.getId();
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can ont returned directors by movie: " + movie);

            return null;
        }
    }

    @Override
    public List<ActingPerson> getProducersByMovie(Movie movie) {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_movies " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where isProducer = 1 && movie_id = " + movie.getId();
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned producers by movie: " + movie);

            return null;
        }

    }

    @Override
    public List<ActingPerson> getScreenWritersByMovie(Movie movie) {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_movies " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where isScreenWriter = 1 && movie_id = " + movie.getId();
        LOG.trace("QUERY: " + query);
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not returned screen writers by movie: " + movie);

            return null;
        }
    }

    @Override
    public int addNewGenre(ActingPerson actingPerson, Genre genre) {
        String query = String.format("insert into acting_person_genres values(%d, %d)", actingPerson.getId(),
                genre.ordinal() + 1);
        LOG.trace("QUERY: " + query);

        try {
            return Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not add new genre" + genre + " to acting person: " + actingPerson);

            return -1;
        }
    }

    @Override
    public int addNewMovie(ActingPerson actingPerson, Movie movie, boolean isActor,
                           boolean isDirector, boolean isProducer, boolean isScreenWriter) {
        // checking
        StringBuilder query = new StringBuilder("insert into acting_person_movies values(");

        query.append(actingPerson.getId()).append(", ")
                .append(movie.getId()).append(", ")
                .append(convertBooleanIntoInt(isActor)).append(", ")
                .append(convertBooleanIntoInt(isDirector)).append(", ")
                .append(convertBooleanIntoInt(isProducer)).append(", ")
                .append(convertBooleanIntoInt(isScreenWriter)).append(");");
        LOG.trace("QUERY: " + query.toString());

        try {
            return Executor.executeUpdate(connection, query.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not add new movie: " + movie + " to acting person: " + actingPerson);

            return -1;
        }
    }

    @Override
    public int addNewRole(ActingPerson person, ActingRole role) {
        String query = String.format("insert into acting_person_roles values(%d, %d)",
                person.getId(), role.ordinal() + 1);
        LOG.trace("QUERY: " + query);

        try {
            return Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("can not add new role: " + role + " to acting person: " + person);

            return -1;
        }
    }

    private List<ActingPerson> fillUpList(List<ActingPerson> people, String query) throws SQLException {

        Executor.executeQuery(connection, query, resultSet -> {
            while (resultSet.next()) {
                people.add(constructActingPeople(resultSet));
            }

            return people;
        });


        return people;
    }

    private int putActingPerson(ActingPerson actingPerson, String query) throws SQLException {
        int updated;

        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, actingPerson.getName());
        statement.setString(2, Util.parseStringGender(actingPerson.getGender()));
        statement.setDouble(3, actingPerson.getHeight());
        statement.setString(4, actingPerson.getCountry());
        statement.setInt(5, actingPerson.getAge());
        statement.setDate(6, Util.parseSQLDate(actingPerson.getDeathDate()));
        statement.setInt(7, actingPerson.getTotalMoviesNumber());
        statement.setDouble(8, actingPerson.getAverageClientMark());
        statement.setString(9, actingPerson.getPhotoURL());

        statement.execute();
        updated = statement.getUpdateCount();

        statement.close();
        return updated;
    }

    private ActingPerson constructActingPeople(ResultSet resultSet) throws SQLException {

        return new ActingPerson(
                resultSet.getLong(1),
                resultSet.getString(2),
                Util.parseGender(resultSet.getString(3)),
                resultSet.getDouble(4),
                resultSet.getString(5),
                resultSet.getInt(6),
                Util.parseUtilDateFromSQLDate(resultSet, 7),
                resultSet.getInt(8),
                resultSet.getDouble(9),
                resultSet.getString(10));

    }

    private int convertBooleanIntoInt(boolean value) {
        return value ? 1 : 0;
    }
}
