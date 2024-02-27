package DegreeEZ;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Major {
    public UUID majorID;
    public String majorName;
    public ArrayList<Course> requiredCourses;
    public HashMap<String, Electives> electiveCats;

    public Major(String majorName, ArrayList<Course> requiredCourses, HashMap<String, Electives> electiveCats){
        this.majorName = majorName;
        this.requiredCourses = requiredCourses;
        this.electiveCats = electiveCats;
    }

    public ArrayList<Course> getRequiredCourses() {
        // TODO
    }

    public ArrayList<Course> getElectiveCatList() {
        // TODO
    }

    public String getMajorName() {
        return majorName;
    }
}