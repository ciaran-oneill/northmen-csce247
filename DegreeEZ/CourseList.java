package DegreeEZ;
import java.util.ArrayList;
import java.util.UUID;


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
    public boolean haveCourse(Subject name, int number) {
        if()
    }
    public Course getCourse(String name, int number) {
        if(!haveCourse(name, number)) return null;

        return new Course(name, number);

    }

    public Course getCourseByUUID(UUID id) {
        for (Course course : courses) {
            if (course.getId().equals(id)) {
                return course;
            }
        }
        return null; // or throw an exception if preferred
    }

        

}

