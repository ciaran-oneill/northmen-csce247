package DegreeEZ;
import java.util.ArrayList;

public class MajorList {
   // private static User instance;
    private static MajorList majorList;
    private ArrayList<Major> majors = new ArrayList<>();

    private MajorList() {
        majors = DataLoader.loadMajors("majors.json");
    }
    
    public static MajorList getInstance() {
        if(majorList==null) {
            majorList = new MajorList();
        }
        return majorList;
    }

    public boolean haveMajor(String name ) {
        return true;
    }

    public boolean addMajor(Major major) {
        for (Major existingMajor : majors) {
            if(existingMajor.getMajorName().equals(major.getMajorName())) {
                System.out.println("Major already exists");
                return false;
            }
        }
        majors.add(major);
        return true;
    }

    public Major getMajor(String majorName) {
        for (Major major : majors) {
            if(major.getMajorName().equals(majorName)) {
                return major;
            }
        }
        System.out.println("Major not found");
        return null;
    }

    public boolean MajorNameExists(String majorName) {
        for (Major major : majors) {
            if (major.getMajorName().equals(majorName)) {
                return true;
            }
        }
        return false;
    }


}
