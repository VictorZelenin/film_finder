package dev.zelenin.film_finder.services.search_builder;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.services.ActingPersonService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 27.08.16.
 */
public abstract class AbstractSearchQueryBuilder {
    protected StringBuilder queryBuilder;

    public String getSearchQuery() {
        return queryBuilder.toString();
    }

    public abstract void createSearchQuery();

    public void addQueryPart(String partName, String[] values, boolean isLast) {
        switch (partName) {
            case "title":
                addTitlePart(values[0], isLast);
                break;
            case "imdb_rating":
                addImdbMarkPart(Double.parseDouble(values[0]), isLast);
                break;
            case "actor":
                ActingPerson actingPerson = ActingPersonService.getActingPersonByLastName(values[0]);
                if (actingPerson != null) {
                    addActorsPart(actingPerson, isLast);
                }
                break;
            case "runtime":
                addRuntimePart(Integer.parseInt(values[0]), isLast);
                break;
            case "year":
                addReleaseYearPart(values[0], isLast);
                break;
            case "movie_type":
                addMovieTypePart(parseMovieType(values[0]), isLast);
                break;
            case "country":
                addCountryPart(values[0], isLast);
                break;
            case "genre":
                addGenresPart(parseGenres(values), isLast);
                break;
            case "director":
                ActingPerson director = ActingPersonService.getActingPersonByLastName(values[0]);
                if (director != null) {
                    addDirectorPart(director, isLast);
                }
                break;
            case "clients_mark":
                addClientsMarksPart(Integer.parseInt(values[0]), isLast);
                break;
        }
    }

    private List<Genre> parseGenres(String[] values) {
        List<Genre> genres = new ArrayList<>();
        for (String value : values) {
            genres.add(Genre.valueOf(value.toUpperCase()));
        }

        return genres;
    }

    private String parseMovieType(String value) {
        int integerValue = Integer.parseInt(value);

        switch (integerValue) {
            case 0:
                return "film";
            case 1:
                return "series";
            case 2:
                return "episode";
            default:
                return "film";
        }
    }

    // movies table
    public abstract void addTitlePart(String title, boolean isLast);

    // movies table
    public abstract void addReleaseYearPart(String year, boolean isLast);

    // movies table
    public abstract void addCountryPart(String country, boolean isLast);

    // movies table
    public abstract void addImdbMarkPart(double mark, boolean isLast);

    // movies table
    public abstract void addMovieTypePart(String movieType, boolean isLast);

    // movies table
    public abstract void addRuntimePart(int runtime, boolean isLast);

    // join acting_person_movies table
    public abstract void addActorsPart(ActingPerson actingPerson, boolean isLast);

    // join acting_person_movies table
    public abstract void addDirectorPart(ActingPerson director, boolean isLast);

    // join movies_genres table
    public abstract void addGenresPart(List<Genre> genres, boolean isLast);

    // join acting_person_movies table
    public abstract void addActingPersonPart(ActingPerson actingPerson, boolean isLast);

    // join movie_marks table
    public abstract void addClientsMarksPart(int mark, boolean isLast);
}
