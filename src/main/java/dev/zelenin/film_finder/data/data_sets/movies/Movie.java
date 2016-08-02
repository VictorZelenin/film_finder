package dev.zelenin.film_finder.data.data_sets.movies;

import dev.zelenin.film_finder.data.data_sets.DataSet;
import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.exceptions.IncompatibleActingRoleException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private List<Genre> genres;
    private ActingPerson director;
    private List<ActingPerson> actorsList;
    private List<ActingPerson> screenWriters;
    private List<ActingPerson> producers;

    private String posterURL;


    public Movie() {
        genres = new ArrayList<>();
        actorsList = new ArrayList<>();
        screenWriters = new ArrayList<>();
        producers = new ArrayList<>();
    }

    public Movie(int movieId, String title, MovieType movieType, Date releaseDate, int runtime,
                 ActingPerson director, String plot, String country, double imdbRating,
                 double imdbVotes, String posterURL) {
        this.id = movieId;
        this.title = title;
        this.movieType = movieType;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.director = director;
        this.plot = plot;
        this.country = country;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.posterURL = posterURL;
        genres = new ArrayList<>();
        actorsList = new ArrayList<>();
        screenWriters = new ArrayList<>();
        producers = new ArrayList<>();
    }

    public Movie(int movieId, String title, MovieType movieType, Date releaseDate, int runtime,
                 ActingPerson director, List<Genre> genres, List<ActingPerson> actorsList,
                 List<ActingPerson> screenWriters, String plot, String country, double imdbRating,
                 double imdbVotes, String posterURL, double averageClientMark) {
        this.id = movieId;
        this.title = title;
        this.movieType = movieType;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.director = director;
        this.genres = genres;
        this.actorsList = actorsList;
        this.screenWriters = screenWriters;
        this.plot = plot;
        this.country = country;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.posterURL = posterURL;
        this.averageClientMark = averageClientMark;
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

    public ActingPerson getDirector() {
        return director;
    }

    public void setDirector(ActingPerson director) {
        this.director = director;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<ActingPerson> getActorsList() {
        return actorsList;
    }

    public void setActorsList(List<ActingPerson> actorsList) {
        this.actorsList = actorsList;
    }

    public List<ActingPerson> getScreenWriters() {
        return screenWriters;
    }

    public void setScreenWriters(List<ActingPerson> screenWriters) {
        this.screenWriters = screenWriters;
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

    public List<ActingPerson> getProducers() {
        return producers;
    }

    public void setProducers(List<ActingPerson> producers) {
        this.producers = producers;
    }

    public void addGenre(Genre genre) {
        if (genre == null) {
            System.err.println("Genre object is a null");
        } else {
            genres.add(genre);
        }

    }

    public boolean removeGenre(Genre genre) {
        return genres.remove(genre);
    }

    public void addActor(ActingPerson actor) {
        if (actor == null) {
            System.err.println("Actor is null, please put correct value");
        } else if (actor.isActor()) {
            actorsList.add(actor);
        } else {
            throw new IncompatibleActingRoleException(actor + " is not an actor! " +
                    "He is " + actor.getRolesList());
        }
    }

    public boolean removeActor(ActingPerson actor) {
        return actorsList.remove(actor);
    }

    public void addScreenWriter(ActingPerson screenWriter) {
        if (screenWriter == null) {
            System.err.println("Screenwriter is null");
        } else if (screenWriter.isScreenWriter()) {
            screenWriters.add(screenWriter);
        } else {
            throw new IncompatibleActingRoleException(screenWriter + " is not a screnwriter" +
                    "He is " + screenWriter.getRolesList());
        }
    }

    public boolean removeScreenWriter(ActingPerson screenWriter) {
        return screenWriters.remove(screenWriter);
    }

    public void addProducer(ActingPerson producer) {
        if (producer == null) {
            System.err.println("Producer is a null value");
        } else if (producer.isProducer()) {
            producers.add(producer);
        } else {
            throw new IncompatibleActingRoleException(producer + " is not a producer" +
                    "He is " + producer.getRolesList());
        }
    }

    public boolean removeProducer(ActingPerson producer) {
        return producers.remove(producer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (id != movie.id) return false;
        if (releaseDate != movie.releaseDate) return false;
        if (Double.compare(movie.runtime, runtime) != 0) return false;
        if (Double.compare(movie.imdbRating, imdbRating) != 0) return false;
        if (Double.compare(movie.imdbVotes, imdbVotes) != 0) return false;
        if (Double.compare(movie.averageClientMark, averageClientMark) != 0) return false;
        if (title != null ? !title.equals(movie.title) : movie.title != null) return false;
        if (movieType != movie.movieType) return false;
        if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
        if (genres != null ? !genres.equals(movie.genres) : movie.genres != null) return false;
        if (actorsList != null ? !actorsList.equals(movie.actorsList) : movie.actorsList != null) return false;
        if (screenWriters != null ? !screenWriters.equals(movie.screenWriters) : movie.screenWriters != null)
            return false;
        if (plot != null ? !plot.equals(movie.plot) : movie.plot != null) return false;
        if (country != null ? !country.equals(movie.country) : movie.country != null) return false;
        return posterURL != null ? posterURL.equals(movie.posterURL) : movie.posterURL == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int)id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (movieType != null ? movieType.hashCode() : 0);
        result = 31 * result + releaseDate.hashCode();
        temp = Double.doubleToLongBits(runtime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (actorsList != null ? actorsList.hashCode() : 0);
        result = 31 * result + (screenWriters != null ? screenWriters.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        temp = Double.doubleToLongBits(imdbRating);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(imdbVotes);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (posterURL != null ? posterURL.hashCode() : 0);
        temp = Double.doubleToLongBits(averageClientMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
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
