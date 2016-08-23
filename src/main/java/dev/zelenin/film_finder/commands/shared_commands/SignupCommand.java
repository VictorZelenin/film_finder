package dev.zelenin.film_finder.commands.shared_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.PersonalCabinetCommand;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 22.08.16.
 */
public class SignupCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getSession().getAttribute("client") != null) {
            return new PersonalCabinetCommand().execute(request);
        }

        return Paths.SIGN_UP;
    }
}
