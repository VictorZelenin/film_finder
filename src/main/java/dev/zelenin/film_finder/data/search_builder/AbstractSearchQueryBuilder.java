package dev.zelenin.film_finder.data.search_builder;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;

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
    public abstract void addActorsPart(List<ActingPerson> actingPersonList, boolean isLast);

    // join acting_person_movies table
    public abstract void addDirectorPart(ActingPerson director, boolean isLast);

    // join movies_genres table
    public abstract void addGenresPart(List<Genre> genres, boolean isLast);

    // join acting_person_movies table
    public abstract void addActingPersonPart(ActingPerson actingPerson, boolean isLast);

    // join movie_marks table
    public abstract void addClientsMarksPart(int mark, boolean isLast);
}
