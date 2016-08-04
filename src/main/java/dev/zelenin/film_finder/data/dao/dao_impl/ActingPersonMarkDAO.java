package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IActingPersonMarkDAO;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.executor.Executor;
import dev.zelenin.film_finder.utils.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 04.08.16.
 */
public class ActingPersonMarkDAO extends DAO<ActingPersonMark> implements IActingPersonMarkDAO {
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
    public ActingPersonMark get(long id) {
        String query = "select * from acting_person_marks where id = " + id;

        try {
            return Executor.executeQuery(connection, query, resultSet -> {
                resultSet.next();

                return constructPersonMark(resultSet);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<ActingPersonMark> getAll() {
        String query = "select * from action_person_marks";
        List<ActingPersonMark> actingPersonMarks = new ArrayList<>();

        try {
            return fillUpList(actingPersonMarks, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int update(long id, ActingPersonMark newObject) {
        String query = "update action_person_marks " +
                "set " +
                "mark = ?, " +
                "date = ?, " +
                "description = ?, " +
                "person_id = ?, " +
                "client_id = ? " +
                "where id = " + id;


        try {
            return putPersonMark(newObject, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int remove(ActingPersonMark object) {
        String query = "delete from acting_person_marks " +
                "where id = " + object.getId();
        int updated = 0;

        try {
            updated = Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public int removeAll() {
        String query = "delete from acting_person_marks";
        int updated = 0;

        try {
            updated = Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    // TODO mark must be 10<=
    private int putPersonMark(ActingPersonMark mark, String query) throws SQLException {
        int updated;

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, mark.getMark());
        statement.setDate(2, Util.parseSQLDate(mark.getDate()));
        statement.setString(3, mark.getDescription());
        statement.setLong(4, mark.getActingPerson().getId());
        statement.setLong(5, mark.getClient().getId());

        statement.execute();
        updated = statement.getUpdateCount();

        statement.close();
        return updated;
    }

    private List<ActingPersonMark> fillUpList(List<ActingPersonMark> list, String query) throws SQLException {
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
                Util.parseUtilDateFromSQLDate(resultSet, 3),
                resultSet.getString(4),
                constructPerson(resultSet),
                constructClient(resultSet));
    }

    private Client constructClient(ResultSet resultSet) {
        return new Client(
                // TODO implement there
        );
    }

    private ActingPerson constructPerson(ResultSet resultSet) {
        return new ActingPerson(
                // TODO implement there
        );
    }
}
