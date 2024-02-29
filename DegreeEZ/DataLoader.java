package DegreeEZ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

class DataLoader {

    public static ArrayList<Student> loadStudents(String filePath) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) {
                    UUID uuid = UUID.fromString(line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1)));
                    String firstName = "", lastName = "", username = "", password = "";
                    UUID major = null;
                    ArrayList<CompletedCourse> completedCourses = new ArrayList<>();
                    ArrayList<Course> enrolledClasses = new ArrayList<>();
                    ArrayList<String> outstandingReq = new ArrayList<>();
                    UUID advisor = null;

                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("student_firstName")) firstName = extractValue(line);
                        else if (line.contains("student_lastName")) lastName = extractValue(line);
                        else if (line.contains("student_username")) username = extractValue(line);
                        else if (line.contains("student_password")) password = extractValue(line);
                        else if (line.contains("student_major")) major = UUID.fromString(extractValue(line));
                        else if (line.trim().startsWith("\"completedCourses\"")) completedCourses = parseCompletedCoursesArray(reader);
                        else if (line.trim().startsWith("\"enrolledClasses\"")) enrolledClasses = parseCoursesArray(reader);
                        else if (line.trim().startsWith("\"outstandingReq\"")) outstandingReq = parseStringArray(reader);
                        else if (line.contains("advisor")) advisor = UUID.fromString(extractValue(line).replaceAll("[{},]", "").trim());
                    }

                    students.add(new Student(uuid, firstName, lastName, username, password, major, completedCourses, enrolledClasses, outstandingReq, advisor));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private static ArrayList<CompletedCourse> parseCompletedCoursesArray(BufferedReader reader) throws Exception {
        ArrayList<CompletedCourse> completedCourses = new ArrayList<>();
        String line = reader.readLine().trim(); // Assuming the next line is the start of the array
        if (!line.startsWith("[")) {
            throw new IllegalArgumentException("Expected array start '[' but found: " + line);
        }
        while (!(line = reader.readLine().trim()).equals("]")) { // Until the end of the array
            if (!line.equals("[")) { // Skip the starting bracket of the array
                // Simplified parsing logic, assuming a structure like: { "courseId": "uuid", "grade": "A" }
                UUID courseId = null;
                String grade = "";
                // Extract courseId and grade from the current object
                if (line.contains("courseId")) {
                    courseId = UUID.fromString(extractValue(line));
                    line = reader.readLine().trim(); // Move to grade line
                }
                if (line.contains("grade")) {
                    grade = extractValue(line);
                }
                completedCourses.add(new CompletedCourse(courseId, grade));
                reader.readLine(); // Assuming this reads the closing '}' of the completed course object
            }
        }
        return completedCourses;
    }

    public static List<Advisor> loadAdvisors(String filePath) {
        ArrayList<Advisor> advisors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{") && !line.trim().endsWith("{")) {
                    String uuidStr = line.trim().substring(2, line.trim().length() - 2);
                    UUID uuid = UUID.fromString(uuidStr);
                    String firstName = "", lastName = "", username = "", password = "";
                    ArrayList<UUID> studentUuids = new ArrayList<>();
                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("advisor_firstName")) firstName = extractValue(line);
                        else if (line.contains("advisor_lastName")) lastName = extractValue(line);
                        else if (line.contains("advisor_username")) username = extractValue(line);
                        else if (line.contains("advisor_password")) password = extractValue(line);
                        else if (line.contains("advisor_students")) studentUuids = parseStudentUuidsArray(reader);
                    }
                    advisors.add(new Advisor(uuid, firstName, lastName, username, password, studentUuids));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return advisors;
    }

    public static ArrayList<Major> loadMajors(String filePath) {
        ArrayList<Major> majors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{") && !line.trim().endsWith("{")) {
                    UUID uuid = UUID.fromString(line.trim().substring(2, line.trim().length() - 2));
                    String majorName = "";
                    ArrayList<Course> requiredClasses = new ArrayList<>();
                    //HashMap<String, Electives> electiveCats = new HashMap<>();

                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("major_name")) {
                            majorName = extractValue(line);
                        } else if (line.trim().startsWith("\"requiredClasses\"")) {
                            requiredClasses = parseCoursesArray(reader);
                        }
                    }
                    majors.add(new Major(uuid, majorName, requiredClasses));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majors;
    }

    public static ArrayList<User> getUsers(String studentsFilePath, String advisorsFilePath) {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(loadStudents(studentsFilePath));
        users.addAll(loadAdvisors(advisorsFilePath)); 
        return users;
    }


    public static ArrayList<Course> loadCourses(String filePath) {
        ArrayList<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) {
                    UUID id = null;
                    String courseName = "", courseSubject = "";
                    int courseNumber = 0, creditHours = 0;
                    ArrayList<String> coursePrereq = new ArrayList<>(), availability = new ArrayList<>();
                    // Extract UUID from the line
                    if (line.contains(":")) {
                        String uuidStr = line.trim().substring(2, line.trim().indexOf(":") - 1);
                        id = UUID.fromString(uuidStr);
                    }
                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("course_name")) courseName = extractValue(line);
                        else if (line.contains("course_subject")) courseSubject = extractValue(line);
                        else if (line.contains("course_number")) courseNumber = Integer.parseInt(extractValue(line));
                        else if (line.trim().startsWith("\"course_prereq\"")) coursePrereq = parseStringArray(reader);
                        else if (line.contains("creditHours")) creditHours = Integer.parseInt(extractValue(line));
                        else if (line.trim().startsWith("\"availability\"")) availability = parseStringArray(reader);
                    }
                    // Assuming a method to convert courseSubject string to Subject enum
                    Subject subject = Subject.valueOf(courseSubject.toUpperCase());
                    // Assuming a method to convert availability strings to Semester enum list
                    ArrayList<Semester> semesters = new ArrayList<>();
                    for (String sem : availability) {
                        semesters.add(Semester.valueOf(sem.toUpperCase()));
                    }
                    // Assuming prerequisites are just a list of course names and minGrade is a fixed value or not required here
                    courses.add(new Course(id, courseName, subject, courseNumber, coursePrereq, creditHours, semesters));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    private static ArrayList<UUID> parseStudentUuidsArray(BufferedReader reader) throws Exception {
        ArrayList<UUID> studentUuids = new ArrayList<>();
        String line = reader.readLine().trim(); // Move to the array content
        while (!(line = reader.readLine().trim()).endsWith("]")) { // Until the end of array
            UUID uuid = UUID.fromString(line.replaceAll("[\",]", "").trim());
            studentUuids.add(uuid);
        }
        return studentUuids;
    }

    private static ArrayList<Course> parseCoursesArray(BufferedReader reader) throws Exception {
        ArrayList<Course> courses = new ArrayList<>();
        String line = reader.readLine().trim(); 
        while (!(line = reader.readLine().trim()).endsWith("]")) { 
            String courseName = line.replaceAll("[\",]", "").trim();
            courses.add(new Course(courseName, 0)); 
        }
        return courses;
    }

    private static ArrayList<String> parseStringArray(BufferedReader reader) throws Exception {
        ArrayList<String> items = new ArrayList<>();
        String line = reader.readLine().trim(); // Move to the array content
        if (!line.startsWith("[")) {
            throw new IllegalArgumentException("Expected array start '[' but found: " + line);
        }
        if (!line.equals("[")) { 
            if (line.endsWith("]")) {
                line = line.substring(1, line.length() - 1); 
                for (String item : line.split(",")) {
                    items.add(item.trim().replaceAll("^\"|\"$", "")); 
                }
            } else {
                do {
                    line = reader.readLine().trim();
                    if (line.endsWith(",") || line.endsWith("]")) {
                        line = line.substring(0, line.length() - 1); 
                    }
                    items.add(line.replaceAll("^\"|\"$", ""));
                } while (!line.endsWith("]"));
            }
        }
        return items;
    }
    

    private static String extractValue(String line) {
        return line.substring(line.indexOf(":") + 2, line.length() - 1).replace("\"", "");
    }
}