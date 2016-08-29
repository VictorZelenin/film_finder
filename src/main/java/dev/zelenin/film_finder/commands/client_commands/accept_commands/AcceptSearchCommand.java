package dev.zelenin.film_finder.commands.client_commands.accept_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.client_commands.ShowMovieListCommand;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.services.SearchService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by victor on 27.08.16.
 */
public class AcceptSearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        List<Movie> movies = SearchService.getMoviesBySearchParam(request.getParameterMap());

        ShowMovieListCommand showMovieListCommand = new ShowMovieListCommand();
        showMovieListCommand.setMovies(movies);

        return showMovieListCommand.execute(request);
    }
}
