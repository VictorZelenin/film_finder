package dev.zelenin.film_finder.commands.shared_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by victor on 25.08.16.
 */
public class MovieDescriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Movie movie;
        try {
            int movieId = Integer.parseInt(request.getParameter("movie_id"));
            movie = MovieService.getMovie(movieId);
        } catch (Exception e) {
            return new MainCommand().execute(request);
        }

        if (movie == null) {
            return new MainCommand().execute(request);
        } else {
            MovieService.prepareMovieData(request, movie);
        }

        return Paths.MOVIE_PAGE;
    }

}
