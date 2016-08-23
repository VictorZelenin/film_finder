package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by victor on 23.08.16.
 */
public class MoviePagerService {

    public static void setupMovieList(HttpServletRequest request, int moviesPerPage) {
        final IMovieDAO movieDAO = new DAOFactory(DatabaseManager.getConnection()).getMovieDAO();
        final int moviesCount = movieDAO.rowsCount();
        int page;
        final int currentPage;

        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (RuntimeException e) {
            page = 1;
        }

        currentPage = page;

        setupMovieList(request, moviesCount, moviesPerPage, currentPage,
                () -> movieDAO.getMoviesByRatingInRange(moviesPerPage,
                        moviesPerPage * (currentPage - 1)));
    }

    private static void setupMovieList(HttpServletRequest request, int moviesCount, int moviesPerPage,
                                       int currentPageIndex, Supplier<List<Movie>> moviesSupplier) {
        final List<Movie> movies = moviesSupplier.get();

        if (currentPageIndex * moviesPerPage < moviesCount) {
            request.setAttribute("next_page", currentPageIndex + 1);
        }

        if (currentPageIndex > 1) {
            request.setAttribute("previous_page", currentPageIndex - 1);
        }

        request.setAttribute("movies", movies);
    }
}
