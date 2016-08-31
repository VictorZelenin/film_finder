package dev.zelenin.film_finder.commands.client_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.MovieDescriptionCommand;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.ActingPersonService;
import dev.zelenin.film_finder.services.MarkService;
import dev.zelenin.film_finder.services.MovieService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by victor on 30.08.16.
 */
public class AddMarkCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter("movie_id") != null) {
            long movieId = Long.parseLong(request.getParameter("movie_id"));
            int mark = Integer.parseInt(request.getParameter("mark"));
            String comment = request.getParameter("comment");

            Movie movie = MovieService.getMovie(movieId);
            Client client = (Client) request.getSession().getAttribute("client");

            MarkService.addMovieMark(mark, new Date(), comment, movie, client);
        }

        if (request.getParameter("person_id") != null) {
            long personId = Long.parseLong(request.getParameter("person_id"));
            int mark = Integer.parseInt(request.getParameter("mark"));
            String comment = request.getParameter("comment");

            ActingPerson actingPerson = ActingPersonService.getActingPersonById(personId);
            Client client = (Client) request.getSession().getAttribute("client");

            MarkService.addActingPersonMark(mark, new Date(), comment, actingPerson, client);
        }

        return new MovieDescriptionCommand().execute(request);
    }
}
