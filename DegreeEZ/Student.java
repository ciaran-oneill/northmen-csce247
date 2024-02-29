package DegreeEZ;

import java.util.ArrayList;
import java.util.UUID;


public class Student extends User {
    private UUID majorUuid; 
    private ArrayList<CompletedCourse> completedCourses;
    private ArrayList<Course> enrolledCourses;
    private ArrayList<Course> outstandingRequirements;
    private UUID advisorUuid; 

    // Constructor
    public Student(UUID uuid, String firstName, String lastName, String username, String password, UUID majorUuid, ArrayList<CompletedCourse> completedCourses,  ArrayList<Course> enrolledCourses, ArrayList<Course> outstandingRequirements, UUID advisorUuid) {
        super(uuid, firstName, lastName, username, password);
        this.majorUuid = majorUuid;
        this.completedCourses = completedCourses;
        this.enrolledCourses = enrolledCourses;
        this.outstandingRequirements = outstandingRequirements;
        this.advisorUuid = advisorUuid;
    }

    public UUID getMajorUuid(UUID studentMajor) {
        return this.majorUuid;
    }

    public ArrayList<Course> getEnrolledClasses() {
        return this.enrolledCourses;
    }

    public ArrayList<Course> getOutstandingRequirements() {
        return this.outstandingRequirements;
    }

    public ArrayList<CompletedCourse> getCompletedCourses() {
        return this.completedCourses;
    }

    public UUID getAdvisorUuid() {
        return this.advisorUuid;
    }

    public void setMajorUuid(UUID newMajor) {
        this.majorUuid = newMajor;
    }

    public void setEnrolledCourses(ArrayList<Course> newEnrolledCourses) {
        this.enrolledCourses = newEnrolledCourses;
    }

    public void addEnrolledCourse(Course newCourse) {
        enrolledCourses.add(newCourse);
    }

    public void setCompletedCourses(ArrayList<CompletedCourse> newCompletedCourses) {
        this.completedCourses = newCompletedCourses;
    }

    public void addCompletedCourse(CompletedCourse newCompletedCourse) {
        completedCourses.add(newCompletedCourse);
    }

    public void setOutstandingRequirements(ArrayList<Course> newOustandingRequirements) {
        this.outstandingRequirements = newOustandingRequirements;
    }
    
    public void addOutstandingRequirement(Course newOutstandingRequirement) {
        outstandingRequirements.add(newOutstandingRequirement);
    }



}
