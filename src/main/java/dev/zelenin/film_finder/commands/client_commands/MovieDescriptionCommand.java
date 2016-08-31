package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.shared_commands.MainCommand;
import dev.zelenin.film_finder.data.data_sets.marks.MovieMark;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.MarkService;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static dev.zelenin.film_finder.utils.Util.calculateAverageMovieMark;

/**
 * Created by victor on 25.08.16.
 */
// TODO new SimpleDateFormat("День dd Месяц MM Год yyyy Время hh:mm");
public class MovieDescriptionCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        Movie movie;
        try {
            int movieId = Integer.parseInt(request.getParameter("movie_id"));

            movie = MovieService.getMovie(movieId);
            downloadMovieMarks(request, movie);

        } catch (Exception e) {
            return new MainCommand().execute(request);
        }

        if (movie != null) {
            MovieService.prepareMovieData(request, movie);

        } else {
            return new MainCommand().execute(request);
        }

        return Paths.MOVIE_PAGE;
    }

    private void downloadMovieMarks(HttpServletRequest request, Movie movie) {
        Client client = (Client) request.getSession().getAttribute("client");
        List<MovieMark> movieMarks = MarkService.getAllMovieMarks(movie);
        movie.setAverageClientMark(calculateAverageMovieMark(movieMarks));

        if (client != null) {
            request.setAttribute("mark", MarkService.getMovieMarkByClient(client, movie));
        }

        request.setAttribute("marks", movieMarks);

    }

}
