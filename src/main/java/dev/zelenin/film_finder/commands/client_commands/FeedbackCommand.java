package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.Message;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.FeedbackService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by victor on 28.08.16.
 */
public class FeedbackCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Client client = (Client) session.getAttribute("client");
        Message message;

        if (client != null) {
            message = FeedbackService.getFeedbackMessage(client);
            session.setAttribute("message", message.getValue());
        }

        return Paths.FEEDBACK_PAGE;
    }
}
