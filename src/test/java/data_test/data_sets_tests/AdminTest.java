package data_test.data_sets_tests;

import dev.zelenin.film_finder.data.data_sets.Message;
import dev.zelenin.film_finder.data.data_sets.users.Admin;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by victor on 01.08.16.
 */
public class AdminTest {

    private Admin admin;

    @Before
    public void setUp() {
        admin = new Admin();
    }

    @Test
    public void testAddOneMessage() {
        Message message = mock(Message.class);

        admin.addMessage(message);

        assertEquals(admin.getFeedbackMessages().size(), 1);
    }

    // null haven't put into list -> caused IndexOutOfBoundsException
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAddNullMessage() {
        Message message = null;
        admin.addMessage(message);
        assertNotNull(admin.getFeedbackMessages().get(0));
    }

    @Test
    public void testEqualsAdminsById() {
        Admin newAdmin = new Admin();

        assertEquals(admin, newAdmin);
    }

    @Test
    public void testEqualsAdminByAllParam() {
        Admin admin = new Admin(1, "Victor", "zelenin.victor95@gmail.com", "1q2w3e4r5t");
        Admin admin1 = new Admin(1, "Victor", "zelenin.victor95@gmail.com", "1q2w3e4r5t");

        assertTrue(admin.equals(admin1));
    }

    @Test
    public void testDefaultConstructorForListCreating() {
        Admin admin = new Admin();
        assertNotNull(admin.getFeedbackMessages());
    }
}
