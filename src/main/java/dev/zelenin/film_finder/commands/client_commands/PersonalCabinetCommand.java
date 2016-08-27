package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

        setupClientProfile(request);

        return Paths.PERSONAL_CABINET_PAGE;
    }

    public static void setupClientProfile(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("client");

        if (client != null) {
            List<Movie> movieList = MovieService.getMoviesByClient(client);
            List<MovieMark> markList = MovieService.getMovieMarks(client);

            request.getSession().setAttribute("movieList", movieList);
            request.getSession().setAttribute("markList", markList);
        }
    }
}
