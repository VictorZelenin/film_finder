package dev.zelenin.film_finder.utils;

import dev.zelenin.film_finder.data.data_sets.movies.MovieType;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;

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
}
