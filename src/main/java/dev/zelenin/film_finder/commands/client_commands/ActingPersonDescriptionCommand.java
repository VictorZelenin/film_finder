package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.shared_commands.MainCommand;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.services.ActingPersonService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 28.08.16.
 */
public class ActingPersonDescriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        ActingPerson actingPerson;

        try {
            int personId = Integer.parseInt(request.getParameter("person_id"));

            actingPerson = ActingPersonService.getActingPersonById(personId);
        } catch (Exception e) {
            return new MainCommand().execute(request);
        }

        if (actingPerson == null) {
            return new MainCommand().execute(request);
        } else {
            ActingPersonService.prepareActingPersonData(request, actingPerson);
        }

        return Paths.ACTING_PERSON_PAGE;
    }
}
