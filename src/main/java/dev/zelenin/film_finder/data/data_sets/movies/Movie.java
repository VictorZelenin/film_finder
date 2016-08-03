package dev.zelenin.film_finder.data.data_sets.movies;

import dev.zelenin.film_finder.data.data_sets.DataSet;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by victor on 01.08.16.
 */
// TODO check roles when will add new person
// TODO rewrite equals and hashcode methods
public class Movie extends DataSet implements Serializable {
    private String title;
    private MovieType movieType;

    private Date releaseDate;
    private int runtime;
    private String plot;
    private String country;

    private double imdbRating;
    private double imdbVotes;
    private double averageClientMark;

//    private List<Genre> genres;
//    private ActingPerson director;
//    private List<ActingPerson> actorsList;
//    private List<ActingPerson> screenWriters;
//    private List<ActingPerson> producers;

    private String posterURL;


    public Movie() {
//        genres = new ArrayList<>();
//        actorsList = new ArrayList<>();
//        screenWriters = new ArrayList<>();
//        producers = new ArrayList<>();
    }

    public Movie(long movieId, String title, MovieType movieType, Date releaseDate, int runtime,
                 String plot, String country, double imdbRating,
                 double imdbVotes, double averageClientMark, String posterURL) {
        this.id = movieId;
        this.title = title;
        this.movieType = movieType;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.plot = plot;
        this.country = country;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.posterURL = posterURL;
        this.averageClientMark = averageClientMark;
//        genres = new ArrayList<>();
//        actorsList = new ArrayList<>();
//        screenWriters = new ArrayList<>();
//        producers = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieType getMovieType() {
        return movieType;
    }

    public void setMovieType(MovieType movieType) {
        this.movieType = movieType;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(double imdbRating) {
        this.imdbRating = imdbRating;
    }

    public double getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(double imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public double getAverageClientMark() {
        return averageClientMark;
    }

    public void setAverageClientMark(double averageClientMark) {
        this.averageClientMark = averageClientMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (runtime != movie.runtime) return false;
        if (Double.compare(movie.imdbRating, imdbRating) != 0) return false;
        if (Double.compare(movie.imdbVotes, imdbVotes) != 0) return false;
        if (Double.compare(movie.averageClientMark, averageClientMark) != 0) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (movieType != movie.movieType) return false;
        if (releaseDate != null ? !releaseDate.equals(movie.releaseDate) : movie.releaseDate != null) return false;
        if (plot != null ? !plot.equals(movie.plot) : movie.plot != null) return false;
        if (country != null ? !country.equals(movie.country) : movie.country != null) return false;
        return posterURL != null ? posterURL.equals(movie.posterURL) : movie.posterURL == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = title != null ? title.hashCode() : 0;
        result = 31 * result + (movieType != null ? movieType.hashCode() : 0);
        result = 31 * result + (releaseDate != null ? releaseDate.hashCode() : 0);
        result = 31 * result + runtime;
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(imdbRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imdbVotes);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(averageClientMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (posterURL != null ? posterURL.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + id +
                ", title='" + title + '\'' +
                ", movieType=" + movieType +
                ", imdbRating=" + imdbRating +
                ", averageClientMark=" + averageClientMark +
                '}';
    }
}
