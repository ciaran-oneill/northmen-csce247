package DegreeEZ;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

class DataLoader {

    public static ArrayList<User> loadUsers(String studentsFilePath, String advisorsFilePath) {
        ArrayList<User> users = new ArrayList<>();
        users.addAll(loadStudents(studentsFilePath));
        users.addAll(loadAdvisors(advisorsFilePath));
        return users;
    }
    

    public static ArrayList<Student> loadStudents(String filePath) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) {
                    UUID uuid = UUID.fromString(line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1)));
                    String firstName = "", lastName = "", username = "", password = "";
                    UUID major = null;
                    ArrayList<CompletedCourse> completedCourses = new ArrayList<CompletedCourse>();
                    ArrayList<Course> enrolledClasses = new ArrayList<Course>();
                    ArrayList<Course> outstandingReq = new ArrayList<Course>();
                    UUID advisor = null;

                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("student_firstName")) firstName = extractValue(line);
                        else if (line.contains("student_lastName")) lastName = extractValue(line);
                        else if (line.contains("student_username")) username = extractValue(line);
                        else if (line.contains("student_password")) password = extractValue(line);
                        else if (line.contains("student_major")) major = UUID.fromString(extractValue(line));
                        else if (line.trim().startsWith("\"completedCourses\"")) completedCourses = parseCompletedCoursesArray(reader);
                        else if (line.trim().startsWith("\"enrolledClasses\"")) enrolledClasses = parseCoursesArray(reader);
                        else if (line.trim().startsWith("\"outstandingReq\"")) outstandingReq = parseCoursesArray(reader);
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

    public static ArrayList<Advisor> loadAdvisors(String filePath) {
        ArrayList<Advisor> advisors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) {
                    UUID uuid = UUID.fromString(line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1)));
                    String firstName = "", lastName = "", username = "", password = "";
                    ArrayList<UUID> studentUuids = new ArrayList<>();
    
                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("advisor_firstName")) firstName = extractValue(line);
                        else if (line.contains("advisor_lastName")) lastName = extractValue(line);
                        else if (line.contains("advisor_username")) username = extractValue(line);
                        else if (line.contains("advisor_password")) password = extractValue(line);
                        else if (line.trim().startsWith("\"advisor_students\"")) studentUuids = parseUUIDArray(reader); 
                    }
    
                    advisors.add(new Advisor(uuid, username, firstName, lastName, password, studentUuids));
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
                if (line.trim().startsWith("{")) {
                    UUID majorId = UUID.fromString(line.substring(line.indexOf("\"") + 1, line.indexOf("\"", line.indexOf("\"") + 1)));
                    String majorName = "";
                    ArrayList<Course> requiredCourses = new ArrayList<>();
                    ArrayList<HashMap<String, Electives>> electiveCats = new ArrayList<HashMap<String,Electives>>();
    
                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("major_name")) majorName = extractValue(line);
                        // Placeholder for parsing required courses array
                        else if (line.trim().startsWith("\"requiredClasses\"")) requiredCourses = parseCoursesArray(reader); 
                        // Placeholder for parsing elective categories; implement based on actual structure
                        else if (line.trim().startsWith("\"electiveCats\"")) electiveCats = parseElectiveCats(reader); 
                    }
    
                    majors.add(new Major(majorId, majorName, requiredCourses, electiveCats));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majors;
    }

    public static ArrayList<Course> loadCourses(String filePath) {
        ArrayList<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("\"")) { // Start of a new course entry
                    UUID id = UUID.fromString(line.trim().split("\"")[1]); // Extracting UUID
                    reader.readLine(); // Move past '{'
                    String name = "";
                    Subject subject = null;
                    int number = 0;
                    int minGrade = 0;
                    ArrayList<HashMap<Course,String>> prerequisites = new ArrayList<HashMap<Course,String>>();
                    ArrayList<Semester> availability = new ArrayList<>();
                    int creditHours = 0;

                    while (!(line = reader.readLine().trim()).equals("},")) { // Ensure correct closing brace check
                        if (line.contains("course_name")) name = extractValue(line);
                        else if (line.contains("course_subject")) subject = Subject.valueOf(extractValue(line));
                        else if (line.contains("course_number")) number = Integer.parseInt(extractValue(line));
                        else if (line.contains("minGrade")) minGrade = Integer.parseInt(extractValue(line));
                        else if (line.trim().startsWith("\"availability\"")) {
                            availability = parseAvailability(reader.readLine());
                        }
                        else if (line.contains("creditHours")) creditHours = Integer.parseInt(extractValue(line));
                    }

                    // Adjust constructor to take parsed UUID
                    Course course = new Course(id, name, subject, number, prerequisites, minGrade, availability, creditHours);
                    courses.add(course);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    private static ArrayList<Semester> parseAvailability(String line) {
        ArrayList<Semester> availability = new ArrayList<>();
        // Assuming the availability array is always on a single line
        String[] parts = line.trim().split("\\[")[1].split("]")[0].split(",");
        for (String part : parts) {
            String semesterStr = part.trim().replaceAll("\"", "").toUpperCase();
            try {
                Semester semester = Semester.valueOf(semesterStr);
                availability.add(semester);
            } catch (IllegalArgumentException e) {
                System.err.println("Invalid semester value: " + semesterStr);
                // Handle the error or skip the invalid semester
            }
        }
        return availability;
    }
    

    private static ArrayList<HashMap<String, Electives>> parseElectiveCats(BufferedReader reader) {
       // to be implemented
        return new ArrayList<HashMap<String, Electives>>();
    }
    
    

    private static ArrayList<UUID> parseUUIDArray(BufferedReader reader) {
        // to be implemented
        return new ArrayList<>();
    }


    private static ArrayList<Course> parseCoursesArray(BufferedReader reader) {
        // to be implemented
        return new ArrayList<>();
    }

    private static ArrayList<CompletedCourse> parseCompletedCoursesArray(BufferedReader reader) throws Exception {
        ArrayList<CompletedCourse> completedCourses = new ArrayList<>();
        String line = reader.readLine().trim(); // Assuming the next line is the start of the array
        if (!line.startsWith("[")) {
            throw new IllegalArgumentException("Expected array start '[' but found: " + line);
        }
        while (!(line = reader.readLine().trim()).equals("]")) { // Until the end of the array
            if (line.startsWith("{")) {
                UUID courseId = null;
                String name = "";
                Subject subject = null;
                int number = 0;
                ArrayList<HashMap<Course, String>> prerequisites = new ArrayList<>(); // Simplified handling of prerequisites
                int minGrade = 0;
                int creditHours = 0;
                int finalGrade = 0;
                Semester semesterTaken = null;
                boolean pass = false;

                while (!(line = reader.readLine().trim()).equals("}")) {
                    if (line.contains("courseId")) courseId = UUID.fromString(extractValue(line));
                    else if (line.contains("\"name\"")) name = extractValue(line);
                    else if (line.contains("\"subject\"")) subject = Subject.valueOf(extractValue(line).toUpperCase());
                    else if (line.contains("\"number\"")) number = Integer.parseInt(extractValue(line));
                    // Assuming prerequisites are simplified and not parsed here for brevity
                    else if (line.contains("\"minGrade\"")) minGrade = Integer.parseInt(extractValue(line));
                    else if (line.contains("\"creditHours\"")) creditHours = Integer.parseInt(extractValue(line));
                    else if (line.contains("\"finalGrade\"")) finalGrade = Integer.parseInt(extractValue(line));
                    else if (line.contains("\"semesterTaken\"")) semesterTaken = Semester.valueOf(extractValue(line).toUpperCase());
                    else if (line.contains("\"pass\"")) pass = Boolean.parseBoolean(extractValue(line));
                }

                completedCourses.add(new CompletedCourse(courseId, name, subject, number, prerequisites, minGrade, creditHours, finalGrade, semesterTaken, pass));
            }
        }
        return completedCourses;
    }
    
    // Extracts a String value from a line in the JSON file
    private static String extractValue(String line) {
        return line.substring(line.indexOf(":") + 1).replaceAll("\"", "").trim();
    }
}