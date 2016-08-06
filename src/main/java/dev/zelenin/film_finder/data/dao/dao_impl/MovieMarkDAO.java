package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieMarkDAO;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.executor.Executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dev.zelenin.film_finder.utils.Util.*;

/**
 * Created by victor on 04.08.16.
 */
public class MovieMarkDAO extends DAO<MovieMark> implements IMovieMarkDAO {
    public MovieMarkDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(MovieMark object) {
        String insertQuery = "insert into movie_marks(mark, date, description, movie_id, client_id) " +
                "values(?, ?, ?, ?, ?)";

        try {
            return putMovieMark(object, insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    @Override
    public MovieMark get(long id) {
        String query = "select movie_marks.id, mark, movie_marks.date, description, " +
                "movies.id, title, movie_type,release_date, runtime, plot, country, imdb_rating, imdb_votes, " +
                "average_client_mark, poster_url, clients.id, clients.name, clients.gender, email, password, " +
                "clients.photo_url from movie_marks " +
                "join movies on movies.id = movie_id " +
                "join clients on clients.id = client_id " +
                "where movie_marks.id = " + id;

        try {
            return Executor.executeQuery(connection, query, resultSet -> {
                resultSet.next();
                return constructMovieMark(resultSet);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<MovieMark> getAll() {
        String query = "select movie_marks.id, mark, movie_marks.date, description, " +
                "movies.id, title, movie_type,release_date, runtime, plot, country, imdb_rating, imdb_votes, " +
                "average_client_mark, poster_url, clients.id, clients.name, clients.gender, email, password, " +
                "clients.photo_url from movie_marks " +
                "join movies on movies.id = movie_id " +
                "join clients on clients.id = client_id ";
        List<MovieMark> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int update(long id, MovieMark newObject) {
        String query = "update movie_marks " +
                "set " +
                "mark = ?, " +
                "date = ?, " +
                "description = ? " +
                "where id = " + id;

        PreparedStatement statement = null;
        int updated = 0;

        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, newObject.getMark());
            statement.setDate(2, parseSQLDate(newObject.getDate()));
            statement.setString(3, newObject.getDescription());
            statement.execute();

            updated = statement.getUpdateCount();

        } catch (SQLException e) {
            e.printStackTrace();
            updated = -1;
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return updated;
    }

    @Override
    public int remove(MovieMark object) {
        String query = "delete from movie_marks where id = " + object.getId();

        try {
            return Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int removeAll() {
        String query = "delete from movie_marks";

        try {
            return Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public List<MovieMark> getMovieMarksByClient(Client client) {
        String query = "select movie_marks.id, mark, movie_marks.date, description, " +
                "movies.id, title, movie_type,release_date, runtime, plot, country, imdb_rating, imdb_votes, " +
                "average_client_mark, poster_url, clients.id, clients.name, clients.gender, email, password, " +
                "clients.photo_url from movie_marks " +
                "join movies on movies.id = movie_id " +
                "join clients on clients.id = client_id " +
                "where client_id = " + client.getId();
        List<MovieMark> list = new ArrayList<>();

        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<MovieMark> getMovieMarksByMovie(Movie movie) {
        String query = "select movie_marks.id, mark, movie_marks.date, description, " +
                "movies.id, title, movie_type,release_date, runtime, plot, country, imdb_rating, imdb_votes, " +
                "average_client_mark, poster_url, clients.id, clients.name, clients.gender, email, password, " +
                "clients.photo_url from movie_marks " +
                "join movies on movies.id = movie_id " +
                "join clients on clients.id = client_id " +
                "where movie_id = " + movie.getId();
        List<MovieMark> list = new ArrayList<>();


        try {
            return fillUpList(list, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private int putMovieMark(MovieMark mark, String query) throws SQLException {
        int updated;

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, mark.getMark());
        statement.setDate(2, parseSQLDate(mark.getDate()));
        statement.setString(3, mark.getDescription());
        statement.setLong(4, mark.getMarkedMovie().getId());
        statement.setLong(5, mark.getClient().getId());
        statement.execute();
        updated = statement.getUpdateCount();

        statement.close();

        return updated;
    }

    private List<MovieMark> fillUpList(List<MovieMark> list, String query) throws SQLException {

        Executor.executeQuery(connection, query, resultSet -> {
            while (resultSet.next()) {
                list.add(constructMovieMark(resultSet));
            }

            return list;
        });

        return list;
    }

    private MovieMark constructMovieMark(ResultSet resultSet) throws SQLException {
        return new MovieMark(
                resultSet.getLong(1),
                resultSet.getInt(2),
                parseUtilDateFromSQLDate(resultSet, 3),
                resultSet.getString(4),
                constructMovie(resultSet),
                constructClient(resultSet));
    }

    private Movie constructMovie(ResultSet resultSet) throws SQLException {
        return new Movie(
                resultSet.getLong(5),
                resultSet.getString(6),
                parseMovieType(resultSet.getString(7)),
                parseUtilDateFromSQLDate(resultSet, 8),
                resultSet.getInt(9),
                resultSet.getString(10),
                resultSet.getString(11),
                resultSet.getDouble(12),
                resultSet.getDouble(13),
                resultSet.getDouble(14),
                resultSet.getString(15));
    }

    private Client constructClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getLong(16),
                resultSet.getString(17),
                parseGender(resultSet.getString(18)),
                resultSet.getString(19),
                resultSet.getString(20),
                resultSet.getString(21));
    }

}
