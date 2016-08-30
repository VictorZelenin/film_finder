package dev.zelenin.film_finder.commands.client_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.shared_commands.MainCommand;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.FeedbackService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by victor on 30.08.16.
 */
public class AcceptFeedbackCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("client");

        String message = request.getParameter("feedback_message");
        System.out.println(message);

        FeedbackService.saveFeedbackMessage(message, new Date(), client);

        return new MainCommand().execute(request);
    }
}
