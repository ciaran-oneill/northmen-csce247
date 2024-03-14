package DegreeEZ;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Major {
    public UUID majorID;
    public String majorName;
    public ArrayList<Course> requiredCourses;
    public HashMap<String, Electives> electiveCats;

    public Major(UUID majorId,String majorName, ArrayList<Course> requiredCourses) { // need to implement elective cats still
        this.majorName = majorName;
        this.requiredCourses = requiredCourses;
        this.electiveCats = electiveCats;
    }

    public ArrayList<Course> getRequiredCourses() {
        return this.requiredCourses;
    }

    public HashMap<String,Electives> getElectiveCatList() {
        return this.electiveCats;
    }

    public String getMajorName() {
        return majorName;
    }

    public UUID getMajorUuid() {
        return this.majorID;
    }
}