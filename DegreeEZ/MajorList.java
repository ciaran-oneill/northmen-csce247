package DegreeEZ;
import java.util.ArrayList;

public class MajorList {
    private static User instance;
    private MajorList majorList;
    private ArrayList<Major> majors;

    private MajorList() {
        if (instance == null) instance = new MajorList(DataLoader.getMajorList("majorList.java"));
        return instance;
        // WIP
    }

    public static MajorList getInstance() {
        if (instance == null) instance = new Users(DataLoader.getUsers("users.json"));
        return instance;
        // WIP
    }

    public ArrayList<Major> getMajors() {
        if (instance == null) instance = new Major(DataLoader.getMajors("majors.json"));
        return instance;
        //WIP
    } 
}
