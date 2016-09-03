package dev.zelenin.film_finder.commands.client_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.PersonalCabinetCommand;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.ClientService;
import dev.zelenin.film_finder.services.form_parser.ClientFormHandler;
import dev.zelenin.film_finder.services.form_parser.FormHandler;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Created by victor on 29.08.16.
 */

// TODO validation
public class AcceptEditCommand implements Command {

    @Override
    public String execute(HttpServletRequest request) {
        FormHandler handler = new ClientFormHandler();

        Map<String, Object> attributes = handler.handle(request);

        Client client = (Client) request.getSession().getAttribute("client");
        String name = constructClientName(attributes);
        String email = (String) attributes.get("email");
        String password = (String) attributes.get("password");
        String confirmPassword = (String) attributes.get("password_confirmation");
        String gender = (String) attributes.get("gender");
        String photoUrl = (String) attributes.get("photo_url");

        if (!isCorrectPassword(password, confirmPassword)) {
            return Paths.EDIT;
        }

        Client updatedClient =
                ClientService.updateClient(client.getId(), name, gender, email, password, photoUrl);

        request.getSession().setAttribute("client", updatedClient);

        return new PersonalCabinetCommand().execute(request);
    }

    private String constructClientName(Map<String, Object> attributes) {
        return attributes.get("first_name") + " " + attributes.get("last_name");
    }

    private boolean isCorrectPassword(String password, String confirmPassword) {
        return password.equals(confirmPassword) && !Objects.equals(password, "");
    }
}
