//package DegreeEZ;

import java.util.ArrayList;
import java.util.UUID;

public class StudentList {
    private static StudentList instance = null;
    private ArrayList<Student> students;

    private StudentList() {
        students = DataLoader.loadStudents("students.json");
    }

    public static synchronized StudentList getInstance() {
        if (instance == null) {
            instance = new StudentList();
        }
        return instance;
    }

    public static synchronized Student getStudentByUUID(UUID id) {
        for (Student s : getInstance().students) {
            if (s.getUUID().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public static synchronized ArrayList<Student> getStudents() {
        return getInstance().students;
    }

}
