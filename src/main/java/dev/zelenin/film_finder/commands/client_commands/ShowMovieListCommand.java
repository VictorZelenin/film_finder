package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.services.MoviePagerService;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by victor on 26.08.16.
 */
public class ShowMovieListCommand implements Command {
    private List<Movie> movies;

    @Override
    public String execute(HttpServletRequest request) {
        String param = request.getParameter("default");

        if (param != null) {
            MoviePagerService.setupMovieList(request, 5, MovieService.getMoviesByImdbRating());
        } else {
            setupMovieList(movies, request);
        }

        return Paths.MOVIE_LIST;
    }

    private void setupMovieList(List<Movie> movies, HttpServletRequest request) {
        MoviePagerService.setupMovieList(request, 5, movies);
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
