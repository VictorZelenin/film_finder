package dev.zelenin.film_finder.commands.client_commands;

import dev.zelenin.film_finder.commands.Command;
import dev.zelenin.film_finder.commands.shared_commands.ShowMovieListCommand;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.services.SearchService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 27.08.16.
 */
public class AcceptSearchCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        // load movie list by param-s


        System.out.println(request.getParameterMap());

//        request.getParameterMap().values().forEach(strs -> System.out.println(Arrays.toString(strs)));


//        String title = request.getParameter("title");

        List<Movie> movies = SearchService.getMoviesBySearchParam(request.getParameterMap());

        ShowMovieListCommand showMovieListCommand = new ShowMovieListCommand();
        showMovieListCommand.setMovies(new ArrayList<>());

        return showMovieListCommand.execute(request);
    }
}
