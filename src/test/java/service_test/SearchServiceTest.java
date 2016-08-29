package service_test;

import dev.zelenin.film_finder.services.search_builder.MySQLSearchQueryBuilder;
import dev.zelenin.film_finder.services.SearchService;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by victor on 27.08.16.
 */
public class SearchServiceTest {

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

    @Test
    public void validateParamMapTest() {
        Map<String, String[]> testMap = new HashMap<>();
        testMap.put("a", new String[]{"aa", "bbcc"});
        testMap.put("b", new String[]{});
        testMap.put("c", new String[]{});
        testMap.put("d", new String[]{"addda", "q", "qoo"});


        SearchService.validateMap(testMap);
        System.out.println(testMap);


    }
}
