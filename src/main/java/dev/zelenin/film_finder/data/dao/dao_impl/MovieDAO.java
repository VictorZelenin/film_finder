package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static dev.zelenin.film_finder.utils.Util.parseMovieType;

/**
 * Created by victor on 03.08.16.
 */
public class MovieDAO extends DAO<Movie> implements IMovieDAO {

    public MovieDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(Movie object) throws SQLException {
        return 0;
    }

    @Override
    public Movie get(long id) {
        String selectClientByIdQuery = "select * from movies where id =" + id;

        try {
            return Executor.executeQuery(connection, selectClientByIdQuery, resultSet -> {
                resultSet.next();

                return new Movie(resultSet.getLong(1), resultSet.getString(2),
                        parseMovieType(resultSet.getString(3)), new Date(resultSet.getDate(4).getTime()),
                        resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7),
                        resultSet.getDouble(8), resultSet.getDouble(9), resultSet.getDouble(10),
                        resultSet.getString(11));
            });
            // logging
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
        }
        return null;
    }

    @Override
    public List<Movie> getAll() {
        return null;
    }

    @Override
    public int update(long id, Movie newObject) {
        return 0;
    }

    @Override
    public int remove(Movie object) {
        return 0;
    }

    @Override
    public int removeAll() {
        return 0;
    }
}
