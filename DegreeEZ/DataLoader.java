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
        // Implement based on the structure of your Major JSON and class
        return majors;
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

    private static String extractValue(String line) {
        return line.substring(line.indexOf(":") + 2, line.length() - 1).replace("\"", "");
    }
}