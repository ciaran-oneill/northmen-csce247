package DegreeEZ;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class CompletedCourse extends Course {
    private int finalGrade;
    private Semester semesterTaken;
    private boolean pass;

    public CompletedCourse(UUID id, String name, Subject subject, int number, 
                           ArrayList<HashMap<Course, String>> prerequisites, 
                           int minGrade, int creditHours, int finalGrade, 
                           Semester semesterTaken, boolean pass) {
        super(id, name, subject, number, prerequisites, minGrade, new ArrayList<>(), creditHours);
        
        this.finalGrade = finalGrade;
        this.semesterTaken = semesterTaken;
        this.pass = pass;
    }

    // Getters and setters for the new fields
    public int getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(int finalGrade) {
        this.finalGrade = finalGrade;
    }

    public Semester getSemesterTaken() {
        return semesterTaken;
    }

    public void setSemesterTaken(Semester semesterTaken) {
        this.semesterTaken = semesterTaken;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
