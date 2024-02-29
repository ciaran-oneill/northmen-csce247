package DegreeEZ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class DataLoader {

    private static final String STUDENTS_FILE_PATH = "students.json";
    private static final String ADVISORS_FILE_PATH = "advisors.json";

    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) {
                    String uuid = line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\""));
                    Student student = parseStudent(reader);
                    student.setUuid(UUID.fromString(uuid));
                    students.add(student);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    private static Student parseStudent(BufferedReader reader) throws Exception {
        String firstName = "", lastName = "", username = "";
        UUID studentMajor = null, advisor = null;
        String password = "";
        List<String> enrolledClasses = new ArrayList<>(), outstandingReq = new ArrayList<>();
        String line;
        while (!(line = reader.readLine()).trim().equals("},")) { 
            if (line.contains("student_firstName")) {
                firstName = extractValue(line);
            } else if (line.contains("student_lastName")) {
                lastName = extractValue(line);
            } else if (line.contains("student_username")) {
                username = extractValue(line);
            } else if (line.contains("student_password")) {
                password = extractValue(line);
            } else if (line.contains("student_major")) {
                String majorUuidString = extractValue(line);
                studentMajor = UUID.fromString(majorUuidString);

            } else if (line.contains("enrolledClasses")) {
                enrolledClasses = parseArray(reader);
            } else if (line.contains("outstandingReq")) {
                outstandingReq = parseArray(reader);
            } else if (line.contains("advisor")) {
                String advisorString = extractValue(line);
                advisor = UUID.fromString(advisorString);
            }
        }
        Student student = new Student(firstName, lastName, username); 
        student.setEnrolledClasses(enrolledClasses);
        student.setOutstandingReq(outstandingReq);
        return student;
    }
    
    private static List<String> parseArray(BufferedReader reader) throws Exception {
        List<String> arrayItems = new ArrayList<>();
        String line = reader.readLine().trim(); 
        if (!line.startsWith("[")) {
            throw new IllegalArgumentException("Expected array start '['");
        }
        if (!line.endsWith("]")) { 
            do {
                line = line.trim();
                if (line.endsWith(",")) {
                    line = line.substring(0, line.length() - 1); 
                }
                arrayItems.add(line.replace("\"", ""));
                line = reader.readLine().trim();
            } while (!line.endsWith("]")); 
        }
        line = line.substring(0, line.length() - 1).trim(); 
        if (!line.isEmpty()) {
            arrayItems.add(line.replace("\"", ""));
        }
        return arrayItems;
    }
    

    private static HashMap<UUID, Student> allStudents = loadAllStudents();

    public static List<Advisor> getAdvisors() {
        List<Advisor> advisors = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("advisors.json"));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) {
                    Advisor advisor = parseAdvisor(reader);
                    advisors.add(advisor);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return advisors;
    }

    private static Advisor parseAdvisor(BufferedReader reader) throws Exception {
        String firstName = "", lastName = "", username = "", password = "";
        ArrayList<Student> students = new ArrayList<>();
        String line;
        while (!(line = reader.readLine()).trim().equals("},")) {
            if (line.contains("advisor_firstName")) {
                firstName = extractValue(line);
            } else if (line.contains("advisor_lastName")) {
                lastName = extractValue(line);
            } else if (line.contains("advisor_username")) {
                username = extractValue(line);
            } else if (line.contains("advisor_password")) { 
                password = extractValue(line);
            } else if (line.contains("advisor_students")) {
                List<UUID> studentUuids = parseUuidArray(reader);
                for (UUID uuid : studentUuids) {
                    Student student = allStudents.get(uuid);
                    if (student != null) {
                        students.add(student);
                    }
                }
            }
        }
        return new Advisor(firstName, lastName, username, password, students);
    }

    private static List<UUID> parseUuidArray(BufferedReader reader) throws Exception {
        List<UUID> uuids = new ArrayList<>();
        String line = reader.readLine().trim(); // Start of array "["
        while (!(line = reader.readLine()).trim().equals("]")) { // Until the end of array "]"
            line = line.trim().replaceAll("[\",]", ""); // Remove potential commas and quotes
            if (!line.isEmpty()) {
                uuids.add(UUID.fromString(line));
            }
        }
        return uuids;
    }


    // Placeholder for a method to load all students into a map for quick lookup by UUID
    private static HashMap<UUID, Student> loadAllStudents() {
        // Implement loading logic here
        return new HashMap<>();
    }

    private static String extractValue(String line) {
        return line.substring(line.indexOf(":") + 2, line.length() - 1).replace("\"", "");
    }
}
