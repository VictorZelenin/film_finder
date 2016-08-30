package service_test;

import dev.zelenin.film_finder.data.data_sets.users.Client;
import dev.zelenin.film_finder.data.data_sets.users.util.Gender;
import dev.zelenin.film_finder.services.FeedbackService;
import org.junit.Test;

import java.util.Date;

/**
 * Created by victor on 30.08.16.
 */
public class FeedbackServiceTest {

    @Test
    public void testSaveFeedbackMessage() {
        FeedbackService.saveFeedbackMessage("aaaa", new Date(), new Client(1, "a", Gender.FEMALE, "b", "c", "g"));
    }
}
