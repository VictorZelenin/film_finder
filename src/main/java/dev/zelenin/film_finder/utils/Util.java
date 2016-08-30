package dev.zelenin.film_finder.utils;

import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.MovieType;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;
import dev.zelenin.film_finder.data.database.executor.Executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 03.08.16.
 */
public class Util {

    public static MovieType parseMovieType(String type) {
        if (type == null) {
            return null;
        }
        switch (type.toLowerCase()) {
            case "movie":
                return MovieType.FILM;
            case "series":
                return MovieType.SERIES;
            case "episode":
                return MovieType.EPISODE;
            default:
                return MovieType.FILM;
        }
    }

    public static String putInStringMovieType(MovieType movieType) {
        if (movieType == null) {
            return null;
        }

        switch (movieType) {
            case FILM:
                return "movie";
            case SERIES:
                return "series";
            case EPISODE:
                return "episode";
            default:
                return "unspecified";
        }
    }

    public static Gender parseGender(String gender) {
        if (gender == null) {
            return null;
        }
        switch (gender.toLowerCase()) {
            case "male":
                return Gender.MALE;
            case "female":
                return Gender.FEMALE;
            default:
                return Gender.UNKNOWN;
        }
    }

    public static String parseStringGender(Gender gender) {
        if (gender == null) {
            return null;
        }
        switch (gender) {
            case MALE:
                return "male";
            case FEMALE:
                return "female";
            default:
                return "unknown";
        }
    }

    public static Date parseUtilDateFromSQLDate(ResultSet resultSet, int index) {
        java.sql.Date date;
        try {
            date = resultSet.getDate(index);
        } catch (SQLException e) {
            // logging
            return null;
        }
        // logging
        if (date == null) {
            return null;
        } else {
            return new Date(date.getTime());
        }
    }

    public static java.sql.Date parseSQLDate(Date date) {
        if (date == null) {
            return null;
        } else {
            return new java.sql.Date(date.getTime());
        }
    }

    public static List<Genre> getGenres(List<Genre> genres, Connection connection, String query) {
        Executor.executeQuery(connection, query, resultSet -> {
            while (resultSet.next()) {
                genres.add(Genre.valueOf(resultSet.getString(1).toUpperCase()));
            }

            return genres;
        });

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return genres;
    }
}
