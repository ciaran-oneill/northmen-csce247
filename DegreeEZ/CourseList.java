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
<<<<<<< HEAD
    public Course getCourseByName(String name) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null; // or throw an exception if preferred
=======
    public Course getCourse(String name, int number) {
        if(!haveCourse(name, number)) return null;

        return new Course(name, number);

>>>>>>> 573f75325be219570882a41078471a4618bb1258
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

