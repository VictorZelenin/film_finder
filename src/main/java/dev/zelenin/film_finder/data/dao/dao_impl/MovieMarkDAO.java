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
import java.util.List;

import static dev.zelenin.film_finder.utils.Util.parseSQLDate;
import static dev.zelenin.film_finder.utils.Util.parseUtilDateFromSQLDate;

/**
 * Created by victor on 04.08.16.
 */
public class MovieMarkDAO extends DAO<MovieMark> implements IMovieMarkDAO {
    public MovieMarkDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(MovieMark object) {
        String insertQuery = "insert into acting_person_marks(mark, date, description, person_id, client_id) " +
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
        return null;
    }

    @Override
    public List<MovieMark> getAll() {
        return null;
    }

    @Override
    public int update(long id, MovieMark newObject) {
        return 0;
    }

    @Override
    public int remove(MovieMark object) {
        return 0;
    }

    @Override
    public int removeAll() {
        return 0;
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

    private Movie constructMovie(ResultSet resultSet) {
        return new Movie(

        );
    }

    private Client constructClient(ResultSet resultSet) {
        return new Client(

        );
    }

}
