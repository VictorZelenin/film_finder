package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.executor.Executor;
import dev.zelenin.film_finder.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dev.zelenin.film_finder.utils.Util.*;

/**
 * Created by victor on 03.08.16.
 */
// TODO logging here
// TODO refactoring this piece of code
public class MovieDAO extends DAO<Movie> implements IMovieDAO {

    public MovieDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(Movie object) {
        String insertQuery = "insert into movies(title, movie_type, release_date, runtime, plot, country, " +
                "imdb_rating, imdb_votes, average_client_mark, poster_url) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            return putMovie(object, insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Movie get(long id) {
        String selectMovieByIdQuery = "select * from movies where id =" + id;

        try {
            return Executor.executeQuery(connection, selectMovieByIdQuery, resultSet -> {
                resultSet.next();

                return constructNewMovie(resultSet);
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
        String getAllQuery = "select * from movies";
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, getAllQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int update(long id, Movie newObject) {
        String updateQuery = "update movies set " +
                "title = ?, " +
                "movie_type = ?, " +
                "release_date = ?, " +
                "runtime = ?, " +
                "plot = ?," +
                "country = ?, " +
                "imdb_rating = ?, " +
                "imdb_votes = ?, " +
                "average_client_mark = ?, " +
                "poster_url = ? " +
                "where id = " + id;

        try {
            return putMovie(newObject, updateQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int remove(Movie object) {
        String removeQuery = "delete from movies where id = " + object.getId();
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
        String removeAllQuery = "delete from movies";
        int updated = 0;
        try {
            updated = Executor.executeUpdate(connection, removeAllQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public List<Genre> getMovieGenres(Movie movie) {
        String getGenresQuery = "select distinct genre from movies_genres " +
                "inner join genres on genres.id = genre_id" +
                " where movie_id = " + movie.getId();

        List<Genre> genres = new ArrayList<>();

        try {
            return Util.getGenres(genres, connection, getGenresQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getMoviesByGenres(List<Genre> genres) {
        StringBuilder queryBuilder = new StringBuilder();
        List<Movie> movies = new ArrayList<>();

        queryBuilder.append("select distinct movies.id, title, movie_type, release_date,runtime, plot, country, " +
                "imdb_rating, ")
                .append("imdb_votes, average_client_mark, poster_url from movies_genres")
                .append(" join movies on movies.id = movie_id")
                .append(" join genres on genres.id = genre_id")
                .append(" where ");

        for (int i = 0; i < genres.size(); i++) {
            if (i == genres.size() - 1) {
                queryBuilder.append("genre =")
                        .append("'")
                        .append(genres.get(i).toString().toLowerCase())
                        .append("'");

            } else {
                queryBuilder.append("genre =")
                        .append("'")
                        .append(genres.get(i).toString().toLowerCase())
                        .append("'")
                        .append(" or ");
            }
        }


        try {
            return fillUpMovieList(movies, queryBuilder.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getMoviesByActingPerson(ActingPerson person) {
        String getMoviesByActingPersonQuery = "select distinct movies.id, title, movie_type, release_date," +
                "runtime, plot, country, imdb_rating, imdb_votes, average_client_mark, poster_url " +
                "from acting_person_movies join movies on movies.id = movie_id" +
                " where acting_person_id = " + person.getId();
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, getMoviesByActingPersonQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // TODO not tested
    @Override
    public List<Movie> getMoviesByRegExpInTitle(String regexp) {
        String query = "select * from movies where title regexp '" + regexp + "'";
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getMoviesEarlierByDate(Date date) {
        String query = "select * from movies where release_date < " + "'" + Util.parseSQLDate(date) + "'";
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getMoviesLaterByDate(Date date) {
        String query = "select * from movies where release_date > " + "'" + Util.parseSQLDate(date) + "'";
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getMoviesByCountry(String country) {
        String query = "select * from movies where country = " + "'" + country + "'";
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // TODO check rating
    @Override
    public List<Movie> getMoviesByImdbRating(double rating) {
        String query = "select * from movies where imdb_rating > " + rating;
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getTheMostChosenMovies(int value) {
        String query = "select distinct movie_id, title, movie_type, release_date, runtime, plot, country, " +
                "imdb_rating, imdb_votes, average_client_mark, poster_url, " +
                " count(client_id) from clients_movies join movies on movies.id = movie_id " +
                " group by movie_id having count(client_id) > " + value;
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getTheMostChosenMoviesByMale(int value) {
        String query = "select distinct movie_id, title, movie_type, release_date, runtime, plot, country, " +
                "imdb_rating, imdb_votes, average_client_mark, poster_url, " +
                " gender, count(gender) from clients_movies join movies on movies.id = movie_id " +
                " join clients on clients.id = client_id " +
                " group by movie_id having gender = 'male' && count(gender) > " + value;
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getTheMostChosenMoviesByFemale(int value) {
        String query = "select distinct movie_id, title, movie_type, release_date, runtime, plot, country, " +
                "imdb_rating, imdb_votes, average_client_mark, poster_url, " +
                " gender, count(gender) from clients_movies join movies on movies.id = movie_id " +
                " join clients on clients.id = client_id " +
                " group by movie_id having gender = 'female' && count(gender) > " + value;
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Movie> getMoviesWithHighestMarks(double value) {
        String query = "select * from movies where average_client_mark > " + value;
        List<Movie> movies = new ArrayList<>();

        try {
            return fillUpMovieList(movies, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int addGenreToMovie(Movie movie, Genre genre) {
        String query = String.format("insert into movies_genres values(%d, %d)", movie.getId(),
                genre.ordinal() + 1);

        try {
            return Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private int putMovie(Movie movie, String query) throws SQLException {
        int updated;
        PreparedStatement statement = connection.prepareStatement(query);

        statement.setString(1, movie.getTitle());
        statement.setString(2, putInStringMovieType(movie.getMovieType()));
        statement.setDate(3, parseSQLDate(movie.getReleaseDate()));
        statement.setInt(4, movie.getRuntime());
        statement.setString(5, movie.getPlot());
        statement.setString(6, movie.getCountry());
        statement.setDouble(7, movie.getImdbRating());
        statement.setDouble(8, movie.getImdbVotes());
        statement.setDouble(9, movie.getAverageClientMark());
        statement.setString(10, movie.getPosterURL());
        statement.execute();

        updated = statement.getUpdateCount();

        statement.close();
        return updated;
    }

    private Movie constructNewMovie(ResultSet resultSet) throws SQLException {
        return new Movie(
                resultSet.getLong(1),
                resultSet.getString(2),
                parseMovieType(resultSet.getString(3)),
                parseUtilDateFromSQLDate(resultSet, 4),
                resultSet.getInt(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getDouble(8),
                resultSet.getDouble(9),
                resultSet.getDouble(10),
                resultSet.getString(11));
    }

    private List<Movie> fillUpMovieList(List<Movie> movies, String query) throws SQLException {
        Executor.executeQuery(connection, query, resultSet -> {
            while (resultSet.next()) {
                movies.add(constructNewMovie(resultSet));
            }

            return movies;
        });

        return movies;
    }

}
