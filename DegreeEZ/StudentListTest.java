package DegreeEZ;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class StudentListTest {
    private StudentList studentList = StudentList.getInstance();
    private ArrayList<Student> students = StudentList.getStudents();
    
    @BeforeEach
    public void setup() {
        students.clear();
        students.add(new Student(UUID , "ASmith", "Ann", "Smith", "password", "3"));
        students.add(new Student(UUID , "JDoe", "John", "Doe", "password1", "3"));
        DataWriter.saveStudents(null, students);
    }

    @AfterEach
    public void tearDown() {
        StudentList.getInstance().getStudents().clear();
        DataWriter.saveStudents(null, students);
    }

}
