package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonMarkDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.executor.Executor;
import dev.zelenin.film_finder.exceptions.IncorrectMarkException;

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
public class ActingPersonMarkDAO extends DAO<ActingPersonMark> implements IActingPersonMarkDAO {
    private static final int MIN_MARK_VALUE = 0;
    private static final int MAX_MARK_VALUE = 10;

    public ActingPersonMarkDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(ActingPersonMark object) {
        String insertQuery = "insert into acting_person_marks(mark, date, description, person_id, client_id) " +
                "values(?, ?, ?, ?, ?)";

        try {
            return putPersonMark(object, insertQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public ActingPersonMark find(long id) {
        String query = "select acting_person_marks.id, mark, acting_person_marks.date, description, " +
                "acting_people.id, acting_people.name, acting_people.gender, height, country, age, death_date, " +
                "total_movies_number, average_client_mark, acting_people.photo_url, clients.id, clients.name, " +
                "clients.gender, email, password, clients.photo_url " +
                "from acting_person_marks " +
                "join acting_people on acting_people.id = person_id " +
                "join clients on clients.id = client_id " +
                "where acting_person_marks.id = " + id;

        return Executor.executeQuery(connection, query, resultSet -> {
            resultSet.next();

            return constructPersonMark(resultSet);
        });
    }

    @Override
    public List<ActingPersonMark> findAll() {
        String query = "select acting_person_marks.id, mark, acting_person_marks.date, description, " +
                "acting_people.id, acting_people.name, acting_people.gender, height, country, age, death_date, " +
                "total_movies_number, average_client_mark, acting_people.photo_url, clients.id, clients.name, " +
                "clients.gender, email, password, clients.photo_url " +
                "from acting_person_marks " +
                "join acting_people on acting_people.id = person_id " +
                "join clients on clients.id = client_id ";
        List<ActingPersonMark> actingPersonMarks = new ArrayList<>();

        return fillUpList(actingPersonMarks, query);
    }

    @Override
    public int update(long id, ActingPersonMark newObject) {
        String query = "update acting_person_marks " +
                "set " +
                "mark = ?, " +
                "date = ?, " +
                "description = ? " +
                "where id = " + id;
        int updated = -1;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, newObject.getMark());
            statement.setDate(2, parseSQLDate(newObject.getDate()));
            statement.setString(3, newObject.getDescription());
            statement.execute();

            updated = statement.getUpdateCount();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public int remove(ActingPersonMark object) {
        String query = "delete from acting_person_marks " +
                "where id = " + object.getId();
        int updated;

        updated = Executor.executeUpdate(connection, query);

        return updated;
    }

    @Override
    public int removeAll() {
        String query = "delete from acting_person_marks";
        int updated;

        updated = Executor.executeUpdate(connection, query);
        return updated;
    }

    @Override
    public int rowsCount() {
        return rowsCount("acting_person_marks");
    }

    @Override
    public List<ActingPersonMark> findActingPersonMarksByClient(Client client) {
        String query = "select acting_person_marks.id, mark, acting_person_marks.date, description, " +
                "acting_people.id, acting_people.name, acting_people.gender, height, country, age, death_date, " +
                "total_movies_number, average_client_mark, acting_people.photo_url, clients.id, clients.name, " +
                "clients.gender, email, password, clients.photo_url " +
                "from acting_person_marks " +
                "join acting_people on acting_people.id = person_id " +
                "join clients on clients.id = client_id " +
                "where client_id = " + client.getId();
        List<ActingPersonMark> marks = new ArrayList<>();

        return fillUpList(marks, query);
    }

    @Override
    public List<ActingPersonMark> findActingPersonMarksByActingPerson(ActingPerson person) {
        String query = "select acting_person_marks.id, mark, acting_person_marks.date, description, " +
                "acting_people.id, acting_people.name, acting_people.gender, height, country, age, death_date, " +
                "total_movies_number, average_client_mark, acting_people.photo_url, clients.id, clients.name, " +
                "clients.gender, email, password, clients.photo_url " +
                "from acting_person_marks " +
                "join acting_people on acting_people.id = person_id " +
                "join clients on clients.id = client_id " +
                "where person_id = " + person.getId();
        List<ActingPersonMark> list = new ArrayList<>();

        return fillUpList(list, query);
    }

    @Override
    public ActingPersonMark findActingPersonMarkByClientAndPerson(Client client, ActingPerson person) {
        String query = "select acting_person_marks.id, mark, acting_person_marks.date, description, " +
                "acting_people.id, acting_people.name, acting_people.gender, height, country, age, death_date, " +
                "total_movies_number, average_client_mark, acting_people.photo_url, clients.id, clients.name, " +
                "clients.gender, email, password, clients.photo_url " +
                "from acting_person_marks " +
                "join acting_people on acting_people.id = person_id " +
                "join clients on clients.id = client_id " +
                "where person_id = " + person.getId() +
                " and " +
                " client_id = " + client.getId();

        return Executor.executeQuery(connection, query, resultSet -> {
            resultSet.next();

            return constructPersonMark(resultSet);
        });
    }

    private int putPersonMark(ActingPersonMark mark, String query) throws SQLException {
        int updated;

        if (mark.getMark() > MAX_MARK_VALUE && mark.getMark() < MIN_MARK_VALUE) {
            throw new IncorrectMarkException();
        }

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, mark.getMark());
        statement.setDate(2, parseSQLDate(mark.getDate()));
        statement.setString(3, mark.getDescription());
        statement.setLong(4, mark.getActingPerson().getId());
        statement.setLong(5, mark.getClient().getId());

        statement.execute();
        updated = statement.getUpdateCount();

        statement.close();
        return updated;
    }

    private List<ActingPersonMark> fillUpList(List<ActingPersonMark> list, String query) {
        Executor.executeQuery(connection, query, resultSet -> {
            while (resultSet.next()) {
                list.add(constructPersonMark(resultSet));
            }
            return list;
        });

        return list;
    }

    private ActingPersonMark constructPersonMark(ResultSet resultSet) throws SQLException {
        return new ActingPersonMark(
                resultSet.getLong(1),
                resultSet.getInt(2),
                parseUtilDateFromSQLDate(resultSet, 3),
                resultSet.getString(4),
                constructPerson(resultSet),
                constructClient(resultSet));
    }

    private Client constructClient(ResultSet resultSet) throws SQLException {
        return new Client(
                resultSet.getLong(15),
                resultSet.getString(16),
                parseGender(resultSet.getString(17)),
                resultSet.getString(18),
                resultSet.getString(19),
                resultSet.getString(20));
    }

    private ActingPerson constructPerson(ResultSet resultSet) throws SQLException {
        return new ActingPerson(
                resultSet.getLong(5),
                resultSet.getString(6),
                parseGender(resultSet.getString(7)),
                resultSet.getInt(8),
                resultSet.getString(9),
                resultSet.getInt(10),
                parseUtilDateFromSQLDate(resultSet, 11),
                resultSet.getInt(12),
                resultSet.getDouble(13),
                resultSet.getString(14));
    }
}
