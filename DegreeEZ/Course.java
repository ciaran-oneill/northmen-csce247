package DegreeEZ; 

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Course {
    private UUID id;
<<<<<<< HEAD
    //private Subject subject;
>>>>>>> 1d1cd90139808ae3e8a136d25655fc311ea06b8d
=======
    private Subject subject;
>>>>>>> 3cba52b5df97375de8fd351e86b70ee11bfbeea8
    private int number;
    private String name;
    private ArrayList<HashMap<Course, String>> prerequisites; // each Course can have multiple prerequisites with specific min grades
    private int minGrade;
    private List<Semester> availability; 
    private int creditHours;

    // Constructor
    public Course(UUID id, String name, Subject subject, int number, ArrayList<HashMap<Course,String>> prerequisites, int minGrade, ArrayList<Semester> availability) {
        this.id = UUID.randomUUID(); // Generate a unique ID for each course
        this.subject = subject;
        this.number = number;
        this.name = name;
        this.minGrade = minGrade;
        this.prerequisites = new ArrayList<>();
        this.availability = new ArrayList<>();
    }

    // Getters and setters
    public UUID getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(int minGrade) {
        this.minGrade = minGrade;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public List<Semester> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Semester> availability) {
        this.availability = availability;
    }

    // Additional methods
    public void addPrerequisite(Course prerequisite, String grade) {
        HashMap<Course, String> prerequisiteMap = new HashMap<>();
        prerequisiteMap.put(prerequisite, grade);
        this.prerequisites.add(prerequisiteMap);
    }

    public boolean checkAvailability(Semester semester) {
        return this.availability.contains(semester);
    }
}