package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 20.08.16.
 */
public class PersonalCabinetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("client");

        if (client == null) {
            return Paths.LOG_IN;
        }

        // TODO strange bug!!!
//        Util.setupClientProfile(request);
        return Paths.PERSONAL_CABINET_PAGE;
    }
}
