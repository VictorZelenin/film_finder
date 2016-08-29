package dev.zelenin.film_finder.services.search_builder;

import dev.zelenin.film_finder.data.data_sets.acting_person.ActingPerson;
import dev.zelenin.film_finder.data.data_sets.movies.Genre;
import dev.zelenin.film_finder.utils.Util;

import java.util.List;

/**
 * Created by victor on 27.08.16.
 */
public class MySQLSearchQueryBuilder extends AbstractSearchQueryBuilder {

    @Override
    public void createSearchQuery() {
        queryBuilder = new StringBuilder("select distinct " +
                "id, title, movie_type, release_date, runtime, plot, country, imdb_rating, imdb_votes, " +
                "average_client_mark, poster_url from movies " +
                " left join acting_person_movies on acting_person_movies.movie_id = movies.id " +
                " join movies_genres on movies.id = movies_genres.movie_id " +
                "where ");
    }

    @Override
    public void addTitlePart(String title, boolean isLast) {
        queryBuilder.append("title = '")
                .append(title)
                .append("'");
        addLast(isLast);
    }

    @Override
    public void addReleaseYearPart(String year, boolean isLast) {
        queryBuilder.append("release_date = '")
                .append(year)
                .append("'");
        addLast(isLast);
    }

    @Override
    public void addCountryPart(String country, boolean isLast) {
        queryBuilder.append("country = '")
                .append(country)
                .append("'");
        addLast(isLast);
    }

    @Override
    public void addActorsPart(ActingPerson actingPerson, boolean isLast) {

        queryBuilder.append("acting_person_id = ")
                .append(actingPerson.getId())
                .append(" and ")
                .append("isActor = 1");

        addLast(isLast);
    }

    @Override
    public void addDirectorPart(ActingPerson director, boolean isLast) {
        queryBuilder.append("acting_person_id = ")
                .append(director.getId())
                .append(" and ")
                .append("isDirector = 1");
        addLast(isLast);
    }

    @Override
    public void addGenresPart(List<Genre> genres, boolean isLast) {
        queryBuilder.append("genre_id ")
                .append("in(");
        for (int i = 0; i < genres.size(); i++) {
            if (i == genres.size() - 1) {
                queryBuilder.append(genres.get(i).ordinal() + 1)
                        .append(")");
            } else {
                queryBuilder.append(genres.get(i).ordinal() + 1)
                        .append(", ");
            }
        }

        addLast(isLast);
    }

    @Override
    public void addImdbMarkPart(double mark, boolean isLast) {
        queryBuilder.append("imdb_rating >= '")
                .append(mark)
                .append("'");
        addLast(isLast);
    }

    @Override
    public void addMovieTypePart(String movieType, boolean isLast) {
        queryBuilder.append("movie_type = '")
                .append(Util.parseMovieType(movieType))
                .append("'");
        addLast(isLast);
    }

    @Override
    public void addRuntimePart(int runtime, boolean isLast) {
        queryBuilder.append("runtime >= '")
                .append(runtime)
                .append("'");
        addLast(isLast);
    }

    @Override
    public void addActingPersonPart(ActingPerson actingPerson, boolean isLast) {
        addDirectorPart(actingPerson, isLast);
    }

    @Override
    public void addClientsMarksPart(int mark, boolean isLast) {
        queryBuilder.append("average_client_mark >= '")
                .append(mark)
                .append("'");
        addLast(isLast);
    }

    private void addLast(boolean isLast) {
        if (isLast) {
            queryBuilder.append(";");
        } else {
            queryBuilder.append(" and ");
        }
    }
}
