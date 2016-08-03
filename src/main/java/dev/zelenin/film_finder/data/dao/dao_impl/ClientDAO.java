package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IClientDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;
import dev.zelenin.film_finder.data.database.executor.Executor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dev.zelenin.film_finder.utils.Util.parseGender;
import static dev.zelenin.film_finder.utils.Util.parseMovieType;

/**
 * Created by victor on 03.08.16.
 */
// TODO logging here
// TODO refactoring
public class ClientDAO extends DAO<Client> implements IClientDAO {

    public ClientDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(Client object) {
        String insertQuery = String.format("insert into clients(name, gender, email, password, " +
                        "photo_url) " +
                        "values('%s','%s', '%s', '%s', '%s')", object.getName(),
                parseStringGender(object.getGender()), object.getEmail(), object.getPassword(),
                object.getClientPhoto());

        int updated = 0;

        try {
            updated = Executor.executeUpdate(connection, insertQuery);
            // logging
        } catch (SQLException e) {
            // logging
            e.printStackTrace();
        }

        return updated;
    }

    private String parseStringGender(Gender gender) {
        if (gender == null) {
            return null;
        }
        switch (gender) {
            case MALE:
                return "male";
            case FEMALE:
                return "female";
            default:
                return "unknown";
        }
    }

    @Override
    public Client get(long id) {
        String selectClientByIdQuery = "select * from clients where id =" + id;

        try {
            return Executor.executeQuery(connection, selectClientByIdQuery, resultSet -> {
                resultSet.next();

                return new Client(resultSet.getLong(1), resultSet.getString(2),
                        parseGender(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5),
                        resultSet.getString(6));
            });
            // logging
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
        }
        return null;
    }

    @Override
    public List<Client> getAll() {
        String getAllClientsQuery = "select * from clients";

        List<Client> clients = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getAllClientsQuery, resultSet -> {
                while (resultSet.next()) {
                    clients.add(new Client(resultSet.getLong(1), resultSet.getString(2),
                            parseGender(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5),
                            resultSet.getString(6)));
                }

                return clients;
            });
            // logging here
        } catch (SQLException e) {
            e.printStackTrace();
            // logging
        }

        return clients;
    }

    @Override
    public int update(long id, Client newObject) {
        String updateQuery = "update clients set id = ?, name = ?, gender = ?, email = ?, password = ?, " +
                "photo_url = ?" +
                " where id = " + id;
        int updated = 0;

        try (PreparedStatement statement = connection.prepareStatement(updateQuery)) {
            statement.setLong(1, newObject.getId());
            statement.setString(2, newObject.getName());
            statement.setString(3, parseStringGender(newObject.getGender()));
            statement.setString(4, newObject.getEmail());
            statement.setString(5, newObject.getPassword());

            statement.setString(6, newObject.getClientPhoto());

            statement.execute();

            updated = statement.getUpdateCount();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return updated;
    }

    @Override
    public int remove(Client object) {
        String deleteClientQuery = "delete from clients where id = " + object.getId();

        try {
            return Executor.executeUpdate(connection, deleteClientQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int removeAll() {
        String deleteAllClientsQuery = "delete from clients";
        try {
            return Executor.executeUpdate(connection, deleteAllClientsQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public List<Movie> getClientChosenMovies(Client client) {
        String getChosenMoviesQuery = "select distinct movies.id, title, movie_type, release_date, runtime, " +
                "plot, country, imdb_rating, imdb_votes, average_client_mark, poster_url " +
                "from clients_movies " +
                "join movies on movie_id = movies.id " + "where client_id = " + client.getId();
        System.out.println(getChosenMoviesQuery);
        List<Movie> chosenMovies = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getChosenMoviesQuery, resultSet -> {
                while (resultSet.next()) {
                    chosenMovies.add(new Movie(resultSet.getLong(1), resultSet.getString(2),
                            parseMovieType(resultSet.getString(3)), new Date(resultSet.getDate(4).getTime()),
                            resultSet.getInt(5), resultSet.getString(6), resultSet.getString(7),
                            resultSet.getDouble(8), resultSet.getDouble(9), resultSet.getDouble(10),
                            resultSet.getString(11)));
                }

                return chosenMovies;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chosenMovies;
    }

    // TODO implement this methods
    @Override
    public List<MovieMark> getClientMovieMarks(Client client) {
        String getMoviesMarksQuery = "select distinct `movie_marks`.id, mark, date, description, movies.id, " +
                "title, movie_type, release_date, runtime, plot, country, imdb_rating, imdb_votes," +
                " average_client_mark, poster_url from movie_marks join movies on movies.id = movie_id" +
                " where client_id = " + client.getId();

        List<MovieMark> movieMarks = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getMoviesMarksQuery, result -> {
                while (result.next()) {
                    movieMarks.add(new MovieMark(result.getLong(1), result.getInt(2),
                            new Date(result.getDate(3).getTime()), result.getString(4),
                            parseMovie(result), client));
                }

                return movieMarks;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movieMarks;
    }

    private Movie parseMovie(ResultSet resultSet) throws SQLException {
        return new Movie(resultSet.getLong(5), resultSet.getString(6),
                parseMovieType(resultSet.getString(7)), new Date(resultSet.getDate(8).getTime()),
                resultSet.getInt(9), resultSet.getString(10), resultSet.getString(11),
                resultSet.getDouble(12), resultSet.getDouble(13), resultSet.getDouble(14),
                resultSet.getString(15));
    }

    @Override
    public List<ActingPersonMark> getClientActingPersonMarks(Client client) {
        String getActingPersonMarksQuery = "select distinct acting_person_marks.id, mark, date, description, " +
                "acting_people.id, name, height, country, age, death_date, total_movies_number," +
                " average_client_mark, photo_url from acting_person_marks " +
                "join acting_people on person_id = acting_people.id where client_id = " + client.getId();

        List<ActingPersonMark> personMarks = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getActingPersonMarksQuery, result -> {
                while (result.next()) {
                    personMarks.add(new ActingPersonMark(result.getLong(1), result.getInt(2),
                            new Date(result.getDate(3).getTime()), result.getString(4),
                            parseActingPerson(result), client));
                }
                System.out.println(personMarks);
                return personMarks;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return personMarks;
    }

    @Override
    public List<Client> getClientsByGender(Gender gender) {
        String getClientsByGenderQuery = "select * from clients where gender = '"
                + parseStringGender(gender) + "'";

        List<Client> clients = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getClientsByGenderQuery, result -> {
                while (result.next()) {
                    clients.add(createClientFromResultSet(result));
                }

                return clients;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return clients;
    }

    private Client createClientFromResultSet(ResultSet result) throws SQLException {
        return new Client(result.getLong(1), result.getString(2),
                parseGender(result.getString(3)), result.getString(4), result.getString(5),
                result.getString(6));
    }

    private ActingPerson parseActingPerson(ResultSet result) throws SQLException {
        return new ActingPerson(result.getLong(5), result.getString(6), result.getDouble(7),
                result.getString(8), result.getInt(9), validateDate(result), result.getInt(11),
                result.getDouble(12), result.getString(13));
    }

    private Date validateDate(ResultSet resultSet) throws SQLException {
        java.sql.Date date = resultSet.getDate(10);
        if (date == null) {
            return null;
        } else {
            return new Date(date.getTime());
        }
    }
}
