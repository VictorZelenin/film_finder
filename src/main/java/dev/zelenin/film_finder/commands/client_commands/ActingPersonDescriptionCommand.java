package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.marks.ActingPersonMark;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.ActingPersonService;
import dev.zelenin.film_finder.services.MarkService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static dev.zelenin.film_finder.utils.Util.calculateAveragePersonMark;

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
            downloadActingPersonMarks(request, actingPerson);
        } catch (Exception e) {
            return new MainCommand().execute(request);
        }

        if (actingPerson != null) {
            ActingPersonService.prepareActingPersonData(request, actingPerson);
        } else {
            return new MainCommand().execute(request);
        }

        return Paths.ACTING_PERSON_PAGE;
    }

    private void downloadActingPersonMarks(HttpServletRequest request, ActingPerson actingPerson) {
        Client client = (Client) request.getSession().getAttribute("client");
        List<ActingPersonMark> markList = MarkService.getAllPersonMarks(actingPerson);
        actingPerson.setAverageClientMark(calculateAveragePersonMark(markList));

        if (client != null) {
            request.setAttribute("mark", MarkService.getActingPersonMarkByClient(client, actingPerson));
        }

        request.setAttribute("marks", markList);
    }
}
