package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMessageDAO;
import dev.zelenin.film_finder.data.data_sets.Message;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import java.sql.Connection;
import java.util.Date;

/**
 * Created by victor on 30.08.16.
 */
public class FeedbackService extends DatabaseService {

    public static void saveFeedbackMessage(String message, Date date, Client client) {
        Connection connection = DatabaseManager.getConnection();
//        Connection connection = DatabaseManager.getDebugConnection();

        IMessageDAO dao = new DAOFactory(connection).getMessageDAO();
        Message feedbackMessage = new Message(message, date, client);
        Message storedMessage = getFeedbackMessage(client);

        if ( storedMessage == null) {
            dao.save(feedbackMessage);
        } else {
            dao.update(storedMessage.getId(), feedbackMessage);
        }

        closeConnection(connection);
    }

    public static Message getFeedbackMessage(Client client) {
        Connection connection = DatabaseManager.getConnection();
        IMessageDAO dao = new DAOFactory(connection).getMessageDAO();
        Message message = dao.findMessageFromClient(client);

        closeConnection(connection);

        return message;
    }
}
