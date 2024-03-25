package DegreeEZ;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest {

    private static final String STUDENT_FILE_PATH = "students_test.json";
    private static final String ADVISOR_FILE_PATH = "advisors_test.json";

    @BeforeEach
    void setUp() {
        // Delete existing test files if they exist
        deleteTestFiles();
    }

    @AfterEach
    void tearDown() {
        // Delete test files after each test
        deleteTestFiles();
    }

    private void deleteTestFiles() {
        try {
            Files.deleteIfExists(Paths.get(STUDENT_FILE_PATH));
            Files.deleteIfExists(Paths.get(ADVISOR_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSaveStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(UUID.randomUUID(), "John", "Doe", "johndoe", "password", Major.COMPUTER_SCIENCE,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null));
        students.add(new Student(UUID.randomUUID(), "Jane", "Smith", "janesmith", "password123", Major.MATHEMATICS,
                new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), null));

        DataWriter.saveStudents(STUDENT_FILE_PATH, students);

        assertTrue(Files.exists(Paths.get(STUDENT_FILE_PATH)));

        // TODO: Add more assertions to check the content of the saved file
    }

    @Test
    void testSaveAdvisors() {
        List<Advisor> advisors = new ArrayList<>();
        advisors.add(new Advisor(UUID.randomUUID(), "Alice", "Adams", "aliceadams", "password"));
        advisors.add(new Advisor(UUID.randomUUID(), "Bob", "Brown", "bobbrown", "password123"));

        DataWriter.saveAdvisors(ADVISOR_FILE_PATH, advisors);

        assertTrue(Files.exists(Paths.get(ADVISOR_FILE_PATH)));

        // TODO: Add more assertions to check the content of the saved file
    }
}
