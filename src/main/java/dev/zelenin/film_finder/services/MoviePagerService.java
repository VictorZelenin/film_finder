package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.DatabaseManager;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by victor on 23.08.16.
 */
public class MoviePagerService {

    public static void setupMovieList(HttpServletRequest request, int moviesPerPage, List<Movie> movies) {
        Connection connection = DatabaseManager.getConnection();
        final IMovieDAO movieDAO = new DAOFactory(connection).getMovieDAO();
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
                splitMovieList(movies, moviesPerPage, currentPage));
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void setupMovieList(HttpServletRequest request, int moviesCount, int moviesPerPage,
                                       int currentPageIndex, List<Movie> list) {
        if (currentPageIndex * moviesPerPage < moviesCount) {
            request.setAttribute("next_page", currentPageIndex + 1);
        }

        if (currentPageIndex > 1) {
            request.setAttribute("previous_page", currentPageIndex - 1);
        }

        request.setAttribute("movies", list);
    }

    public static List<Movie> splitMovieList(List<Movie> list, int moviesPerPage, int currentPage) {
        if (moviesPerPage * (currentPage - 1) > list.size()) {
            throw new IllegalArgumentException();
        } else if (moviesPerPage * (currentPage - 1) + moviesPerPage >= list.size()
                && list.size() >= moviesPerPage * (currentPage - 1)) {
            return list.subList(moviesPerPage * (currentPage - 1), list.size());
        } else {
            return list.subList(moviesPerPage * (currentPage - 1), moviesPerPage);
        }
    }
}
