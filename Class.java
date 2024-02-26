import java.util.ArrayList;

public class Class {
    private Subject subject;
    private int number;
    private String name;
    private ArrayList<Class> prerequisites;
    private String minGrade;
    private int creditHours;
    private ArrayList<CompletedCourse> completedCourse;
    private ArrayList<Class> libArts;
    private String availability;

    public Class(Subject subject, int number, String name) {    
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSubject(Subject subject) {
        return subject;
    }

    public String addPrerequisites() {
        return prerequisites;
    }

    public int minGrade(int grade) {
        return grade;
    }
}
