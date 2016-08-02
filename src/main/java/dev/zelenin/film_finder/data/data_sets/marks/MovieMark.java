package dev.zelenin.film_finder.data.data_sets.marks;

import dev.zelenin.film_finder.data.data_sets.movies.Movie;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 01.08.16.
 */
// TODO rewrite hashcode and equals
public class MovieMark extends Mark implements Serializable {
    private Movie markedMovie;

    public MovieMark() {
    }

    public MovieMark(int movieMarkId, int mark, Date date, String description, Movie markedMovie) {
        super(mark, date, description);
        this.id = movieMarkId;
        this.markedMovie = markedMovie;
    }

    public Movie getMarkedMovie() {
        return markedMovie;
    }

    public void setMarkedMovie(Movie markedMovie) {
        this.markedMovie = markedMovie;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MovieMark movieMark = (MovieMark) o;

        if (id != movieMark.id) return false;
        if (markedMovie != null ? !markedMovie.equals(movieMark.markedMovie) : movieMark.markedMovie != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int)id;
        result = 31 * result + (markedMovie != null ? markedMovie.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MovieMark{" +
                "movieMarkId=" + id +
                ", markedMovie=" + markedMovie +
                ", value=" + mark +
                '}';
    }
}
