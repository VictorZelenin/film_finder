package dev.zelenin.film_finder.commands.shared_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.services.MoviePagerService;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 26.08.16.
 */
public class ShowMovieListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {

        String param = request.getParameter("");

        if (param != null) {
            // setup movie list here
        } else {
            MoviePagerService.setupMovieList(request, 5, MovieService.getMoviesByImdbRating());
        }



        return Paths.MOVIE_LIST;
    }
}
