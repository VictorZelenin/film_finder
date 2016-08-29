package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 28.08.16.
 */
// TODO impl feedback.jsp
public class FeedbackCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return Paths.FEEDBACK_PAGE;
    }
}
