package DegreeEZ;

import java.util.UUID;

public class Prerequisite {
    private UUID courseID;
    private int minGrade;

    public Prerequisite(UUID courseID, int minGrade) {
        this.courseID = courseID;
        this.minGrade = minGrade;
    }

    public String toString() {
        Course course = CourseList.getCourseByUUID(courseID);
        if (course != null) {
            return course.courseCode() + " (Min Grade: " + minGrade + ")";
        } else {
            return "Unknown Course ID: " + courseID.toString() + " (Min Grade: " + minGrade + ")";
        }
    }

    public UUID getCourseID() {
        return courseID;
    }

    public void setCourseID(UUID courseID) {
        this.courseID = courseID;
    }

    public int getMinGrade() {
        return minGrade;
    }

    public void setMinGrade(int minGrade) {
        this.minGrade = minGrade;
    }
}
