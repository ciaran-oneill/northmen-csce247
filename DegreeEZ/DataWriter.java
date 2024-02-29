package DegreeEZ;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class DataWriter {

    public static void saveStudents(String filePath, List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("[\n");
            for (int i = 0; i < students.size(); i++) {
                Student student = students.get(i);
                String studentJson = String.format(
                    "    {\n" +
                    "        \"%s\": {\n" +
                    "            \"student_firstName\": \"%s\",\n" +
                    "            \"student_lastName\": \"%s\",\n" +
                    "            \"student_username\": \"%s\",\n" +
                    "            \"student_password\": \"%s\",\n" +
                    "            \"student_major\": \"%s\",\n" +
                    "            \"enrolledClasses\": %s,\n" +
                    "            \"outstandingReq\": %s,\n" +
                    "            \"advisor\": \"%s\"\n" +
                    "        }\n" +
                    "    }%s",
                    student.getUuid().toString(), student.getFirstName(), student.getLastName(),
                    student.getUserName(), student.getPassword(), student.getMajorUuid().toString(),
                    formatStringList(student.getEnrolledClasses()), formatStringList(student.getOutstandingRequirements()),
                    student.getAdvisorUuid().toString(), (i < students.size() - 1) ? "," : "");
                writer.write(studentJson);
                writer.newLine();
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveAdvisors(String filePath, List<Advisor> advisors) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("[\n");
            for (int i = 0; i < advisors.size(); i++) {
                Advisor advisor = advisors.get(i);
                String advisorJson = String.format(
                    "    {\n" +
                    "        \"%s\": {\n" +
                    "            \"advisor_firstName\": \"%s\",\n" +
                    "            \"advisor_lastName\": \"%s\",\n" +
                    "            \"advisor_username\": \"%s\",\n" +
                    "            \"advisor_password\": \"%s\",\n" +
                    "            \"advisor_students\": %s\n" +
                    "        }\n" +
                    "    }%s",
                    advisor.getUuid().toString(), advisor.getFirstName(), advisor.getLastName(),
                    advisor.getUserName(), advisor.getPassword(), formatUuidList(advisor.getStudentUuids()),
                    (i < advisors.size() - 1) ? "," : "");
                writer.write(advisorJson);
                writer.newLine();
            }
            writer.write("]");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String formatStringList(List<String> items) {
        if (items == null || items.isEmpty()) return "[]";
        String result = items.stream()
                             .map(item -> "\"" + item + "\"")
                             .reduce((acc, item) -> acc + ", " + item)
                             .orElse("");
        return "[" + result + "]";
    }

    private static String formatUuidList(List<UUID> uuids) {
        if (uuids == null || uuids.isEmpty()) return "[]";
        String result = uuids.stream()
                             .map(uuid -> "\"" + uuid.toString() + "\"")
                             .reduce((acc, uuid) -> acc + ", " + uuid)
                             .orElse("");
        return "[" + result + "]";
    }
}
