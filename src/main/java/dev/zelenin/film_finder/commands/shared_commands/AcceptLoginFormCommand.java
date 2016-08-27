package dev.zelenin.film_finder.commands.shared_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.PersonalCabinetCommand;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.LoginService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 17.08.16.
 */
public class AcceptLoginFormCommand implements Command {
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";

    @Override
    public String execute(HttpServletRequest request) {
        Client user;

        String email = request.getParameter(EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);

        try {
            user = LoginService.createClient(email, password);
            request.getSession().setAttribute("client", user);

            return new PersonalCabinetCommand().execute(request);

        } catch (Exception e) {
            request.setAttribute("error_message", "Error message");

            // setup some info

            e.printStackTrace();
            return Paths.LOG_IN;
        }
    }
}
