package DegreeEZ;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class DataLoaderTest {

    @Test
    public void testLoadStudents() {
        String testFilePath = "src/test/resources/test_students.json"; // Adjust the path as needed
        ArrayList<Student> students = DataLoader.loadStudents(testFilePath);
        assertNotNull(students, "Students list should not be null");
        assertFalse(students.isEmpty(), "Students list should not be empty");


    }

    @Test
    public void testLoadAdvisors() {
        String testFilePath = "src/test/resources/test_advisors.json"; // Adjust the path as needed
        ArrayList<Advisor> advisors = DataLoader.loadAdvisors(testFilePath);
        assertNotNull(advisors, "Advisors list should not be null");
        assertFalse(advisors.isEmpty(), "Advisors list should not be empty");

    }

    @Test
    public void testLoadCourses() {
        String testFilePath = "src/test/resources/test_courses.json"; // Adjust the path as needed
        ArrayList<Course> courses = DataLoader.loadCourses(testFilePath);
        assertNotNull(courses, "Courses list should not be null");
        assertFalse(courses.isEmpty(), "Courses list should not be empty");

    }


}
