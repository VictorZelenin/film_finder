package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.services.MoviePagerService;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 20.08.16.
 */
public class MainCommand implements Command {

    private static final int MOVIES_PER_PAGE = 5;

    @Override
    public String execute(HttpServletRequest request) {
        MoviePagerService.setupMovieList(request, MOVIES_PER_PAGE, MovieService.getMoviesByImdbRating());

        return Paths.CONTROLLER;
    }
}
