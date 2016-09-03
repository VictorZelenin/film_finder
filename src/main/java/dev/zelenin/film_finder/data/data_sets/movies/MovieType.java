package dev.zelenin.film_finder.data.data_sets.movies;

/**
 * Created by victor on 01.08.16.
 */
public enum MovieType {
    FILM,

    SERIES,

    EPISODE;

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
}
