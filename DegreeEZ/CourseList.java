package DegreeEZ;
import java.util.ArrayList;


public class CourseList {
    private static CourseList courseList;
    private ArrayList<Course> courses;

    public CourseList() {
        courses = DataLoader.loadCourses("courses.json");
    }

    public static CourseList getInstance() {
        if(courseList==null) {
            courseList = new CourseList();
        }

        return courseList;
    }
    public boolean haveCourse(String name, int number) {
        return true;
    }
    public Course getCourse(String name, int number) {
        if(!haveCourse(name, number)) return null;

        return new Course(name, number);

    }
}

