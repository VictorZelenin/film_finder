package dev.zelenin.film_finder.commands.client_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.PersonalCabinetCommand;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 29.08.16.
 */
// TODO implement here
public class AcceptEditCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return new PersonalCabinetCommand().execute(request);
    }
}
