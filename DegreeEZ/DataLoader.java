package DegreeEZ;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.UUID;

class DataLoader {

    public static ArrayList<Advisor> loadAdvisors(String filePath) {
        ArrayList<Advisor> advisors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) { // Start of a new advisor record
                    String uuidString = line.trim().replace("{", "").replace("\"", "").replace(":", "").trim();
                    UUID uuid = UUID.fromString(uuidString); // Assumes UUID is the line directly after "{"
                    String advisorFirstName = "", advisorLastName = "", advisorUsername = "", advisorPassword = "";
                    ArrayList<UUID> studentUUIDs = new ArrayList<>();
                    while (!(line = reader.readLine()).trim().equals("}")) { // End of the advisor record
                        if (line.contains("advisor_firstName")) {
                            advisorFirstName = extractValue(line);
                        } else if (line.contains("advisor_lastName")) {
                            advisorLastName = extractValue(line);
                        } else if (line.contains("advisor_username")) {
                            advisorUsername = extractValue(line);
                        } else if (line.contains("advisor_password")) {
                            advisorPassword = extractValue(line);
                        } else if (line.contains("advisor_students")) {
                            studentUUIDs = extractUUIDs(reader); // Handle the array of student UUIDs
                        }
                    }
                    advisors.add(new Advisor(uuid, advisorUsername, advisorFirstName, advisorLastName, advisorPassword, new ArrayList<>(studentUUIDs)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return advisors;
    }

    public static ArrayList<Student> loadStudents(String filePath) {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) { // Start of a new student record
                    String uuidString = line.trim().replace("{", "").replace("\"", "").replace(":", "").trim();
                    UUID uuid = UUID.fromString(uuidString); // Assumes UUID is the line directly after "{"
                    String firstName = "", lastName = "", username = "", password = "";
                    UUID majorUUID = null, advisorUUID = null;
                    ArrayList<CompletedCourse> completedCourses = new ArrayList<>();
                    ArrayList<UUID> enrolledClassUUIDs = new ArrayList<>();
                    ArrayList<UUID> outstandingReqUUIDs = new ArrayList<>();

                    while (!(line = reader.readLine()).trim().equals("}")) { // End of the student record
                        if (line.contains("student_firstName")) {
                            firstName = extractValue(line);
                        } else if (line.contains("student_lastName")) {
                            lastName = extractValue(line);
                        } else if (line.contains("student_username")) {
                            username = extractValue(line);
                        } else if (line.contains("student_password")) {
                            password = extractValue(line);
                        } else if (line.contains("student_major")) {
                            majorUUID = UUID.fromString(extractValue(line));
                        } else if (line.contains("completedCourses")) {
                            completedCourses = extractCompletedCourses(reader); 
                        } else if (line.contains("enrolledClasses")) {
                            enrolledClassUUIDs = extractUUIDs(reader);
                        } else if (line.contains("outstandingReq")) {
                            outstandingReqUUIDs = extractUUIDs(reader);
                        } else if (line.contains("advisor")) {
                            advisorUUID = UUID.fromString(extractValue(line));
                        }
                    }
                    ArrayList<Course> enrolledClasses = CourseList.getCoursesByUUIDs(enrolledClassUUIDs);
                    ArrayList<Course> outstandingRequirements = CourseList.getCoursesByUUIDs(outstandingReqUUIDs);
                    Advisor advisor = AdvisorList.getAdvisorByUUID(advisorUUID);

                    students.add(new Student(uuid, firstName, lastName, username, password, majorUUID, completedCourses, enrolledClasses, outstandingRequirements, advisorUUID));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public static ArrayList<Course> loadCourses(String filePath) {
        ArrayList<Course> courses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) { // Start of a new course record
                    UUID courseId = UUID.fromString(line.trim().replace("{", "").replace("\"", "").replace(":", "").trim());
                    String courseName = "", courseSubject = "";
                    int courseNumber = 0, creditHours = 0, minGrade = 0;
                    ArrayList<Prerequisite> prerequisites = new ArrayList<>();
                    ArrayList<Semester> availability = new ArrayList<>();

                    while (!(line = reader.readLine()).trim().equals("}")) { // End of the course record
                        if (line.contains("course_name")) {
                            courseName = extractValue(line);
                        } else if (line.contains("course_subject")) {
                            courseSubject = extractValue(line);
                        } else if (line.contains("course_number")) {
                            courseNumber = Integer.parseInt(extractValue(line));
                        } else if (line.contains("course_prereq")) {
                            prerequisites = extractPrerequisites(reader);
                        } else if (line.contains("creditHours")) {
                            creditHours = Integer.parseInt(extractValue(line));
                        } else if (line.contains("availability")) {
                            availability = extractAvailability(reader);
                        }
                    }
                    Course newCourse = new Course(courseId, courseName, Subject.valueOf(courseSubject), courseNumber, availability, creditHours);
                    newCourse.setPrerequisites(prerequisites);
                    courses.add(newCourse);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    public static ArrayList<Major> loadMajors(String filePath) {
        ArrayList<Major> majors = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().startsWith("{")) { // Start of a new major record
                    UUID majorId = null;
                    String majorName = "";
                    ArrayList<Course> requiredClasses = new ArrayList<>();
                    int electiveCreditsRequired = 0;
                    ArrayList<Course> electiveOptions = new ArrayList<>();
    
                    while (!(line = reader.readLine()).trim().equals("}")) { // End of the major record
                        if (line.contains("major_id")) {
                            majorId = UUID.fromString(extractValue(line));
                        } else if (line.contains("major_name")) {
                            majorName = extractValue(line);
                        } else if (line.contains("requiredClasses")) {
                            ArrayList<UUID> requiredClassUUIDs = extractUUIDs(reader);
                            requiredClasses = CourseList.getCoursesByUUIDs(requiredClassUUIDs);
                        } else if (line.contains("electiveCreditsRequired")) {
                            electiveCreditsRequired = Integer.parseInt(extractValue(line));
                        } else if (line.contains("electiveOptions")) {
                            ArrayList<UUID> electiveOptionUUIDs = extractUUIDs(reader);
                            electiveOptions = CourseList.getCoursesByUUIDs(electiveOptionUUIDs);
                        }
                    }
    
                    majors.add(new Major(majorId, majorName, requiredClasses, electiveCreditsRequired, electiveOptions));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return majors;
    }


    private static ArrayList<Prerequisite> extractPrerequisites(BufferedReader reader) throws Exception {
        ArrayList<Prerequisite> prerequisites = new ArrayList<>();
        String line = reader.readLine().trim(); // Assumes this line is the opening of the prerequisites array "["

        while (!(line = reader.readLine().trim()).equals("]")) { // Read until the end of the array
            if (line.startsWith("{")) {
                UUID courseID = null;
                int minGrade = 0;

                while (!(line = reader.readLine().trim()).equals("}")) {
                    if (line.contains("courseID")) {
                        courseID = UUID.fromString(extractValue(line));
                    } else if (line.contains("minGrade")) {
                        minGrade = Integer.parseInt(extractValue(line));
                    }
                }
                prerequisites.add(new Prerequisite(courseID, minGrade));
            }
        }

        return prerequisites;
    }


    private static ArrayList<Semester> extractAvailability(BufferedReader reader) throws Exception {
        ArrayList<Semester> availability = new ArrayList<>();
        String line = reader.readLine().trim(); // Move to the line that should start the array
    
        // Check if we correctly start with the array opening bracket
        if (!line.equals("[")) {
            throw new IllegalArgumentException("Expected the start of availability array '[' but found: " + line);
        }
    
        while (!(line = reader.readLine().trim()).equals("]")) { // Until the end of the array
            line = line.replace("\"", "").replace(",", "").trim(); // Clean the line to extract the enum name
            try {
                availability.add(Semester.valueOf(line.toUpperCase())); // Add the semester to the list
            } catch (IllegalArgumentException e) {
                System.err.println("Warning: Unknown semester '" + line + "' found in the file");
            }
        }
    
        return availability;
    }
    

    private static ArrayList<CompletedCourse> extractCompletedCourses(BufferedReader reader) throws Exception {
        ArrayList<CompletedCourse> completedCourses = new ArrayList<>();
        String line = reader.readLine().trim(); // Move to the start of the completed courses array
    
        if (!line.startsWith("[")) {
            throw new IllegalArgumentException("Expected completedCourses array start '[' but found: " + line);
        }
    
        while (!(line = reader.readLine().trim()).equals("]")) { // Read until the end of the array
            if (line.startsWith("{")) { // Start of a new completed course object
                UUID courseId = null;
                int finalGrade = 0;
    
                while (!(line = reader.readLine().trim()).equals("}")) { // Read until the end of the object
                    if (line.contains("\"courseId\"")) {
                        courseId = UUID.fromString(extractValue(line));
                    } else if (line.contains("\"finalGrade\"")) {
                        finalGrade = Integer.parseInt(extractValue(line));
                    }
                }
    
                // Find the course by UUID and create a CompletedCourse object
                if (courseId != null) {
                    Course course = CourseList.getCourseByUUID(courseId);
                    if (course != null) {
                        completedCourses.add(new CompletedCourse(courseId, finalGrade));
                    } else {
                        System.err.println("Warning: Could not find a course matching ID " + courseId);
                    }
                }
            }
        }
    
        return completedCourses;
    }
    
    


    private static String extractValue(String line) {
        return line.substring(line.indexOf(":") + 1).replace("\"", "").trim();
    }

    private static ArrayList<UUID> extractUUIDs(BufferedReader reader) throws Exception {
        ArrayList<UUID> uuids = new ArrayList<>();
        String line = reader.readLine().trim(); // Reads the line containing the array
    
        // Remove the brackets and split by comma
        String[] uuidStrings = line.substring(1, line.length() - 1).split(",");
    
        for (String uuidString : uuidStrings) {
            uuidString = uuidString.trim().replace("\"", ""); // Remove any surrounding quotes
            uuids.add(UUID.fromString(uuidString));
        }
    
        return uuids;
    }


}