package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IMessageDAO;
import dev.zelenin.film_finder.data.data_sets.Message;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by victor on 03.08.16.
 */
// TODO rewrite hashcode and equals
public class MessageDAO extends DAO<Message> implements IMessageDAO {
    public MessageDAO(Connection connection) {
        super(connection);
    }

    // not tested
    @Override
    public int save(Message object) {
        int updated = 0;
        String insertQuery = "insert into messages(message, date, client_id)" +
                "values(?, ?, ?)";


        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, object.getValue());
            statement.setDate(2, new Date(object.getDate().getTime()));
            statement.setLong(3, object.getClient().getId());

            statement.execute();

            updated = statement.getUpdateCount();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public Message get(long id) {
        return null;
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public int update(long id, Message newObject) {
        return 0;
    }

    @Override
    public int remove(Message object) {
        return 0;
    }

    @Override
    public int removeAll() {
        return 0;
    }
}
