package DegreeEZ;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Advisor extends User {
    ArrayList<Student> students;

    public Advisor(UUID uuid, String userName, String firstName, String lastName, String password, ArrayList<Student> students) {
        super(uuid, userName, firstName, lastName, password);
        this.students = students;
    }
    
    public void viewStudents() {
        // list all students
    }

    public void performDegreeAudit(Student student) {
        // Gives advisor options to change student major, schedule, classes, etc.
    }

    public void addCompletedCourse(Student student, CompletedCourse completedCourse) {
        // Adds a completed course to the specified student's degree progress
    }


    public void registerClass(Student student, Course course) {
        // Adds a 'currently enrolled' course to the specified student's degree progress
    }

    public void addRequiredCourse(Student student, Course course) {
        // Adds a 'required' course to the specified student's degree progress
    }

    public List<UUID> getStudentUuids() {
        List<UUID> studentUuids = new ArrayList<>();
        for (Student student : students) {
            studentUuids.add(student.getUUID()); // Collect each student's UUID
        }
        return studentUuids;
    }
}
