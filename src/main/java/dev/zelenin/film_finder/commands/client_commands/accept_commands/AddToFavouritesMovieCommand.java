package dev.zelenin.film_finder.commands.client_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.MovieDescriptionCommand;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.ClientService;
import dev.zelenin.film_finder.services.MovieService;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 31.08.16.
 */
public class AddToFavouritesMovieCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Client client = (Client) request.getSession().getAttribute("client");
        Movie movie = MovieService.getMovie(Long.parseLong(request.getParameter("movie_id")));

        if (client != null) {
            ClientService.addMovieToFavourites(movie, client);
        }

        return new MovieDescriptionCommand().execute(request);
    }
}
