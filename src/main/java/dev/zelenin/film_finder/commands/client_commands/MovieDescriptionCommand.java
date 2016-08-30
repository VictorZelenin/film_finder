package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.shared_commands.MainCommand;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.services.MarkService;
import dev.zelenin.film_finder.services.MovieService;
import dev.zelenin.film_finder.utils.Paths;

import javax.servlet.http.HttpServletRequest;

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
            System.out.println(movieId);
            movie = MovieService.getMovie(movieId);
//            downloadMovieMarks(request, movie);


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

    private void downloadMovieMarks(HttpServletRequest request, Movie movie) {
        Client client = (Client) request.getSession().getAttribute("client");

        request.setAttribute("mark", MarkService.getMovieMarkByClient(client, movie));
        request.setAttribute("marks", MarkService.getAllMovieMarks(movie));

    }

}
