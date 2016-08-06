package dev.zelenin.film_finder.data.dao.dao_impl;

import dev.zelenin.film_finder.data.dao.dao_interfaces.IMessageDAO;
import dev.zelenin.film_finder.data.data_sets.Message;
import dev.zelenin.film_finder.data.data_sets.users.Admin;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.executor.Executor;
import dev.zelenin.film_finder.exceptions.ClientNotFoundException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static dev.zelenin.film_finder.utils.Util.*;

/**
 * Created by victor on 03.08.16.
 */
// TODO logging here
public class MessageDAO extends DAO<Message> implements IMessageDAO {
    public MessageDAO(Connection connection) {
        super(connection);
    }

    @Override
    public int save(Message object) {
        checkClient(object.getClient());

        int updated = 0;
        String insertQuery = "insert into messages(message, date, client_id)" +
                "values(?, ?, ?)";


        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, object.getValue());
            statement.setDate(2, parseSQLDate(object.getDate()));
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
        String selectQuery = "select * from messages join clients on client_id = clients.id " +
                " where messages.id = " + id;


        try {
            return Executor.executeQuery(connection, selectQuery, resultSet -> {
                resultSet.next();
                return constructMessage(resultSet);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Message constructMessage(ResultSet resultSet) throws SQLException {
        return new Message(resultSet.getLong(1), resultSet.getString(2), parseUtilDateFromSQLDate(resultSet, 3),
                createClientFromResultSet(resultSet));
    }

    private Client createClientFromResultSet(ResultSet result) throws SQLException {
        return new Client(result.getLong(5), result.getString(6),
                parseGender(result.getString(7)), result.getString(8), result.getString(9),
                result.getString(10));
    }

    @Override
    public List<Message> getAll() {
        String getAllMessagesQuery = "select * from messages join clients on client_id = clients.id";
        List<Message> messages = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getAllMessagesQuery, resultSet -> {
                while (resultSet.next()) {
                    messages.add(constructMessage(resultSet));
                }

                return messages;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public int update(long id, Message newObject) {
        checkClient(newObject.getClient());

        String updateMessageQuery = "update messages set message = ?, date = ?" +
                " where id = " + id;
        int updated = 0;

        try (PreparedStatement statement = connection.prepareStatement(updateMessageQuery)) {

            statement.setString(1, newObject.getValue());
            statement.setDate(2, parseSQLDate(newObject.getDate()));

            statement.execute();
            updated = statement.getUpdateCount();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }

    @Override
    public int remove(Message object) {
        String removeQuery = "delete from messages where id = " + object.getId();

        try {
            return Executor.executeUpdate(connection, removeQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public int removeAll() {
        String removeAllQuery = "delete from messages";

        try {
            return Executor.executeUpdate(connection, removeAllQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    @Override
    public Message getMessageFromClient(final Client client) {

        if (!exists(client)) {
            throw new ClientNotFoundException();
        }

        String getMessageFromClientQuery = "select * from messages where client_id = " + client.getId();


        try {
            return Executor.executeQuery(connection, getMessageFromClientQuery, resultSet -> {
                resultSet.next();

                return new Message(resultSet.getLong(1), resultSet.getString(2),
                        parseUtilDateFromSQLDate(resultSet, 3), client);
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Message> getMessagesForAdmin(Admin admin) {
        if (admin == null) {
            throw new RuntimeException();
        }
        String getMessagesQuery = "select distinct message_id, message, date, clients.id, client_id, name, " +
                "gender, email, password, " +
                "photo_url from messages_to_admins join messages on messages.id = message_id" +
                " join clients on clients.id = client_id where admin_id = " + admin.getId();
        List<Message> messageList = new ArrayList<>();

        try {
            Executor.executeQuery(connection, getMessagesQuery, resultSet -> {
                while (resultSet.next()) {
                    messageList.add(new Message(resultSet.getLong(1), resultSet.getString(2),
                            parseUtilDateFromSQLDate(resultSet, 3), createClientFromResultSet(resultSet)));
                }
                return messageList;
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return messageList;
    }

    @Override
    public boolean exists(Client client) {
        List<Message> messages = getAll();
        List<Long> longs = messages
                .stream()
                .map(message -> message.getClient().getId())
                .collect(Collectors.toList());
        System.out.println(longs);
        return client != null && longs.contains(client.getId());

    }

    @Override
    public int addMessageToAdmin(Message message, Admin admin) {
        String query = String.format("insert into messages_to_admins values(%d, %d)",
                admin.getId(), message.getId());

        try {
            return Executor.executeUpdate(connection, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    private void checkClient(Client client) {
        if (client == null) {
            throw new ClientNotFoundException();
        }

        if (!new ClientDAO(connection).exists(client)) {
            throw new ClientNotFoundException();
        }

    }
}
