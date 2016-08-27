package service_test;

import dev.zelenin.film_finder.data.search_builder.MySQLSearchQueryBuilder;
import org.junit.Test;

/**
 * Created by victor on 27.08.16.
 */
public class SearchBuilderTest {

    @Test
    public void createQueryTest() {
        MySQLSearchQueryBuilder queryBuilder = new MySQLSearchQueryBuilder();
        queryBuilder.createSearchQuery();
//        queryBuilder.addTitlePart("The Departed", false);
        queryBuilder.addReleaseYearPart("2000", false);
        queryBuilder.addRuntimePart(130, false);
        queryBuilder.addCountryPart("USA", false);
        queryBuilder.addTitlePart(null, true);
        System.out.println(queryBuilder.getSearchQuery());


    }
}
