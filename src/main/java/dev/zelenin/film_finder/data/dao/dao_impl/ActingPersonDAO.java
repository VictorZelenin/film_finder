package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingRole;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.executor.Executor;
import dev.zelenin.film_finder.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO треба поставити у відповідність, кожну роль у ств фільму із кожним фільмом! Редизайн
/**
 * Created by victor on 04.08.16.
 */
public class ActingPersonDAO extends DAO<ActingPerson> implements IActingPersonDAO {
    public ActingPersonDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(ActingPerson object) {
        String insertQuery = "insert into acting_people(name, gender, height, country, age, death_date," +
                " total_movies_number, average_client_mark, photo_url) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            return putActingPerson(object, insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public ActingPerson get(long id) {
        String query = "select * from acting_people where id = " + id;

        try {
            return Executor.executeQuery(connection, query, resultSet -> {
                resultSet.next();

                return constructActingPeople(resultSet);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getAll() {
        String query = "select * from acting_people";
        List<ActingPerson> people = new ArrayList<>();

        try {
            return fillUpList(people, query);
        } catch (SQLException e) {
            e.printStackTrace();
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

        try {
            return putActingPerson(newObject, updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int remove(ActingPerson object) {
        String removeQuery = "delete from acting_people where id = " + object.getId();
        int updated = 0;
        try {
            updated = Executor.executeUpdate(connection, removeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public int removeAll() {
        String removeAllQuery = "delete from acting_people";
        int updated = 0;

        try {
            updated = Executor.executeUpdate(connection, removeAllQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public List<ActingPerson> getByMovie(Movie movie) {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date,  " +
                "total_movies_number, acting_people.average_client_mark, photo_url from acting_person_movies" +
                " join acting_people on acting_person_id = acting_people.id " +
                " where movie_id = " + movie.getId();
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingRole> getActingPersonRoleList(ActingPerson actingPerson) {
        String query = "select role from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "where acting_person_id = " + actingPerson.getId();
        List<ActingRole> roles = new ArrayList<>();

        try {
            Executor.executeQuery(connection, query, resultSet -> {
                while (resultSet.next()) {
                    roles.add(ActingRole.valueOf(resultSet.getString(1).toUpperCase()));
                }

                return roles;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    @Override
    public List<Genre> getActingPersonGenreList(ActingPerson actingPerson) {
        String query = "select genre from acting_person_genres " +
                "join genres on genres.id = genre_id " +
                "where acting_person_id = " + actingPerson.getId();
        List<Genre> genres = new ArrayList<>();

        try {
            return Util.getGenres(genres, connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // TODO not tested
    @Override
    public List<ActingPerson> getByRegExp(String regexp) {
        String query = "select * from acting_people where name regexp '" + regexp + "'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getByCountry(String country) {
        String query = "select * from acting_people where country = '" + country + "'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getAliveActingPeople() {
        String query = "select * from acting_people " +
                "where death_date is null || death_date = '0000-00-00'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getDeadActingPeople() {
        String query = "select * from acting_people " +
                "where death_date is not null && death_date != '0000-00-00'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getByTotalMovieQuantity(int value) {
        String query = "select * from acting_people where total_movies_number > " + value;
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getByClientsMark(double value) {
        String query = "select * from acting_people where average_client_mark = " + value;
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // TODO not tested
    @Override
    public List<ActingPerson> getByTemplate(String template) {
        String query = "select * from acting_people where name like '" + template + "'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getDirectors() {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where role = 'director'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getProducers() {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where role = 'producer'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPerson> getScreenWriters() {
        String query = "select acting_people.id, name, gender, height, acting_people.country, age, death_date, " +
                "total_movies_number, acting_people.average_client_mark, photo_url " +
                "from acting_person_roles " +
                "join roles_in_movie on roles_in_movie.id = role_id " +
                "join acting_people on acting_people.id = acting_person_id " +
                "where role = 'screen_writer'";
        List<ActingPerson> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // TODO Map<ROLE, List<ActingPerson>>
    @Override
    public List<ActingPerson> getActorsByMovie(Movie movie) {
//        List<ActingPerson> list = this.getByMovie(movie);

//        for ()

        return null;
    }

    @Override
    public List<ActingPerson> getDirectorsByMovie(Movie movie) {
        return null;
    }

    @Override
    public List<ActingPerson> getProducersByMovie(Movie movie) {
        return null;
    }

    @Override
    public List<ActingPerson> getScreenWritersByMovie(Movie movie) {
        return null;
    }

    @Override
    public boolean exists(ActingPerson person) {
        return person != null && this.get(person.getId()) != null;
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
}
