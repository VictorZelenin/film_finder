package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_factory.IDAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.DatabaseManager;
import dev.zelenin.film_finder.data.search_builder.AbstractSearchQueryBuilder;
import dev.zelenin.film_finder.data.search_builder.MySQLSearchQueryBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.Map.*;

/**
 * Created by victor on 27.08.16.
 */
public class SearchService {
    public static List<Movie> getMoviesBySearchParam(Map<String, String[]> paramMap) {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);
        IMovieDAO movieDAO = factory.getMovieDAO();

        List<Movie> movies = movieDAO.findMoviesBySearchQuery(constructQuery(validateMap(paramMap)));

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return movies;
    }

    private static Map<String, String[]> validateMap(Map<String, String[]> paramMap) {
        return null;
    }

    private static String constructQuery(Map<String, String[]> paramMap) {
        AbstractSearchQueryBuilder builder = new MySQLSearchQueryBuilder();
        builder.createSearchQuery();

        int mapSize = paramMap.size();
        int i = 0;

        for (Entry<String, String[]> entry : paramMap.entrySet()) {
//            if (entry.getKey().equals("title")) {
//
//            }
            System.out.println(entry.getKey());
            System.out.println(Arrays.toString(entry.getValue()));
            i++;
        }

//        if (title != null && !title.equals("")) {
//            builder.addTitlePart(title, true);
//        }


        // if title_param != null {addTitleParam(title_param, true)}
//        builder.addActorsPart();
//        builder.addReleaseYearPart();
//        builder.addTitlePart();


        return builder.getSearchQuery();
    }
}
