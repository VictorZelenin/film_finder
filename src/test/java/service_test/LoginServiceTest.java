package service_test;

import dev.zelenin.film_finder.services.LoginService;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by victor on 22.08.16.
 */
public class LoginServiceTest {

    @Test
    public void createUserTest() throws Exception {
        String email = "zelenin.victor95@gmail.com";
        String password = "1q2w3e4r5t";

        assertNotNull(LoginService.createClient(email, password));

    }
}
