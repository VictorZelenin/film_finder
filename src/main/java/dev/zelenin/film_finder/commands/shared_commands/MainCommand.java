package dev.zelenin.film_finder.commands.shared_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.services.MoviePagerService;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by victor on 20.08.16.
 */
public class MainCommand implements Command {

    private static final int MOVIES_PER_PAGE = 5;

    @Override
    public String execute(HttpServletRequest request) {
        List<Genre> genres = MovieService.getAllGenres();
        request.setAttribute("genres", genres);

        MoviePagerService.setupMovieList(request, MOVIES_PER_PAGE);

        return Paths.CONTROLLER;
    }
}
