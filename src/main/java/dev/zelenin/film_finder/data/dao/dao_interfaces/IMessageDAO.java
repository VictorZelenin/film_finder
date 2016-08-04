package dev.zelenin.film_finder.data.dao.dao_interfaces;

import dev.zelenin.film_finder.data.data_sets.Message;
import dev.zelenin.film_finder.data.data_sets.users.Admin;
import dev.zelenin.film_finder.data.data_sets.users.Client;

import java.util.List;

/**
 * Created by victor on 03.08.16.
 */

public interface IMessageDAO extends IDAO<Message> {
    Message getMessageFromClient(Client client);

    boolean exists(Client client);

    boolean exists(Message message);

    List<Message> getMessagesForAdmin(Admin admin);
}
