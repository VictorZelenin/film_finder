package data_test.dao_tests;

import dev.zelenin.film_finder.data.dao.dao_impl.ClientDAO;
import dev.zelenin.film_finder.data.dao.dao_impl.MessageDAO;
import dev.zelenin.film_finder.data.data_sets.Message;
import dev.zelenin.film_finder.data.data_sets.users.Admin;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.exceptions.ClientNotFoundException;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by victor on 04.08.16.
 */
public class MessageDAOTests {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/film_finder_db" +
            "?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private Connection connection;
    private MessageDAO messageDAO;

    @Before
    public void setUp() throws SQLException {

        connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
        messageDAO = new MessageDAO(connection);
    }

    // TODO check id before using into DB
    @Test
    public void getMessageTest() {
        Message message = messageDAO.get(6);
        System.out.println(message);
        assertNotNull(message);
    }


    @Test
    public void getAllMessagesTest() {
        List<Message> messages = messageDAO.getAll();
        System.out.println(messages);
        assertTrue(messages.size() > 1);
    }

    @Test
    public void saveMessageTest() {
        Client client = new ClientDAO(connection).get(16);
        System.out.println(client);
        Message message = new Message("", new Date(), client);
        System.out.println(message.getClient());

        assertTrue(messageDAO.save(message) == 1);
    }

    @Test
    public void existsTest() {
        Client client = new ClientDAO(connection).get(21);
        System.out.println(client);

        assertFalse(messageDAO.exists(client));
    }

    @Test(expected = ClientNotFoundException.class)
    public void getMessageByClientTest() {
        Client client = new ClientDAO(connection).get(18); // exists in clients , doesn't exist in messages
        System.out.println(client);
        Message message = messageDAO.getMessageFromClient(client);
        System.out.println(message);
    }

    // TODO запретить изменение message_id and client_id
    @Test
    public void updateMessageTest() {
        Message message = messageDAO.get(7);

        message.setValue("baaar");
        assertTrue(messageDAO.update(7, message) == 1);
    }

    @Test
    public void removeTest() {
        assertNotEquals(messageDAO.remove(messageDAO.get(3)), -1);
    }

    @Test
    public void getMessagesForAdminsTest() {
        List<Message> messages = messageDAO.getMessagesForAdmin(new Admin(4, "a", "b", "c"));
        System.out.println(messages);
        assertTrue(messages.size() > 0);
    }
}
