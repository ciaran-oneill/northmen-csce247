package DegreeEZ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class DegreeWorksApplicationTest {

    @Test
    void testLogin() {
        DegreeWorksApplication app = new DegreeWorksApplication();

        // Create a test advisor
        Advisor advisor = new Advisor(UUID.randomUUID(), "Alice", "Adams", "aliceadams", "password");
        AdvisorList.getAdvisors().add(advisor);

        User loggedInUser = app.login("aliceadams", "password");

        assertNotNull(loggedInUser);
        assertEquals(advisor, loggedInUser);

        app.logout();
    }

    @Test
    void testLogout() {
        DegreeWorksApplication app = new DegreeWorksApplication();
        app.logout();

        assertNull(app.getUser());
    }

    @Test
    void testCreateAccount() {
        DegreeWorksApplication app = new DegreeWorksApplication();

        // Create a test student
        Student student = (Student) app.createAccount(false, "John", "Doe", "johndoe", "password",
                Major.COMPUTER_SCIENCE);
        assertNotNull(student);
        assertEquals("johndoe", student.getUserName());
        assertEquals(Major.COMPUTER_SCIENCE, student.getMajor());
        assertTrue(StudentList.getStudents().contains(student));

        // Try creating an account with an existing username
        User existingUser = app.createAccount(false, "Jane", "Smith", "johndoe", "password123", Major.MATHEMATICS);
        assertNull(existingUser);

        app.logout();
    }
}
