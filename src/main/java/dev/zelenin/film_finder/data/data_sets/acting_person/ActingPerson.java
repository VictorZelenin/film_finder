package dev.zelenin.film_finder.data.data_sets.acting_person;

import dev.zelenin.film_finder.data.data_sets.DataSet;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.data.data_sets.movies.Movie;

import java.io.Serializable;
import java.util.*;

/**
 * Created by victor on 01.08.16.
 */
// TODO rewrite hashcode and equals
public class ActingPerson extends DataSet implements Serializable {
    private String name;
    private Date birthday;
    private double height;
    private String country;
    private int age;
    private int totalMoviesNumber;
    private Date deathDate;
    private String photoURL;

    private Set<ActingRole> rolesList;
    private List<Genre> genres;
    private List<Movie> bestMovies;

    private double averageClientMark;

    public ActingPerson() {
        rolesList = new HashSet<>();
        genres = new ArrayList<>();
        bestMovies = new ArrayList<>();
    }

    public ActingPerson(int actingPersonId, String name, Date birthday,
                        double height, String country, int age, int totalMoviesNumber) {
        this.id = actingPersonId;
        this.name = name;
        this.birthday = birthday;
        this.height = height;
        this.country = country;
        this.age = age;
        this.totalMoviesNumber = totalMoviesNumber;
        rolesList = new HashSet<>();
        genres = new ArrayList<>();
        bestMovies = new ArrayList<>();

    }

    public ActingPerson(int actingPersonId, String name, Date birthday, Set<ActingRole> rolesList,
                        List<Genre> genres, List<Movie> bestMovies, double height, String country,
                        int age, int totalMoviesNumber, double averageClientMark, Date deathDate) {
        this.id = actingPersonId;
        this.name = name;
        this.birthday = birthday;
        this.rolesList = rolesList;
        this.genres = genres;
        this.bestMovies = bestMovies;
        this.height = height;
        this.country = country;
        this.age = age;
        this.totalMoviesNumber = totalMoviesNumber;
        this.averageClientMark = averageClientMark;
        this.deathDate = deathDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<ActingRole> getRolesList() {
        return rolesList;
    }

    public void setRolesList(Set<ActingRole> rolesList) {
        this.rolesList = rolesList;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Movie> getBestMovies() {
        return bestMovies;
    }

    public void setBestMovies(List<Movie> bestMovies) {
        this.bestMovies = bestMovies;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTotalMoviesNumber() {
        return totalMoviesNumber;
    }

    public void setTotalMoviesNumber(int totalMoviesNumber) {
        this.totalMoviesNumber = totalMoviesNumber;
    }

    public double getAverageClientMark() {
        return averageClientMark;
    }

    public void setAverageClientMark(double averageClientMark) {
        this.averageClientMark = averageClientMark;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
    }

    public void addRole(ActingRole role) {
        rolesList.add(role);
    }

    public boolean removeRole(ActingRole role) {
        return rolesList.remove(role);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public boolean removeGenre(Genre genre) {
        return genres.remove(genre);
    }

    public void addBestMovie(Movie movie) {
        bestMovies.add(movie);
    }

    public boolean removeBestMovie(Movie movie) {
        return bestMovies.remove(movie);
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public boolean isActor() {
        return rolesList.contains(ActingRole.ACTOR);
    }

    public boolean isDirector() {
        return rolesList.contains(ActingRole.DIRECTOR);
    }

    public boolean isScreenWriter() {
        return rolesList.contains(ActingRole.SCREEN_WRITER);
    }

    public boolean isProducer() {
        return rolesList.contains(ActingRole.PRODUCER);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ActingPerson that = (ActingPerson) o;

        if (id != that.id) return false;
        if (Double.compare(that.height, height) != 0) return false;
        if (age != that.age) return false;
        if (totalMoviesNumber != that.totalMoviesNumber) return false;
        if (Double.compare(that.averageClientMark, averageClientMark) != 0) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null) return false;
        if (rolesList != null ? !rolesList.equals(that.rolesList) : that.rolesList != null) return false;
        if (genres != null ? !genres.equals(that.genres) : that.genres != null) return false;
        if (bestMovies != null ? !bestMovies.equals(that.bestMovies) : that.bestMovies != null) return false;
        return country != null ? country.equals(that.country) : that.country == null;

    }

    @Override
    public int hashCode() {
        long result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (rolesList != null ? rolesList.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        result = 31 * result + (bestMovies != null ? bestMovies.hashCode() : 0);
        temp = Double.doubleToLongBits(height);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + totalMoviesNumber;
        temp = Double.doubleToLongBits(averageClientMark);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return (int) result;
    }

    @Override
    public String toString() {
        return "ActingPerson{" +
                "actingPersonId=" + id +
                ", name='" + name + '\'' +
                ", totalMoviesNumber=" + totalMoviesNumber +
                ", averageClientMark=" + averageClientMark +
                '}';
    }
}
