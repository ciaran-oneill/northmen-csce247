package DegreeEZ;
import java.util.ArrayList;

public class DegreeWorksApplication {
    private User user;
    private CourseList courseList;
    private UserList userList;

    public DegreeWorksApplication() {
        // TODO
    }

    public User login(String username, String password) {
        // TODO
        return null;
    }

    public User createAccount(String firstName, String lastName, String username, String password) {
        // TODO
        return null;
    }

    public ArrayList<Course> findCourse() {
        // TODO
        return null;
    }

    public void addCourse(Course course) {
        // TODO
    }

    public void courseToComplete(Course course) {
        // TODO
    }

    public void courseOptions(Course course) {
        // TODO
    }

    public void completedCourses(Course course) {
        // TODO
    }

    public static MajorList getInstance() {
        // TODO
        return null;
    }

    public ArrayList<Major> getMajors() {
        // TODO
        return null;
    }

    public static ClassList getInstance() {
        // TODO
        return null;
    }

    public Course getCourses(String courseName) {
        // TODO
        return null;
    }

    public static UserList getInstance() {
        // TODO
        return null;
    }

    public User getUser(String firstName, String lastName) {
        // TODO
        return null;
    }

    public class Course {
        private Subject subject;
        private int number;
        private String name;

        public Course(Subject subject, int number, String name) {
            // TODO
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

        // Getters and setters for Course attributes

        public void addPrerequisite(Course prerequisite) {
            // TODO
        }

        public void minGrade(int grade) {
            // TODO
        }

        public boolean checkAvailability(String semester) {
            // TODO
            return false;
        }

        public void courseToComplete(ArrayList<String> course) {
            // TODO
        }

        public void courseOptions(ArrayList<String> course) {
            // TODO
        }

        public void changeMajor(String majorName) {
            // TODO
        }

        public ArrayList<String> competedCourses() {
            // TODO
            return null;
        }
    }

    public class MajorList {
        // TODO
    }

    public class UserList {
        // TODO
    }

    public class Subject {
        // TODO
    }

    public class Major {
        // TODO
    }

    public class CompletedCourse {
        // TODO
    }

    public class User {
        private String firstName;
        private String lastName;
        private String username;
        private String password;
        private String email;

        public User(String firstName, String lastName, String password) {
            // TODO
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        // Getters and setters for User attributes
    }

    public class Student extends User {
        // TODO
    }

    public class Advisor extends User {
        // TODO
    }
}
