package dev.zelenin.film_finder.services;

import dev.zelenin.film_finder.data.dao.dao_factory.DAOFactory;
import dev.zelenin.film_finder.data.dao.dao_factory.IDAOFactory;
import dev.zelenin.film_finder.data.dao.dao_interfaces.IMovieDAO;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;
import dev.zelenin.film_finder.data.database.DatabaseManager;
import dev.zelenin.film_finder.services.search_builder.AbstractSearchQueryBuilder;
import dev.zelenin.film_finder.services.search_builder.MySQLSearchQueryBuilder;

import java.sql.Connection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry;

/**
 * Created by victor on 27.08.16.
 */
// TODO validation
public class SearchService extends DatabaseService {

    public static List<Movie> getMoviesBySearchParam(Map<String, String[]> paramMap) {
        Connection connection = DatabaseManager.getConnection();
        IDAOFactory factory = new DAOFactory(connection);
        IMovieDAO movieDAO = factory.getMovieDAO();

        List<Movie> movies = movieDAO.findMoviesBySearchQuery(constructQuery(validateMap(paramMap)));

        closeConnection(connection);

        return movies;
    }


    public static Map<String, String[]> validateMap(Map<String, String[]> paramMap) {
        Iterator<Map.Entry<String, String[]>> iterator = paramMap.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String[]> entry = iterator.next();
            if (entry.getValue().length == 0 ||
                    entry.getValue() == null ||
                    entry.getValue()[0].isEmpty()) {

                iterator.remove();
            }
        }

        return paramMap;
    }

    private static String constructQuery(Map<String, String[]> paramMap) {
        AbstractSearchQueryBuilder builder = new MySQLSearchQueryBuilder();
        builder.createSearchQuery();

        int mapSize = paramMap.size();
        int i = 0;

        System.out.println(paramMap);
        for (Entry<String, String[]> entry : paramMap.entrySet()) {
            if (i == mapSize - 1) {
                builder.addQueryPart(entry.getKey(), entry.getValue(), true);
            } else {
                builder.addQueryPart(entry.getKey(), entry.getValue(), false);
            }

            i++;
        }

        return builder.getSearchQuery();
    }
}
