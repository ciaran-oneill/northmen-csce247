package DegreeEZ;

import java.util.ArrayList;
import java.util.UUID;

public class CompletedCourse {
    public Course course;
    public String finalGrade;
    public Semester semesterTaken;

    public CompletedCourse (String className, int finalGrade, boolean pass) {
        //TODO
        this.className = className; 
        this.finalGrade = finalGrade;
        this.pass = pass;
    }
}
