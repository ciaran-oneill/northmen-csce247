package DegreeEZ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class DataLoader {

    public static List<Student> loadStudents(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{") && line.contains(":")) {
                    UUID uuid = UUID.fromString(line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")));
                    String firstName = "", lastName = "", username = "", password = "";
                    UUID major = null;
                    List<Course> enrolledClasses = null;
                    List<String> outstandingReq = new ArrayList<>();
                    UUID advisor = null;

                    while (!(line = reader.readLine()).trim().equals("}")) {
                        if (line.contains("student_firstName")) firstName = extractValue(line);
                        else if (line.contains("student_lastName")) lastName = extractValue(line);
                        else if (line.contains("student_username")) username = extractValue(line);
                        else if (line.contains("student_password")) password = extractValue(line);
                        else if (line.contains("student_major")) major = UUID.fromString(extractValue(line));
                        else if (line.trim().startsWith("\"enrolledClasses\"")) enrolledClasses = parseCoursesArray(reader);
                        else if (line.trim().startsWith("\"outstandingReq\"")) outstandingReq = parseStringArray(reader);
                        else if (line.contains("advisor")) advisor = UUID.fromString(extractValue(line).replaceAll("[{},]", "").trim());
                    }

                    students.add(new Student(uuid, firstName, lastName, username, password, major, enrolledClasses, outstandingReq, advisor));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
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
                    List<UUID> studentUuids = new ArrayList<>();
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

    public static List<Major> loadMajors(String filePath) {
        List<Major> majors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{") && !line.trim().endsWith("{")) {
                    // Extract the UUID of the major
                    String uuidStr = line.trim().substring(2, line.trim().length() - 2);
                    UUID uuid = UUID.fromString(uuidStr);

                    // Move to the major details
                    String majorName = "", electiveCreditsRequiredStr = "";
                    List<Course> requiredClasses = new ArrayList<>();
                    List<Course> electiveOptions = new ArrayList<>();
                    int electiveCreditsRequired = 0;

                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("major_name")) majorName = extractValue(line);
                        else if (line.trim().startsWith("\"requiredClasses\"")) requiredClasses = parseCoursesArray(reader);
                        else if (line.contains("electiveCreditsRequired")) electiveCreditsRequiredStr = extractValue(line);
                        else if (line.trim().startsWith("\"electiveOptions\"")) electiveOptions = parseCoursesArray(reader);
                    }

                    // Parsing the electiveCreditsRequired
                    electiveCreditsRequired = Integer.parseInt(electiveCreditsRequiredStr);

                    majors.add(new Major(uuid, majorName, requiredClasses, electiveCreditsRequired, electiveOptions));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majors;
    }

    public static List<Course> loadCourses(String filePath) {
        List<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{") && line.contains(":")) {
                    // Skipping the UUID as it's not directly used for Course instantiation
                    String courseName = "", courseSubject = "";
                    int courseNumber = 0, creditHours = 0;
                    List<String> coursePrereq = new ArrayList<>(), availability = new ArrayList<>();

                    while (!(line = reader.readLine().trim()).equals("}")) {
                        if (line.contains("course_name")) courseName = extractValue(line);
                        else if (line.contains("course_subject")) courseSubject = extractValue(line);
                        else if (line.contains("course_number")) courseNumber = Integer.parseInt(extractValue(line));
                        else if (line.trim().startsWith("\"course_prereq\"")) coursePrereq = parseStringArray(reader);
                        else if (line.contains("creditHours")) creditHours = Integer.parseInt(extractValue(line));
                        else if (line.trim().startsWith("\"availability\"")) availability = parseStringArray(reader);
                    }

                    courses.add(new Course(courseName, courseSubject, courseNumber, coursePrereq, creditHours, availability));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    private static List<UUID> parseStudentUuidsArray(BufferedReader reader) throws Exception {
        List<UUID> studentUuids = new ArrayList<>();
        String line = reader.readLine().trim(); // Move to the array content
        while (!(line = reader.readLine().trim()).endsWith("]")) { // Until the end of array
            UUID uuid = UUID.fromString(line.replaceAll("[\",]", "").trim());
            studentUuids.add(uuid);
        }
        return studentUuids;
    }

    private static List<Course> parseCoursesArray(BufferedReader reader) throws Exception {
        List<Course> courses = new ArrayList<>();
        String line = reader.readLine().trim(); 
        while (!(line = reader.readLine().trim()).endsWith("]")) { 
            String courseName = line.replaceAll("[\",]", "").trim();
            courses.add(new Course(courseName, 0)); 
        }
        return courses;
    }

    private static List<String> parseStringArray(BufferedReader reader) throws Exception {
        List<String> items = new ArrayList<>();
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